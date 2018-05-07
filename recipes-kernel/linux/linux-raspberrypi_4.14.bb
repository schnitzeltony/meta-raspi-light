FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "4.14.34", "4.14.34", d)}"
SRCREV = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "32f5076d836518eaf2e7b2caa2e6ee196d27210b", "b6a574b3e3170b4dd2f297ba7f4dbc36f759983a", d)}"
BRANCH = "${@oe.utils.conditional("ENABLE_RPI_RT", "1", "rpi-4.14.y-rt", "rpi-4.14.y", d)}"

SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=${BRANCH} \
    file://0001-menuconfig-check-lxdiaglog.sh-Allow-specification-of.patch \
"
require linux-raspberrypi.inc
