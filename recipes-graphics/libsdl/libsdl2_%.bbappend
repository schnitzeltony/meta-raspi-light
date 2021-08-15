PACKAGECONFIG_GL:rpi = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl kmsdrm', '', d)}"

