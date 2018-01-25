package cc.deepinmc.utils.modules.hologram

import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * The hologram's manager
 *
 * @author Zoyn
 * @since 2018-01-25
 */
class HologramManager {

    companion object {
        val instance = HologramManager()
    }

    private val hologramMap: MutableMap<Location, Hologram> = mutableMapOf()

    fun addHologram(location: Location, hologram: Hologram) {
        if (hologramMap.containsKey(location))
            Bukkit.getLogger().warning("found duplicate hologram add!")
        else
            hologramMap[location] = hologram
    }

    fun removeHologram(location: Location?) = hologramMap.remove(location)

    fun getHolograms() = hologramMap.values

    fun clearHologram() = hologramMap.clear()

}