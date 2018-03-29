FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.27", "4.14.30", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "06ca0b8186703e0577c7755bee085c0bc79e6360", "9d2ad143e40c38d34be86578840499a976c0a5b0", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
"
require linux-raspberrypi.inc
