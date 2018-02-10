FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.80"

SRCREV = "a2f34d45809d8685bca1e91989e35746499ac400"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.76-rt61.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
