DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Broadcom-RPi"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

inherit deploy nopackages

PV = "20200205"

SRC_URI = "https://github.com/raspberrypi/firmware/archive/1.${PV}.tar.gz"
#SRC_URI = "https://github.com/schnitzeltony/firmware/archive/schnitzel-${PV}.tar.gz"
SRC_URI[md5sum] = "d37fe1c3b1e0b2d42932651478069c40"
SRC_URI[sha256sum] = "d7b45105aabf168b072e7ff967a461c2afef80cc3909ffc0292ed0ba53d488b0"

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

