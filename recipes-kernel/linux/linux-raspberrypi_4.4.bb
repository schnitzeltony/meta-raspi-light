FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.32"

SRCREV = "7372539d8fcb1a4f6087e47f89e99a3acb4a4f44"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.21-rt31.patch", "", d)}  \
"
require linux-raspberrypi.inc
