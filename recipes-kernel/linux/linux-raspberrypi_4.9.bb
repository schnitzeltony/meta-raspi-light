FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.40"

SRCREV = "a6d7c7353dfd938f8f5c6d6f33813f2c4b3ff7dc"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.40-rt30.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
