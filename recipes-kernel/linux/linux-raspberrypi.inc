require linux-rpi.inc
inherit linux-raspberrypi-base

DESCRIPTION = "Linux Kernel for Raspberry Pi"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

COMPATIBLE_MACHINE = "raspberrypi"

PE = "1"
PV = "${LINUX_VERSION}+git${SRCPV}"

# We pull in the default config from the RPi kernel GIT tree.
KBUILD_DEFCONFIG_raspberrypi ?= "bcmrpi_defconfig"
KBUILD_DEFCONFIG_raspberrypi2 ?= "bcm2709_defconfig"

# CMDLINE for raspberrypi
CMDLINE = "dwc_otg.lpm_enable=0 console=serial0,115200 root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_append = ' ${@base_conditional("ENABLE_KGDB", "1", "kgdboc=serial0,115200", "", d)}'

KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains("MACHINE_FEATURES", "pitft28r", "stmpe-ts", "", d)}"

do_deploy_append() {
    # Deploy cmdline.txt
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    PITFT="${@bb.utils.contains("MACHINE_FEATURES", "pitft", "1", "0", d)}"
    if [ ${PITFT} = "1" ]; then
        PITFT_PARAMS="fbcon=map:10 fbcon=font:VGA8x8"
    fi
    echo "${CMDLINE}${PITFT_PARAMS}" > ${DEPLOYDIR}/bcm2835-bootfiles/cmdline.txt
}

do_bundle_initramfs_append() {
    if [ ! -z "${INITRAMFS_IMAGE}" -a x"${INITRAMFS_IMAGE_BUNDLE}" = x1 ]; then
        if test "x${KERNEL_IMAGETYPE}" != "xuImage" ; then
            if test -n "${KERNEL_DEVICETREE}"; then
                # Add RPi bootloader trailer to kernel image to enable DeviceTree support
                for type in ${KERNEL_IMAGETYPES} ; do
                    ${STAGING_BINDIR_NATIVE}/mkknlimg --dtok ${KERNEL_OUTPUT_DIR}/$type.initramfs ${KERNEL_OUTPUT_DIR}/$type.initramfs
                done
            fi
        fi
    fi
}
