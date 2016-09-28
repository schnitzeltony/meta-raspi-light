FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.22"

SRCREV = "5a570ebd5856ec520f7d09f208b2278bb754a554"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-fix-dtbo-rules.patch \
"
require linux-raspberrypi.inc
