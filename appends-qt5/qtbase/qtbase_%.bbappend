# since Qt5.8 we need gbm
PACKAGECONFIG_GL:append:rpi = " \
    gbm \
    kms \
    eglfs \
"
