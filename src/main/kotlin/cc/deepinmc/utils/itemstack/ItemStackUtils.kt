package cc.deepinmc.utils.itemstack

import cc.deepinmc.utils.getNMSClass
import cc.deepinmc.utils.getNMSItem
import cc.deepinmc.utils.reflect.getConstructor
import cc.deepinmc.utils.reflect.getMethod
import org.bukkit.inventory.ItemStack

fun ItemStack.setUnbreakable(breakable: Boolean) {
    val nmsItem = getNMSItem(this)
    val nbt = nmsItem.javaClass.getMethod("getTag").invoke(nmsItem) ?: getConstructor(getNMSClass("NBTTagCompound"))?.newInstance()
    val set = getMethod(getNMSClass("NBTTagCompound"), "set", String::class.java, getNMSClass("NBTBase"))
    val obj = set?.invoke(nbt, "Unbreakable", getConstructor(getNMSClass("NBTTagByte"))?.newInstance(if (breakable) 1.toByte() else 0.toByte()))
    nmsItem.javaClass.getMethod("setTag", getNMSClass("NBTTagCompound")).invoke(nmsItem, obj)
}

fun getItemDisplayName(itemStack: ItemStack): String? = itemStack.itemMeta.displayName

fun getItemLore(itemStack: ItemStack?): MutableList<String>? = itemStack?.itemMeta?.lore

fun setItemDisplayName(itemStack: ItemStack, displayName: String): ItemStack {
    val itemMeta = itemStack.itemMeta
    itemMeta.displayName = displayName.replace("&".toRegex(), "§")
    itemStack.itemMeta = itemMeta
    return itemStack
}

fun setItemLore(itemStack: ItemStack, lore: MutableList<String>): ItemStack {
    val itemMeta = itemStack.itemMeta
    itemMeta.lore = lore
    itemStack.itemMeta = itemMeta
    return itemStack
}

fun hasDisplayName(itemStack: ItemStack): Boolean? = itemStack.itemMeta?.hasDisplayName()

fun hasLore(itemStack: ItemStack): Boolean? = itemStack.itemMeta?.hasLore()

