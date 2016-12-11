FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.37"

SRCREV = "9474a42e2aed646472c99709b2fc7eb1d8fdfeda"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.27-rt37.patch", "", d)}  \
"
require linux-raspberrypi.inc
