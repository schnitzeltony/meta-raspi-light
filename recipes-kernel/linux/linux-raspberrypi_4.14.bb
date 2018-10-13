FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.74", "4.14.74", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "a42048c6eee58b1b8d252e30224b7d065615c3fd", "6d27aa156c26977dfd079a7107e31670127d17d3", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
