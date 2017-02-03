SUMMARY = "Remote control and meeting solution"
HOMEPAGE = "https://www.teamviewer.com/"
LICENSE = "TeamViewer"
LIC_FILES_CHKSUM = "file://opt/teamviewer/doc/License.txt;md5=72c21b75397df780de34b238c61c86e4"

SRC_URI = "http://download.teamviewer.com/download/linux/version_11x/teamviewer-host_armhf.deb"
SRC_URI[md5sum] = "77e9bcf2874090bf2288dacf6b6cb901"
SRC_URI[sha256sum] = "ba48a3ffabbf233091aba62333e6c9a1c16be4838e6b506596f181384dac2a2d"

S = "${WORKDIR}"

COMPATIBLE_HOST = 'arm.*-linux'

DEPENDS = " \
    qtbase \
    qtx11extras \
    qtwebkit \
"

inherit distro_features_check systemd gtk-icon-cache

REQUIRED_DISTRO_FEATURES = "x11"

do_install() {
    cp -r ${WORKDIR}/opt ${D}
    cp -r ${WORKDIR}/usr ${D}
    cp -r ${WORKDIR}/var ${D}

    # libdepend is some x86 tool
    rm -f ${D}/opt/teamviewer/tv_bin/script/libdepend

    # systemd
    install -d ${D}${systemd_system_unitdir}
    mv ${D}/opt/teamviewer/tv_bin/script/teamviewerd.service ${D}${systemd_system_unitdir}/teamviewerd.service

    # desktop+icon
    install -d ${D}${datadir}/applications
    ln -sf /opt/teamviewer/tv_bin/desktop/com.teamviewer.teamviewer-host.desktop ${D}${datadir}/applications/teamviewer-host.desktop
    install -d ${D}${datadir}/icons/hicolor/48x48/apps
    ln -sf /opt/teamviewer/tv_bin/desktop/teamviewer.png ${D}${datadir}/icons/hicolor/48x48/apps/teamviewer.png
}

SYSTEMD_SERVICE_${PN} = "teamviewerd.service"

FILES_${PN} += " \
    opt \
"

RDEPENDS_${PN} += "bash"

INSANE_SKIP_${PN} = "already-stripped ldflags"
