package cc.deepinmc.utils

import cc.deepinmc.utils.NMSUtils.getNMSClass
import cc.deepinmc.utils.NMSUtils.getNMSPlayer
import org.bukkit.entity.Player

fun getPing(player: Player): Int = getNMSClass("EntityPlayer").getField("ping").get(getNMSPlayer(player)) as Int

