FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.32"

SRCREV = "8aa56d2b4ad2c3ed72a5732eafd85e4e55013eb1"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.21-rt31.patch", "", d)}  \
"
require linux-raspberrypi.inc
