FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.79"

SRCREV = "17b06b5ef4c3d9c78524e5e23ae4c4f8e3d74aa1"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.76-rt61.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
