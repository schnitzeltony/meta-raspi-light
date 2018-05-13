FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.39", "4.14.39", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "a4b8f1f27a61d6c98a4819a011c0a99f2d7ebfa8", "30eb4e1033a35103f27d60ba609eb466513b5465", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
