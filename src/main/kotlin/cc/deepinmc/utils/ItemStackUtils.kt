package cc.deepinmc.utils

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.stream.Collectors

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

class ItemStackBuilder {

    constructor(material: Material, amount: Int, data: Short, itemMeta: ItemMeta?) {
        this.material = material
        this.amount = amount
        this.data = data
        this.itemMeta = itemMeta ?: Bukkit.getItemFactory().getItemMeta(material)!!
    }

    constructor(material: Material) : this(material, 1, 0, null)

    constructor(material: Material, amount: Int) : this(material, amount, 0, null)

    constructor(material: Material, amount: Int, data: Short) : this(material, amount, data, null)

    constructor(itemStack: ItemStack) : this(itemStack.type, itemStack.amount, itemStack.data.data.toShort(), itemStack.itemMeta)

    private var material: Material
    private var data: Short = 0
    private var amount = 1
    private var itemMeta: ItemMeta

    fun amount(amount: Int): ItemStackBuilder {
        this.amount = amount
        return this
    }

    fun durability(durability: Short): ItemStackBuilder {
        this.data = durability
        return this
    }

    fun displayName(displayName: String): ItemStackBuilder {
        this.itemMeta.displayName = displayName
        return this
    }

    fun lore(vararg lore: String): ItemStackBuilder {
        this.itemMeta.lore = lore.toList()
        return this
    }

    fun lore(lore: MutableList<String>, translate: Boolean): ItemStackBuilder {
        this.itemMeta.lore =
                if (translate) lore
                        .stream()
                        .map { s: String -> s.replace("&".toRegex(), "§") }
                        .collect(Collectors.toList())
                else lore
        return this
    }

    fun itemFlag(itemFlags: MutableList<ItemFlag>): ItemStackBuilder {
        for (itemFlag in itemFlags) {
            this.itemMeta.addItemFlags(itemFlag)
        }
        return this
    }

    fun itemFlag(vararg itemFlags: ItemFlag): ItemStackBuilder {
        for (itemFlag in itemFlags) {
            this.itemMeta.addItemFlags(itemFlag)
        }
        return this
    }

    fun build(): ItemStack {
        val itemStack = ItemStack(material, amount, data)
        itemStack.durability = this.data
        itemStack.itemMeta = this.itemMeta
        return itemStack
    }
}