FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "5.4.64"

RT_PATCHES = " \
	file://rt/patch-5.4.61-rt37.patch \
    file://rt/0001-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
	file://rt/0002-usb-dwc_otg-fix-system-lockup-when-interrupts-are-th.patch \
"

SRCREV = "65caf603f3b1c43f4c92939f7fbb7149e054f486"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-5.4.y \
    file://0001-menuconfig-mconf-cfg-Allow-specification-of-ncurses-.patch \
    file://0002-Revert-selftests-bpf-Skip-perf-hw-events-test-if-the.patch \
    file://0003-perf-cs-etm-Move-definition-of-traceid_list-global-v.patch \
    file://0004-Revert-writeback-Fix-sync-livelock-due-to-b_dirty_ti.patch \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "${RT_PATCHES}", "", d)} \
"
require linux-raspberrypi-5.4.inc
