# since Qt5.8 we need gbm
PACKAGECONFIG_GL_append_rpi = " \
    gbm \
    kms \
    eglfs \
"
