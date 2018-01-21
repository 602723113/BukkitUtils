package cc.deepinmc.utils.plugin

import org.bukkit.plugin.java.JavaPlugin

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2018-01-16
 */
class UtilPlugin : JavaPlugin() {

    override fun onEnable() {
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: UtilPlugin
    }
}