FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.27"

SRCREV = "1c8b82bcb72f95d8f9d606326178192a2abc9c9c"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.21-rt31.patch", "", d)}  \
"
require linux-raspberrypi.inc
