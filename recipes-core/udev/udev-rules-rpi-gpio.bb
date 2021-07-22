SUMMARY = "Raspi udev-rule allowing group dialout access to GPIO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " file://60-rpi.gpio-common.rules"

S = "${WORKDIR}"

INHIBIT_DEFAULT_DEPS = "1"

do_install () {
    install -d ${D}${base_libdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/60-rpi.gpio-common.rules ${D}${base_libdir}/udev/rules.d/
}
