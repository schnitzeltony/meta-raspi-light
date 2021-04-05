FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

LINUX_VERSION ?= "5.10.25"

RT_PATCHES = " \
	file://rt/patch-5.10.25-rt35.patch \
    file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
	file://rt/0002-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
"

SRCREV = "d1fd8a5727908bb677c003d2ae977e9d935a6f94"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.10.y \
    file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "${RT_PATCHES}", "", d)} \
"
require linux-raspberrypi-5.10.inc
