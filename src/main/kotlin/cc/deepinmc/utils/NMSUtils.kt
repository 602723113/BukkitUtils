package cc.deepinmc.utils

import org.bukkit.Bukkit

object NMSUtils {
    private val version = Bukkit.getServer().javaClass.`package`.name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]

    @JvmStatic
    fun getNMSClass(nmsClass: String): Class<*> = Class.forName("net.minecraft.server.$version.$nmsClass")
}
