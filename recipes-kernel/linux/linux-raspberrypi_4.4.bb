FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.48"

SRCREV = "8bce8113e663f8a6bf473d9ba6b8736deead63a4"
SRC_URI = " \
    git://github.com/schnitzeltony/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.47-rt59.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
