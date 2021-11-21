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
    install -d ${DEPLOYDIR}
    cp ${WORKDIR}/config.txt ${DEPLOYDIR}/bootfiles
}

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bootfiles"

PACKAGE_ARCH = "${MACHINE_ARCH}"
