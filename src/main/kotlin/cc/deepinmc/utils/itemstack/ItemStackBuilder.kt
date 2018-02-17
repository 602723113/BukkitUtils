package cc.deepinmc.utils.itemstack

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.function.Consumer
import java.util.stream.Collectors

/**
 * Easy to build a itemstack
 *
 * @author Zoyn
 * @since 2018-02-10
 */
class ItemStackBuilder {

    private var material: Material
    private var data: Short = 0
    private var amount = 1
    private var unbreakable = false
    private var itemMeta: ItemMeta

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

    fun amount(amount: Int): ItemStackBuilder {
        this.amount = amount
        return this
    }

    fun durability(durability: Short): ItemStackBuilder {
        this.data = (this.material.maxDurability - durability).toShort()
        return this
    }

    fun displayName(displayName: String): ItemStackBuilder {
        this.itemMeta.displayName = displayName
        return this
    }

    fun lore(vararg lore: String, translate: Boolean = true): ItemStackBuilder {
        this.itemMeta.lore =
                if (translate)
                    lore.toList()
                            .stream()
                            .map { s: String -> s.replace("&".toRegex(), "§") }
                            .collect(Collectors.toList())
                else lore.toList()
        return this
    }

    fun lore(lore: MutableList<String>, translate: Boolean = true): ItemStackBuilder {
        this.itemMeta.lore =
                if (translate) lore
                        .stream()
                        .map { s: String -> s.replace("&".toRegex(), "§") }
                        .collect(Collectors.toList())
                else lore
        return this
    }

    fun itemFlag(itemFlags: MutableList<ItemFlag>): ItemStackBuilder {
        itemFlags.forEach(Consumer { itemFlag -> this.itemMeta.addItemFlags(itemFlag) })
        return this
    }

    fun itemFlag(vararg itemFlags: ItemFlag): ItemStackBuilder {
        itemFlags.forEach { itemFlag -> this.itemMeta.addItemFlags(itemFlag) }
        return this
    }

    fun enchant(enchantment: Enchantment, level: Int, ignoreSecurity: Boolean): ItemStackBuilder {
        itemMeta.addEnchant(enchantment, level, ignoreSecurity)
        return this
    }

    fun enchant(enchantments: MutableMap<Enchantment, Int>, ignoreSecurity: Boolean): ItemStackBuilder {
        enchantments.forEach { enchantment, level -> itemMeta.addEnchant(enchantment, level, ignoreSecurity) }
        return this
    }

    fun unbreakable(breakable: Boolean): ItemStackBuilder {
        this.unbreakable = breakable
        return this
    }

    fun build(): ItemStack {
        val itemStack = ItemStack(material, amount, data)
        itemStack.durability = this.data
        itemStack.setUnbreakable(unbreakable)
        itemStack.itemMeta = this.itemMeta
        return itemStack
    }
}