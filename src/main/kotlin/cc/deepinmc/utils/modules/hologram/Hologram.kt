package cc.deepinmc.utils.modules.hologram

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * Represent a hologram
 *
 * @author Zoyn
 * @since 2018-01-25
 */
data class Hologram(var lines: MutableList<String>, var armorStands: MutableList<Entity>, var locations: MutableList<Location>)