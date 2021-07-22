# RaspberryPi BSP default settings

IMAGE_CLASSES += "sdcard_image-rpi"

MACHINE_EXTRA_RRECOMMENDS += "udev-rules-rpi udev-rules-rpi-gpio udev-rules-rpi-i2c"
MACHINE_EXTRA_RRECOMMENDS += "${@bb.utils.contains('BBFILE_COLLECTIONS', 'mortsgna-layer', '', 'udev-rules-udisks-rpi', d)}"
