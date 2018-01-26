package cc.deepinmc.utils

import org.bukkit.inventory.ItemStack

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