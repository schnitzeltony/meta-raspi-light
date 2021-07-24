do_install_append_rpi () {
    echo '/dev/mmcblk0p1       /boot                vfat       defaults              0  2' >> ${D}${sysconfdir}/fstab
}
