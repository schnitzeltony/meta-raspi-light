PACKAGECONFIG_append_rpi = " gallium dri3"
GALLIUMDRIVERS_rpi = "vc4"
DRIDRIVERS_rpi = ""

FILESEXTRAPATHS_prepend_rpi := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-VC4-enable-tile-neon-asm-for-aarch64.patch"
