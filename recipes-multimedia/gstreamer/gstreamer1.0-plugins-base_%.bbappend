PACKAGECONFIG_append_rpi = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'opengl gles2 egl gbm', '', d)} \
"
