package cc.deepinmc.utils

import org.bukkit.entity.Player

object PingUtils {

    @JvmStatic
    fun getPing(player: Player): Int {
        val entityPlayer = player.javaClass.getMethod("getHandle").invoke(player)
        return NMSUtils.getNMSClass("EntityPlayer").getField("ping").get(entityPlayer) as Int
    }
}
