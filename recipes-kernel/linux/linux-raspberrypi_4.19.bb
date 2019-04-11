FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19.34"

SRCREV = "ab8652c03fa081b27de7e28a74c2536cb2aa3e5b"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "file://rt/patch-4.19.31-rt18.patch file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch", "", d)} \
"
require linux-raspberrypi.inc
