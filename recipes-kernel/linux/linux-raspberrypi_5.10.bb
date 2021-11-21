FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10.76"

RT_PATCHES = " \
	file://rt/patch-5.10.73-rt54.patch \
    file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
	file://rt/0002-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
"

SRCREV = "bad3872df40dd9a4ba7ff239c17288a7a84a80f9"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;branch=rpi-5.10.y;protocol=https \
    file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
    file://0002-Allow-linking-with-gold-for-build-tests.patch \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "${RT_PATCHES}", "", d)} \
"
require linux-raspberrypi-5.10.inc
