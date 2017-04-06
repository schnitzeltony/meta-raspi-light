FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.20"

SRCREV = "3f53e7886737a975e3fe76bc8ae6cc78f33c8cf8"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.20-rt16.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
