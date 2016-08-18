FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.21"

SRCREV = "9669a50a3a8e4f33b4fe138277bc4407e1eab9b2"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
           file://0001-fix-dtbo-rules.patch \
"
require linux-raspberrypi.inc
