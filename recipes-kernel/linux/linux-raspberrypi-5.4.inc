inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"

COMPATIBLE_MACHINE = "raspberrypi"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"


KCONFIG_MODE = "--alldefconfig"
# We pull in the default config from the RPi kernel GIT tree.
KBUILD_DEFCONFIG_raspberrypi0-wifi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi-cm3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi2 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi2-a9 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3-a9 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG_raspberrypi3-64 ?= "bcmrpi3_defconfig"
KBUILD_DEFCONFIG_raspberrypi4 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG_raspberrypi4-64 ?= "bcm2711_defconfig"

# serial - necessary for early kernel problems (requires adapter)
#CONSOLE="serial0,115200"
# screen
CONSOLE="tty1"

# CMDLINE for raspberrypi

CMDLINE = "dwc_otg.lpm_enable=0 console=${CONSOLE} root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_append = ' ${@oe.utils.conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'

# Set the verbosity of kernel messages during runtime
# shut up
#CMDLINE_append = " quiet"
# verbose
#CMDLINE_append = " debug"

# Avoid screen auto switch off
CMDLINE_append = " consoleblank=0"

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"

# Set a variable in .configure
# $1 - Configure variable to be set
# $2 - value [n/y/value]
kernel_configure_variable() {
    # Remove the config
    CONF_SED_SCRIPT="$CONF_SED_SCRIPT /CONFIG_$1[ =]/d;"
    if test "$2" = "n"
    then
        echo "# CONFIG_$1 is not set" >> ${B}/.config
    else
        echo "CONFIG_$1=$2" >> ${B}/.config
    fi
}

do_configure_prepend() {
    # Clean .config
    echo "" > ${B}/.config
    CONF_SED_SCRIPT=""

    # Set cmdline
    kernel_configure_variable CMDLINE "\"${CMDLINE}\""

    # Localversion
    kernel_configure_variable LOCALVERSION "\"\""
    kernel_configure_variable LOCALVERSION_AUTO n

    if [ "${INITRAMFS_IMAGE_BUNDLE}" = "1" ]; then
        kernel_configure_variable BLK_DEV_INITRD y
        kernel_configure_variable INITRAMFS_SOURCE ""
        kernel_configure_variable RD_GZIP y
        kernel_configure_variable OVERLAY_FS y
        kernel_configure_variable SQUASHFS y
        kernel_configure_variable UBIFS_FS y
    fi

    # Activate the configuration options for VC4
    kernel_configure_variable I2C_BCM2835 y
    kernel_configure_variable DRM y
    kernel_configure_variable DRM_FBDEV_EMULATION y
    kernel_configure_variable DRM_VC4 y
    kernel_configure_variable FB_BCM2708 n

    # Activate onboard sound by default
    kernel_configure_variable SND y
    # kernel_configure_variable SND_BCM2835 y

    # 500Hz timers
    kernel_configure_variable HZ_500 y
    kernel_configure_variable HZ 500

    # CPU freq governor defaults
    kernel_configure_variable CPU_FREQ_DEFAULT_GOV_POWERSAVE n
    kernel_configure_variable CPU_FREQ_DEFAULT_GOV_CONSERVATIVE n
    kernel_configure_variable CPU_FREQ_DEFAULT_GOV_USERSPACE n

    # Realtime
    if [ "${ENABLE_RPI_RT}" = "1" ]; then
        kernel_configure_variable PREEMPT_RCU y
        kernel_configure_variable PREEMPT y
        kernel_configure_variable PREEMPT_RT_BASE y
        kernel_configure_variable PREEMPT_LAZY y
        kernel_configure_variable PREEMPT_VOLUNTARY n
        kernel_configure_variable PREEMPT_RT_FULL y
        kernel_configure_variable PREEMPT_COUNT y
        kernel_configure_variable DEBUG_PREEMPT n
        kernel_configure_variable PREEMPT_TRACER n
        # RT-Kernel: governor performance
        kernel_configure_variable CPU_FREQ_DEFAULT_GOV_PERFORMANCE y
        kernel_configure_variable CPU_FREQ_DEFAULT_GOV_ONDEMAND n
    else
        # governor ondemand
        kernel_configure_variable CPU_FREQ_DEFAULT_GOV_PERFORMANCE n
        kernel_configure_variable CPU_FREQ_DEFAULT_GOV_ONDEMAND y
    fi

    # Keep this the last line
    # Remove all modified configs and add the rest to .config
    sed -e "${CONF_SED_SCRIPT}" < '${WORKDIR}/defconfig' >> '${B}/.config'

    yes '' | oe_runmake oldconfig
}

do_deploy_append() {
    # Modify cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
    if [ ${PITFT} = "1" ]; then
        PITFT_PARAMS="fbcon=map:10 fbcon=font:VGA8x8"
    fi
    echo "${CMDLINE}${PITFT_PARAMS}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt

    # deploy kernel images with version for card-kernel-wtite.sh
    for type in ${KERNEL_IMAGETYPES} ; do
        cp ${KERNEL_OUTPUT_DIR}/${type} ${DEPLOYDIR}/${type}-${KERNEL_VERSION}
    done
}
