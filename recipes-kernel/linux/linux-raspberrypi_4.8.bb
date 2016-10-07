FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.8.0"

SRCREV = "2fb843e0e50b2330e76acea03ff63a5b2b1e411f"
SRC_URI = "git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.8.y \
           file://0001-fix-dtbo-rules.patch \
           file://0001-media-bcm2835-camera-fix-compilation-warning-werror.patch \
"
require linux-raspberrypi.inc
