DESCRIPTION = "Closed source binary files to help boot the ARM on the BCM2835."
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://LICENCE.broadcom;md5=4a4d169737c0786fb9482bb6d30401d1"

inherit deploy

PV = "20170217"

SRC_URI = "https://github.com/schnitzeltony/firmware/archive/schnitzel-${PV}.tar.gz"
SRC_URI[md5sum] = "1bba0acedc5776da4f2f120efd1c3e65"
SRC_URI[sha256sum] = "998d92d676cc34a17335114b4846253c563adf3c619439c0b255a4b4efa78220"

RDEPENDS_${PN} = "rpi-config"

COMPATIBLE_MACHINE = "raspberrypi"

S = "${WORKDIR}/firmware-schnitzel-${PV}/boot"

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

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/${PN}"

PACKAGE_ARCH = "${MACHINE_ARCH}"

