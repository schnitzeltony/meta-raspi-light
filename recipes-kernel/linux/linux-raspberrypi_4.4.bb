FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.35"

SRCREV = "c7703bfa8dac59ff6cf5b00dac6d644f87620e8d"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.27-rt37.patch", "", d)}  \
"
require linux-raspberrypi.inc
