SUMMARY = "config.txt file for the Raspberry Pi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "file://config.txt"

S = "${WORKDIR}/git"

PR = "r5"

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    cp ${WORKDIR}/config.txt ${DEPLOYDIR}/bcm2835-bootfiles/
}

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bcm2835-bootfiles"

PACKAGE_ARCH = "${MACHINE_ARCH}"
