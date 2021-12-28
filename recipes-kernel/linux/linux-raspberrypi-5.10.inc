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

SRC_URI += " \
    ${@bb.utils.contains("INITRAMFS_IMAGE_BUNDLE", "1", "file://initramfs-image-bundle.cfg", "", d)} \
    file://vc4graphics.cfg \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "file://rt.cfg", "", d)} \
    "


do_deploy:append() {
    # deploy kernel images with version for meta-mortsgna's card-kernel-write.sh
    for type in ${KERNEL_IMAGETYPES} ; do
        cp ${KERNEL_OUTPUT_DIR}/${type} ${DEPLOYDIR}/${type}-${KERNEL_VERSION}
    done

    # rework layout to what raspios does
	install -d $deployDir/${OVERLAYS_DIR_NAME}
	install -d $deployDir/${BOOTFILES_DIR_NAME}
    for dtbf in ${KERNEL_DEVICETREE}; do
        dtb=`normalize_dtb "$dtbf"`
        dtb_ext=${dtb##*.}
        dtb_base_name=`basename $dtb .$dtb_ext`

        # remove dtb symlinks
        rm $deployDir/$dtb_base_name.$dtb_ext
        rm $deployDir/$dtb_base_name-${KERNEL_DTB_LINK_NAME}.$dtb_ext

        if [ "$dtb_ext" = "dtbo" -o $dtb_base_name.$dtb_ext = 'overlay_map.dtb' ]; then
            mv $deployDir/$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext $deployDir/${OVERLAYS_DIR_NAME}/$dtb_base_name.$dtb_ext
        else
            mv $deployDir/$dtb_base_name-${KERNEL_DTB_NAME}.$dtb_ext $deployDir/${BOOTFILES_DIR_NAME}/$dtb_base_name.$dtb_ext
        fi
    done
}
