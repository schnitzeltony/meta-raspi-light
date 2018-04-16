SUMMARY = "config.txt file for the Raspberry Pi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

COMPATIBLE_MACHINE = "raspberrypi"

SRC_URI = "file://config.txt"

S = "${WORKDIR}/git"

PR = "r5"

INHIBIT_DEFAULT_DEPS = "1"

inherit deploy siteinfo nopackages

do_deploy() {
    install -d ${DEPLOYDIR}/bcm2835-bootfiles
    cp ${WORKDIR}/config.txt ${DEPLOYDIR}/bcm2835-bootfiles/

    conf_file='${DEPLOYDIR}/bcm2835-bootfiles/config.txt'

    if [ "${SITEINFO_BITS}" = "64" ]; then
        echo '# Force 64 bit - see: https://github.com/raspberrypi/firmware/issues/1193' >> "$conf_file"
        echo 'arm_64bit=1' >> "$conf_file"
        echo >> "$conf_file"
    fi

    cat >> "$conf_file" <<EOF
[pi4]
max_framebuffers=2
dtoverlay=vc4-fkms-v3d,noaudio=on
#dtoverlay=vc4-kms-v3d-pi4,noaudio=on

[all]
dtoverlay=vc4-kms-v3d,noaudio=on
EOF
}

addtask deploy before do_package after do_install
do_deploy[dirs] += "${DEPLOYDIR}/bcm2835-bootfiles"

PACKAGE_ARCH = "${MACHINE_ARCH}"
