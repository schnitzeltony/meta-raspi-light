PACKAGECONFIG_GL_rpi = "${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'gles2', '', d)}"

