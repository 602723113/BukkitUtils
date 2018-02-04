package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

private val version = Bukkit.getServer().javaClass.`package`.name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]
private val craftPlayerGetHandle = getOBCClass("entity.CraftPlayer").getMethod("getHandle")
private val asNMSCopy = getOBCClass("inventory.CraftItemStack").getMethod("asNMSCopy", ItemStack::class.java)
private val playerConnectionSendPacket = getNMSClass("PlayerConnection").getMethod("sendPacket", getNMSClass("Packet"))

fun getOBCClass(obcClass: String): Class<*> = Class.forName("org.bukkit.craftbukkit.$version.$obcClass")

fun getNMSClass(nmsClass: String): Class<*> = Class.forName("net.minecraft.server.$version.$nmsClass")

fun getNMSPlayer(player: Player): Any = craftPlayerGetHandle.invoke(player)

fun getNMSItem(itemStack: ItemStack): Any = asNMSCopy.invoke(itemStack, itemStack)

fun sendPacket(player: Player, packet: Any) {
    playerConnectionSendPacket.invoke(getNMSClass("EntityPlayer").getField("playerConnection").get(getNMSPlayer(player)), packet)
}

