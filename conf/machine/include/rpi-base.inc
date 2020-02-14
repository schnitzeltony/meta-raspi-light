include conf/machine/include/rpi-default-settings.inc
include conf/machine/include/rpi-default-versions.inc
include conf/machine/include/rpi-default-providers.inc

SOC_FAMILY = "rpi"
include conf/machine/include/soc-family.inc

IMAGE_FSTYPES ?= "ext4 rpi-sdimg"

XSERVER = " \
    xserver-xorg \
    xserver-xorg-extension-glx \
    xf86-video-modesetting \
"

# we should automatize this (+machine specific blacklist)
KERNEL_DEVICETREE ?= " \
    bcm2708-rpi-zero-w.dtb \
    bcm2708-rpi-b.dtb \
    bcm2708-rpi-b-plus.dtb \
    bcm2709-rpi-2-b.dtb \
    bcm2710-rpi-3-b.dtb \
    bcm2710-rpi-3-b-plus.dtb \
    bcm2711-rpi-4-b.dtb \
    bcm2708-rpi-cm.dtb \
    bcm2710-rpi-cm3.dtb \
    \
    overlays/at86rf233.dtbo \
    overlays/audioinjector-ultra.dtbo \
    overlays/audioinjector-wm8731-audio.dtbo \
    overlays/dwc2.dtbo \
    overlays/hifiberry-amp.dtbo \
    overlays/hifiberry-dac.dtbo \
    overlays/hifiberry-dacplusadc.dtbo \
    overlays/hifiberry-dacplusadcpro.dtbo \
    overlays/hifiberry-dacplusdsp.dtbo \
    overlays/hifiberry-dacplus.dtbo \
    overlays/hifiberry-digi.dtbo \
    overlays/hifiberry-digi-pro.dtbo \
    overlays/i2c-rtc.dtbo \
    overlays/iqaudio-codec.dtbo \
    overlays/iqaudio-dac.dtbo \
    overlays/iqaudio-dacplus.dtbo \
    overlays/iqaudio-digi-wm8804-audio.dtbo \
    overlays/justboom-dac.dtbo \
    overlays/justboom-digi.dtbo \
    overlays/midi-uart0.dtbo \
    overlays/midi-uart1.dtbo \
    overlays/pi3-disable-bt.dtbo \
    overlays/pi3-disable-wifi.dtbo \
    overlays/pi3-miniuart-bt.dtbo \
    overlays/piscreen.dtbo \
    overlays/piscreen2r.dtbo \
    overlays/pisound.dtbo \
    overlays/pitft22.dtbo \
    overlays/pitft28-capacitive.dtbo \
    overlays/pitft28-resistive.dtbo \
    overlays/pitft35-resistive.dtbo \
    overlays/pps-gpio.dtbo \
    overlays/rpi-ft5406.dtbo \
    overlays/vc4-fkms-v3d.dtbo \
    overlays/vc4-kms-v3d.dtbo \
    overlays/w1-gpio.dtbo \
    overlays/w1-gpio-pullup.dtbo \
"
KERNEL_IMAGETYPE ?= "zImage"

MACHINE_FEATURES += "apm usbhost keyboard vfat ext2 screen touchscreen alsa bluetooth wifi sdio"

# Raspberry Pi has no hardware clock
MACHINE_FEATURES_BACKFILL_CONSIDERED = "rtc"

MACHINE_EXTRA_RRECOMMENDS += " kernel-modules"

# Set Raspberrypi splash image
SPLASH = "psplash-raspberrypi"

IMAGE_BOOT_FILES ?= "bcm2835-bootfiles/* ${KERNEL_IMAGETYPE};${SDIMG_KERNELIMAGE}"

# The kernel image is installed into the FAT32 boot partition and does not need
# to also be installed into the rootfs.
RDEPENDS_${KERNEL_PACKAGE_NAME}-base = ""
