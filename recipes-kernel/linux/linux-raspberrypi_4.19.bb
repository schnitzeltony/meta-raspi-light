FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19.126"

RT_PATCHES = " \
	file://rt/patch-4.19.124-rt53.patch \
    file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
	file://rt/0002-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
"

SRCREV = "f6b3ac28f0a9137d4c24c0b8832e693bbd16f5b7"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "${RT_PATCHES}", "", d)} \
"
require linux-raspberrypi-4.19.inc
