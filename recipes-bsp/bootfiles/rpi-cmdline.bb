SUMMARY = "cmdline.txt file used to boot the kernel on a Raspberry Pi device"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

COMPATIBLE_MACHINE = "^rpi$"
INHIBIT_DEFAULT_DEPS = "1"
inherit deploy nopackages

CMDLINE_DWC_OTG ?= ""
#CMDLINE_DWC_OTG ?= "dwc_otg.lpm_enable=0"

#CMDLINE_ROOTFS ?= ""
CMDLINE_ROOTFS ?= "root=/dev/mmcblk0p2 rootfstype=ext4 rootwait"

#CMDLINE_SERIAL ?= ""
CMDLINE_SERIAL ?= "console=serial0,115200"

#CMDLINE_LOG_SCREEN ?= ""
CMDLINE_LOG_SCREEN ?= "console=tty1"

# Avoid screen auto switch off
#CMDLINE_KEEP_MONITOR_ON ?= ""
CMDLINE_KEEP_MONITOR_ON ?= "consoleblank=0"

# camera
CMDLINE_CMA ?= ""
#CMDLINE_CMA ?= "cma=64M"

CMDLINE_PITFT ?= ""
#CMDLINE_PITFT ?= "fbcon=map:10 fbcon=font:VGA8x8"

# Add the kernel debugger over console kernel command line option if enabled
CMDLINE_KGDB ?= ""
#CMDLINE_KGDB ?= "kgdboc=serial0,115200"

# Disable rpi logo on boot
CMDLINE_LOGO ?= ""
#CMDLINE_LOGO ?= "logo.nologo"

# verbose
CMDLINE_DEBUG ?= ""
#CMDLINE_DEBUG ?= "debug"

# shut up / boot even faster
CMDLINE_SHUT_UP ?= ""
#CMDLINE_SHUT_UP ?= "quiet"

# Add RNDIS capabilities (must be after rootwait)
# example: 
# CMDLINE_RNDIS = "modules-load=dwc2,g_ether g_ether.host_addr=<some MAC 
# address> g_ether.dev_addr=<some MAC address>"
# if the MAC addresses are omitted, random values will be used
CMDLINE_RNDIS ?= ""

CMDLINE = " \
    ${CMDLINE_DWC_OTG} \
    ${CMDLINE_SERIAL} \
    ${CMDLINE_LOG_SCREEN} \
    ${CMDLINE_KEEP_MONITOR_ON} \
    ${CMDLINE_ROOTFS} \
    ${CMDLINE_CMA} \
    ${CMDLINE_KGDB} \
    ${CMDLINE_LOGO} \
    ${CMDLINE_PITFT} \
    ${CMDLINE_DEBUG} \
    ${CMDLINE_SHUT_UP} \
    ${CMDLINE_RNDIS} \
    "

do_compile() {
    echo "${@' '.join('${CMDLINE}'.split())}" > "${WORKDIR}/cmdline.txt"
}

do_deploy() {
    install -d "${DEPLOYDIR}/bootfiles"
    install -m 0644 "${WORKDIR}/cmdline.txt" "${DEPLOYDIR}/bootfiles"
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bootfiles"

PACKAGE_ARCH = "${MACHINE_ARCH}"
