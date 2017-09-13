FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.50"

SRCREV = "12bafdab8214b23223603b75845c5c88f7444b6f"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.47-rt37.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
