FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.9.22"

SRCREV = "72134397d72079a533c8fc742701fdc7f5ae7c5b"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.9.y \
    file://0001-build-arm64-Add-rules-for-.dtbo-files-for-dts-overla.patch \
    file://0002-Avoid-Oops-in-rpi_touchscreen_dsi_probe-by-checking-.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.9.20-rt16.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)}  \
"
require linux-raspberrypi.inc
