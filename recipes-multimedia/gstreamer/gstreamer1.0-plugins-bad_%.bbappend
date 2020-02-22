PACKAGECONFIG_append_rpi = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gl kms', '', d)} \
"
