FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

LINUX_VERSION ?= "4.19.32"

SRCREV = "d65a0f76d3adcf86a6f5c614c68edb3aeb3b8590"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.19.y \
    ${@oe.utils.conditional("ENABLE_RPI_RT", "1", "file://patch-4.19.31-rt18.patch file://FIQ_PREEMPT_RT_on_raspi.patch", "", d)} \
"
require linux-raspberrypi.inc
