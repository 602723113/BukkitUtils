package cc.deepinmc.utils.plugin

import org.bukkit.plugin.java.JavaPlugin

/**
 * Main Class
 *
 * @author Zoyn
 * @since 2018-01-16
 */
class UtilPlugin : JavaPlugin() {

    companion object {
        @JvmStatic
        lateinit var instance: UtilPlugin
            private set
    }

    override fun onEnable() {
        instance = this
    }
}