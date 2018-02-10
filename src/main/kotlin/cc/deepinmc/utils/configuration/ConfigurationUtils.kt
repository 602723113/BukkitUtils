package cc.deepinmc.utils.configuration

import org.bukkit.Location
import org.bukkit.configuration.file.FileConfiguration

fun FileConfiguration.isLocation(path: String): Boolean = this.get(path) is Location

fun FileConfiguration.getLocation(path: String): Location = this.get(path) as Location