# slightly modified to oe-core's default: add 'dri3 xshmfence glamor' for opengl
PACKAGECONFIG_rpi ?= " \
    dri2 udev ${XORG_CRYPTO} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'dri glx dri3 xshmfence glamor', '', d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "xwayland", "", d)} \
    ${@bb.utils.contains("DISTRO_FEATURES", "systemd", "systemd", "", d)} \
"
