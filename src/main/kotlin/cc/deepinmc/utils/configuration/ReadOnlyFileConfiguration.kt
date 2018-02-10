package cc.deepinmc.utils.configuration

import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector
import java.io.File
import java.util.stream.Collectors

/**
 * Represent a only read file configuration
 *
 * @author Zoyn
 * @since 2018-02-10
 */
class ReadOnlyFileConfiguration() {

    private lateinit var config: FileConfiguration

    constructor(config: FileConfiguration) : this() {
        this.config = config
    }

    constructor(file: File) : this() {
        this.config = YamlConfiguration.loadConfiguration(file)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean =
            if (config.contains(key) && config.isBoolean(key)) config.getBoolean(key) else defaultValue

    fun getInt(key: String, defaultValue: Int): Int =
            if (config.contains(key) && config.isInt(key)) config.getInt(key) else defaultValue

    fun getDouble(key: String, defaultValue: Double): Double =
            if (config.contains(key) && config.isDouble(key)) config.getDouble(key) else defaultValue

    fun getLong(key: String, defaultValue: Long): Long =
            if (config.contains(key) && config.isLong(key)) config.getLong(key) else defaultValue

    fun getItemStack(key: String, defaultValue: ItemStack): ItemStack =
            if (config.contains(key) && config.isItemStack(key)) config.getItemStack(key) else defaultValue

    fun getColor(key: String, defaultValue: Color): Color =
            if (config.contains(key) && config.isColor(key)) config.getColor(key) else defaultValue

    fun getLocation(key: String, defaultValue: Location): Location =
            if (config.contains(key) && config.isLocation(key)) config.getLocation(key) else defaultValue

    fun getVector(key: String, defaultValue: Vector): Vector =
            if (config.contains(key) && config.isVector(key)) config.getVector(key) else defaultValue

    fun getString(key: String, defaultValue: String): String =
            if (config.contains(key) && config.isString(key)) config.getString(key) else defaultValue

    fun getString(key: String, defaultValue: String, translate: Boolean = true): String {
        return if (config.contains(key) && config.isString(key))
            if (translate) ChatColor.translateAlternateColorCodes('&', config.getString(key)) else config.getString(key)
        else
            if (translate) ChatColor.translateAlternateColorCodes('&', defaultValue) else defaultValue
    }

    fun getStringList(key: String, defaultValue: List<String>): List<String> =
            if (config.contains(key) && config.isList(key) && !config.getList(key).isEmpty()) config.getStringList(key) else defaultValue

    fun getStringList(key: String, defaultValue: List<String>, translate: Boolean = true): List<String> {
        return if (config.contains(key) && config.isList(key) && !config.getList(key).isEmpty())
            if (translate) config.getStringList(key).stream().map { s -> ChatColor.translateAlternateColorCodes('&', s) }.collect(Collectors.toList()) else config.getStringList(key)
        else
            if (translate) defaultValue.stream().map { s -> ChatColor.translateAlternateColorCodes('&', s) }.collect(Collectors.toList()) else defaultValue
    }

}