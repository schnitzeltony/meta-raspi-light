FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.59", "4.14.59", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "65f3df54394dcf38ef426b1b02f7bb18f9de9ccd", "ad1d85ad2a7dea6a17e6d3cc32adf6ce0ea844c0", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
