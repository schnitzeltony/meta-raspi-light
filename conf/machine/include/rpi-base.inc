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
KERNEL_DEVICETREE = " \
    overlays/act-led-overlay.dtb \
    overlays/adafruit18-overlay.dtb \
    overlays/adau1977-adc-overlay.dtb \
    overlays/adau7002-simple-overlay.dtb \
    overlays/ads1015-overlay.dtb \
    overlays/ads1115-overlay.dtb \
    overlays/ads7846-overlay.dtb \
    overlays/adv728x-m-overlay.dtb \
    overlays/adv7282m-overlay.dtb \
    overlays/akkordion-iqdacplus-overlay.dtb \
    overlays/allo-boss-dac-pcm512x-audio-overlay.dtb \
    overlays/allo-digione-overlay.dtb \
    overlays/allo-katana-dac-audio-overlay.dtb \
    overlays/allo-piano-dac-pcm512x-audio-overlay.dtb \
    overlays/allo-piano-dac-plus-pcm512x-audio-overlay.dtb \
    overlays/anyspi-overlay.dtb \
    overlays/apds9960-overlay.dtb \
    overlays/applepi-dac-overlay.dtb \
    overlays/at86rf233-overlay.dtb \
    overlays/audioinjector-addons-overlay.dtb \
    overlays/audioinjector-isolated-soundcard-overlay.dtb \
    overlays/audioinjector-ultra-overlay.dtb \
    overlays/audioinjector-wm8731-audio-overlay.dtb \
    overlays/audiosense-pi-overlay.dtb \
    overlays/audremap-overlay.dtb \
    overlays/balena-fin-overlay.dtb \
    overlays/cma-overlay.dtb \
    overlays/dht11-overlay.dtb \
    overlays/dionaudio-loco-overlay.dtb \
    overlays/dionaudio-loco-v2-overlay.dtb \
    overlays/disable-bt-overlay.dtb \
    overlays/disable-wifi-overlay.dtb \
    overlays/dpi18-overlay.dtb \
    overlays/dpi24-overlay.dtb \
    overlays/draws-overlay.dtb \
    overlays/dwc2-overlay.dtb \
    overlays/dwc-otg-overlay.dtb \
    overlays/enc28j60-overlay.dtb \
    overlays/enc28j60-spi2-overlay.dtb \
    overlays/exc3000-overlay.dtb \
    overlays/fe-pi-audio-overlay.dtb \
    overlays/goodix-overlay.dtb \
    overlays/googlevoicehat-soundcard-overlay.dtb \
    overlays/gpio-fan-overlay.dtb \
    overlays/gpio-ir-overlay.dtb \
    overlays/gpio-ir-tx-overlay.dtb \
    overlays/gpio-key-overlay.dtb \
    overlays/gpio-no-bank0-irq-overlay.dtb \
    overlays/gpio-no-irq-overlay.dtb \
    overlays/gpio-poweroff-overlay.dtb \
    overlays/gpio-shutdown-overlay.dtb \
    overlays/hd44780-lcd-overlay.dtb \
    overlays/hdmi-backlight-hwhack-gpio-overlay.dtb \
    overlays/hifiberry-amp-overlay.dtb \
    overlays/hifiberry-dac-overlay.dtb \
    overlays/hifiberry-dacplusadc-overlay.dtb \
    overlays/hifiberry-dacplusadcpro-overlay.dtb \
    overlays/hifiberry-dacplusdsp-overlay.dtb \
    overlays/hifiberry-dacplushd-overlay.dtb \
    overlays/hifiberry-dacplus-overlay.dtb \
    overlays/hifiberry-digi-overlay.dtb \
    overlays/hifiberry-digi-pro-overlay.dtb \
    overlays/highperi-overlay.dtb \
    overlays/hy28a-overlay.dtb \
    overlays/hy28b-2017-overlay.dtb \
    overlays/hy28b-overlay.dtb \
    overlays/i2c0-overlay.dtb \
    overlays/i2c1-overlay.dtb \
    overlays/i2c3-overlay.dtb \
    overlays/i2c4-overlay.dtb \
    overlays/i2c5-overlay.dtb \
    overlays/i2c6-overlay.dtb \
    overlays/i2c-bcm2708-overlay.dtb \
    overlays/i2c-gpio-overlay.dtb \
    overlays/i2c-mux-overlay.dtb \
    overlays/i2c-pwm-pca9685a-overlay.dtb \
    overlays/i2c-rtc-gpio-overlay.dtb \
    overlays/i2c-rtc-overlay.dtb \
    overlays/i2c-sensor-overlay.dtb \
    overlays/i2s-gpio28-31-overlay.dtb \
    overlays/ilitek251x-overlay.dtb \
    overlays/imx219-overlay.dtb \
    overlays/imx290-overlay.dtb \
    overlays/imx477-overlay.dtb \
    overlays/iqaudio-codec-overlay.dtb \
    overlays/iqaudio-dac-overlay.dtb \
    overlays/iqaudio-dacplus-overlay.dtb \
    overlays/iqaudio-digi-wm8804-audio-overlay.dtb \
    overlays/irs1125-overlay.dtb \
    overlays/i-sabre-q2m-overlay.dtb \
    overlays/jedec-spi-nor-overlay.dtb \
    overlays/justboom-both-overlay.dtb \
    overlays/justboom-dac-overlay.dtb \
    overlays/justboom-digi-overlay.dtb \
    overlays/ltc294x-overlay.dtb \
    overlays/max98357a-overlay.dtb \
    overlays/maxtherm-overlay.dtb \
    overlays/mbed-dac-overlay.dtb \
    overlays/mcp23s17-overlay.dtb \
    overlays/mcp342x-overlay.dtb \
    overlays/mcp2515-can0-overlay.dtb \
    overlays/mcp2515-can1-overlay.dtb \
    overlays/mcp3008-overlay.dtb \
    overlays/mcp3202-overlay.dtb \
    overlays/mcp23017-overlay.dtb \
    overlays/media-center-overlay.dtb \
    overlays/merus-amp-overlay.dtb \
    overlays/midi-uart0-overlay.dtb \
    overlays/midi-uart1-overlay.dtb \
    overlays/miniuart-bt-overlay.dtb \
    overlays/mmc-overlay.dtb \
    overlays/mpu6050-overlay.dtb \
    overlays/mz61581-overlay.dtb \
    overlays/ov5647-overlay.dtb \
    overlays/ov7251-overlay.dtb \
    overlays/ov9281-overlay.dtb \
    overlays/overlay_map.dtb \
    overlays/papirus-overlay.dtb \
    overlays/pca953x-overlay.dtb \
    overlays/pibell-overlay.dtb \
    overlays/pifacedigital-overlay.dtb \
    overlays/piglow-overlay.dtb \
    overlays/piscreen2r-overlay.dtb \
    overlays/piscreen-overlay.dtb \
    overlays/pisound-overlay.dtb \
    overlays/pitft22-overlay.dtb \
    overlays/pitft28-capacitive-overlay.dtb \
    overlays/pitft28-resistive-overlay.dtb \
    overlays/pitft35-resistive-overlay.dtb \
    overlays/pps-gpio-overlay.dtb \
    overlays/pwm-2chan-overlay.dtb \
    overlays/pwm-ir-tx-overlay.dtb \
    overlays/pwm-overlay.dtb \
    overlays/qca7000-overlay.dtb \
    overlays/rotary-encoder-overlay.dtb \
    overlays/rpi-backlight-overlay.dtb \
    overlays/rpi-cirrus-wm5102-overlay.dtb \
    overlays/rpi-dac-overlay.dtb \
    overlays/rpi-display-overlay.dtb \
    overlays/rpi-ft5406-overlay.dtb \
    overlays/rpi-poe-overlay.dtb \
    overlays/rpi-proto-overlay.dtb \
    overlays/rpi-sense-overlay.dtb \
    overlays/rpi-tv-overlay.dtb \
    overlays/rpivid-v4l2-overlay.dtb \
    overlays/rra-digidac1-wm8741-audio-overlay.dtb \
    overlays/sainsmart18-overlay.dtb \
    overlays/sc16is750-i2c-overlay.dtb \
    overlays/sc16is752-i2c-overlay.dtb \
    overlays/sc16is752-spi0-overlay.dtb \
    overlays/sc16is752-spi1-overlay.dtb \
    overlays/sdhost-overlay.dtb \
    overlays/sdio-overlay.dtb \
    overlays/sdtweak-overlay.dtb \
    overlays/sh1106-spi-overlay.dtb \
    overlays/smi-dev-overlay.dtb \
    overlays/smi-nand-overlay.dtb \
    overlays/smi-overlay.dtb \
    overlays/spi0-1cs-overlay.dtb \
    overlays/spi0-2cs-overlay.dtb \
    overlays/spi1-1cs-overlay.dtb \
    overlays/spi1-2cs-overlay.dtb \
    overlays/spi1-3cs-overlay.dtb \
    overlays/spi2-1cs-overlay.dtb \
    overlays/spi2-2cs-overlay.dtb \
    overlays/spi2-3cs-overlay.dtb \
    overlays/spi3-1cs-overlay.dtb \
    overlays/spi3-2cs-overlay.dtb \
    overlays/spi4-1cs-overlay.dtb \
    overlays/spi4-2cs-overlay.dtb \
    overlays/spi5-1cs-overlay.dtb \
    overlays/spi5-2cs-overlay.dtb \
    overlays/spi6-1cs-overlay.dtb \
    overlays/spi6-2cs-overlay.dtb \
    overlays/spi-gpio35-39-overlay.dtb \
    overlays/spi-gpio40-45-overlay.dtb \
    overlays/spi-rtc-overlay.dtb \
    overlays/ssd1306-overlay.dtb \
    overlays/ssd1306-spi-overlay.dtb \
    overlays/ssd1351-spi-overlay.dtb \
    overlays/superaudioboard-overlay.dtb \
    overlays/sx150x-overlay.dtb \
    overlays/tc358743-audio-overlay.dtb \
    overlays/tc358743-overlay.dtb \
    overlays/tinylcd35-overlay.dtb \
    overlays/tpm-slb9670-overlay.dtb \
    overlays/uart0-overlay.dtb \
    overlays/uart1-overlay.dtb \
    overlays/uart2-overlay.dtb \
    overlays/uart3-overlay.dtb \
    overlays/uart4-overlay.dtb \
    overlays/uart5-overlay.dtb \
    overlays/udrc-overlay.dtb \
    overlays/upstream-overlay.dtb \
    overlays/upstream-pi4-overlay.dtb \
    overlays/vc4-fkms-v3d-overlay.dtb \
    overlays/vc4-kms-kippah-7inch-overlay.dtb \
    overlays/vc4-kms-v3d-overlay.dtb \
    overlays/vc4-kms-v3d-pi4-overlay.dtb \
    overlays/vga666-overlay.dtb \
    overlays/w1-gpio-overlay.dtb \
    overlays/w1-gpio-pullup-overlay.dtb \
    overlays/w5500-overlay.dtb \
    overlays/wittypi-overlay.dtb \
"

