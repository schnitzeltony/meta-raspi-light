SUMMARY = "config.txt file for the Raspberry Pi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "file://config.txt"

S = "${WORKDIR}/git"

PR = "r5"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy nopackages

do_deploy() {
    install -d ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    cp ${WORKDIR}/config.txt ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}

    if grep -q -E '^.{80}.$' ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/config.txt; then
        bbwarn "config.txt contains lines longer than 80 characters, this is not supported"
    fi
}

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
