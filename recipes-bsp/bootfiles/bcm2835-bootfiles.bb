DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

inherit deploy nopackages

INHIBIT_DEFAULT_DEPS = "1"

PV = "20210527"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${PV}.tar.gz"
SRC_URI[sha256sum] = "e137fff9d9de08b4146b77f6711ce4df51c907b06239153b3c123510c833add9"

RDEPENDS_${PN} = "rpi-config"

COMPATIBLE_MACHINE = "^rpi$"

#S = "${WORKDIR}/firmware-schnitzel-${PV}/boot"
S = "${WORKDIR}/firmware-1.${PV}/boot"

do_deploy() {
    install -d ${DEPLOYDIR}/${PN}

    for i in ${S}/*.elf ; do
        cp $i ${DEPLOYDIR}/${PN}
    done
    for i in ${S}/*.dat ; do
        cp $i ${DEPLOYDIR}/${PN}
    done
    for i in ${S}/*.bin ; do
        cp $i ${DEPLOYDIR}/${PN}
    done

    # Add stamp in deploy directory
    touch ${DEPLOYDIR}/${PN}/${PN}-${PV}.stamp
}

do_deploy[depends] += "rpi-config:do_deploy"

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${PN}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Fetching git is close to suicide
INSANE_SKIP = "src-uri-bad"

