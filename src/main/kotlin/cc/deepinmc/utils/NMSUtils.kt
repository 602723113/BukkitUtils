package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

private val version = Bukkit.getServer().javaClass.`package`.name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]
private val entityPlayerGetHandle = getNMSClass("EntityPlayer").getMethod("getHandle")
private val asNMSCopy = getNMSClass("CraftItemStack").getMethod("asNMSCopy", ItemStack::class.java)
private val playerConnectionSendPacket = getNMSClass("PlayerConnection").getMethod("sendPacket", getNMSClass("Packet"))

fun getNMSClass(nmsClass: String): Class<*> = Class.forName("net.minecraft.server.$version.$nmsClass")

fun getNMSPlayer(player: Player): Any = entityPlayerGetHandle.invoke(player)

fun getNMSItem(itemStack: ItemStack): Any = asNMSCopy.invoke(itemStack)

fun sendPacket(player: Player, packet: Any) {
    playerConnectionSendPacket.invoke(getNMSClass("EntityPlayer").getField("playerConnection").get(getNMSPlayer(player)), packet)
}

