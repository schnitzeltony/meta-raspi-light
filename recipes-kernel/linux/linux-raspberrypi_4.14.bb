FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.91", "4.14.93", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "404fa4287aedbfba0daf3b1b792608405adf5f87", "efdcb6d2b15d216fbccf99969caa5551d3f1c5b9", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
