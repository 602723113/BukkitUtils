package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Method

object NMSUtils {

    private val version = Bukkit.getServer().javaClass.`package`.name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[3]
    private var entityPlayerGetHandle: Method
    private var asNMSCopy: Method

    init {
        entityPlayerGetHandle = getNMSClass("EntityPlayer").getMethod("getHandle")
        asNMSCopy = getNMSClass("CraftItemStack").getMethod("asNMSCopy", ItemStack::class.java)
    }

    @JvmStatic
    fun getNMSClass(nmsClass: String): Class<*> = Class.forName("net.minecraft.server.$version.$nmsClass")

    @JvmStatic
    fun getNMSPlayer(player: Player): Any = entityPlayerGetHandle.invoke(player)

    @JvmStatic
    fun getNMSItem(itemStack: ItemStack): Any = asNMSCopy.invoke(itemStack)
}
