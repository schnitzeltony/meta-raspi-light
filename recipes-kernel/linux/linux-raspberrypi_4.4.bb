FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

LINUX_VERSION ?= "4.4.30"

SRCREV = "c1f53f11eda0dbc733e7ecef1c1af0885a3537a3"
SRC_URI = " \
    git://github.com/raspberrypi/linux.git;protocol=git;branch=rpi-4.4.y \
    file://0001-fix-dtbo-rules.patch \
    ${@base_conditional("ENABLE_RPI_RT", "1", "file://patch-4.4.21-rt31.patch", "", d)}  \
"
require linux-raspberrypi.inc
