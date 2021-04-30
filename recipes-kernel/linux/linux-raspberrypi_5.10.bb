FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10.33"

RT_PATCHES = " \
	file://rt/patch-5.10.30-rt37.patch \
    file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
	file://rt/0002-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
"

SRCREV = "96110e96f1a82e236afb9a248258f1ef917766e9"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.10.y \
    file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "${RT_PATCHES}", "", d)} \
"
require linux-raspberrypi-5.10.inc
