package net.tolmikarc.customwarp

import net.tolmikarc.customwarp.command.WarpCommandGroup
import net.tolmikarc.customwarp.listener.PlayerListener
import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Common
import org.mineacademy.fo.plugin.SimplePlugin
import org.mineacademy.fo.settings.YamlStaticConfig
import java.util.*

class CustomWarpsPlugin : SimplePlugin() {
    override fun onPluginStart() {
        Common.ADD_TELL_PREFIX = true
        Common.ADD_LOG_PREFIX = true
        Common.log("Starting CustomWarps")
        registerEvents(WarpStorage(UUID.randomUUID()))
        registerEvents(PlayerListener())
        registerCommands("cw|customwarps", WarpCommandGroup())
    }

    override fun getSettings(): List<Class<out YamlStaticConfig?>> {
        return listOf(Settings::class.java, Localization::class.java)
    }

    override fun getFoundedYear(): Int {
        return 2021
    }

}