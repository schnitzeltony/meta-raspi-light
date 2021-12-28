DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

inherit deploy nopackages

INHIBIT_DEFAULT_DEPS = "1"

PV = "20211118"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${PV}.tar.gz"
SRC_URI[sha256sum] = "0a2d395f454fc0b4e1c639db57258abb53ca0d9ecd596bd8c836f2a50d6a49f3"

DEPENDS = "rpi-config rpi-cmdline"

COMPATIBLE_MACHINE = "^rpi$"

S = "${WORKDIR}/firmware-1.${PV}/boot"

do_deploy() {
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}

    for i in ${S}/*.elf ; do
        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    done
    for i in ${S}/*.dat ; do
        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    done
    for i in ${S}/*.bin ; do
        cp $i ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    done

    install -d ${DEPLOYDIR}/${OVERLAYS_DIR_NAME}
    cp ${S}/${OVERLAYS_DIR_NAME}/README ${DEPLOYDIR}/${OVERLAYS_DIR_NAME}
}

do_deploy[depends] += "rpi-config:do_deploy rpi-cmdline:do_deploy"

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME} ${DEPLOYDIR}/${OVERLAYS_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Fetching git is close to suicide
INSANE_SKIP = "src-uri-bad"

