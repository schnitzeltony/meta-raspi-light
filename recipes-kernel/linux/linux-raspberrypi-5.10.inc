inherit kernel siteinfo
require recipes-kernel/linux/linux-yocto.inc

DESCRIPTION = "Linux Kernel for Raspberry Pi"

COMPATIBLE_MACHINE = "raspberrypi"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"


KCONFIG_MODE = "--alldefconfig"
# We pull in the default config from the RPi kernel GIT tree.
KBUILD_DEFCONFIG:raspberrypi0-wifi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG:raspberrypi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG:raspberrypi-cm3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG:raspberrypi2 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG:raspberrypi2-a9 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG:raspberrypi3 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG:raspberrypi3-a9 ?= "bcm2709_defconfig"
KBUILD_DEFCONFIG:raspberrypi3-64 ?= "bcmrpi3_defconfig"
KBUILD_DEFCONFIG:raspberrypi4 ?= "bcm2711_defconfig"
KBUILD_DEFCONFIG:raspberrypi4-64 ?= "bcm2711_defconfig"

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

do_configure:prepend() {
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

do_deploy:append() {
    # deploy kernel images with version for card-kernel-write.sh
    for type in ${KERNEL_IMAGETYPES} ; do
        cp ${KERNEL_OUTPUT_DIR}/${type} ${DEPLOYDIR}/${type}-${KERNEL_VERSION}
    done
}
