FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.35"

SRCREV = "0c929b3c94636bd4c0cd62181145c733b3d19c0a"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.35-rt25.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
