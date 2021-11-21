DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

inherit deploy nopackages

INHIBIT_DEFAULT_DEPS = "1"

PV = "20211007"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${PV}.tar.gz"
SRC_URI[sha256sum] = "1afa2ed736ac08c75768847a71d6558e329bdade14db4da9c71fcff8977d26cd"

DEPENDS = "rpi-config rpi-cmdline"

COMPATIBLE_MACHINE = "^rpi$"

#S = "${WORKDIR}/firmware-schnitzel-${PV}/boot"
S = "${WORKDIR}/firmware-1.${PV}/boot"

do_deploy() {
    install -d ${DEPLOYDIR}/bootfiles

    for i in ${S}/*.elf ; do
        cp $i ${DEPLOYDIR}/bootfiles
    done
    for i in ${S}/*.dat ; do
        cp $i ${DEPLOYDIR}/bootfiles
    done
    for i in ${S}/*.bin ; do
        cp $i ${DEPLOYDIR}/bootfiles
    done
}

do_deploy[depends] += "rpi-config:do_deploy"

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bootfiles"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Fetching git is close to suicide
INSANE_SKIP = "src-uri-bad"

