DESCRIPTION = "add udisk/udev rule to hide boot partition from udev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"
SRC_URI = "file://80-udisks-rpi.rules"

do_install () {
	install -d ${D}${base_libdir}/udev/rules.d
	install -m 644 ${WORKDIR}/80-udisks-rpi.rules ${D}${base_libdir}/udev/rules.d
}

FILES_${PN} = "${base_libdir}/udev/rules.d"
