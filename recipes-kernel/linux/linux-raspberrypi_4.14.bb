FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.81", "4.14.82", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "acf578d07d57480674d5361df9171fe9528765cb", "2efa7450a8f408084d40b28cfa3fa75cf488d473", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
