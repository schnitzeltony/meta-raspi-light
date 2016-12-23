FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.39"

SRCREV = "1a2d9df75eaef078f8c2c22f51d0b315d09c3bac"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.27-rt37.patch", "", d)}  \
"
require linux-raspberrypi.inc