KERNEL_DEVICETREE_append_arm = " \
    bcm2708-rpi-b.dtb \
    bcm2708-rpi-b-plus.dtb \
    bcm2708-rpi-b-rev1.dtb \
    bcm2708-rpi-cm.dtb \
    bcm2708-rpi-zero.dtb \
    bcm2708-rpi-zero-w.dtb \
    bcm2709-rpi-2-b.dtb \
    bcm2710-rpi-2-b.dtb \
    bcm2710-rpi-3-b.dtb \
    bcm2710-rpi-3-b-plus.dtb \
    bcm2710-rpi-cm3.dtb \
    bcm2711-rpi-4-b.dtb \
    bcm2711-rpi-cm4.dtb \
    bcm2835-rpi-a.dtb \
    bcm2835-rpi-a-plus.dtb \
    bcm2835-rpi-b.dtb \
    bcm2835-rpi-b-plus.dtb \
    bcm2835-rpi-b-rev2.dtb \
    bcm2835-rpi-cm1-io1.dtb \
    bcm2835-rpi-zero.dtb \
    bcm2835-rpi-zero-w.dtb \
    bcm2836-rpi-2-b.dtb \
    bcm2837-rpi-3-a-plus.dtb \
    bcm2837-rpi-3-b.dtb \
    bcm2837-rpi-3-b-plus.dtb \
    bcm2837-rpi-cm3-io3.dtb \
"

KERNEL_DEVICETREE_append_aarch64 = " \
    broadcom/bcm2710-rpi-2-b.dtb \
    broadcom/bcm2710-rpi-3-b.dtb \
    broadcom/bcm2710-rpi-3-b-plus.dtb \
    broadcom/bcm2710-rpi-cm3.dtb \
    broadcom/bcm2711-rpi-4-b.dtb \
    broadcom/bcm2837-rpi-3-a-plus.dtb \
    broadcom/bcm2837-rpi-3-b.dtb \
    broadcom/bcm2837-rpi-3-b-plus.dtb \
    broadcom/bcm2837-rpi-cm3-io3.dtb \
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
