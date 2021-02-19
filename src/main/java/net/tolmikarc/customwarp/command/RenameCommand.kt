package net.tolmikarc.customwarp.command

import net.tolmikarc.customwarp.entity.Warp
import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand

class RenameCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "rename") {
    override fun onCommand() {
        val storage = WarpStorage.getWarpStorage(player.uniqueId)
        val warp = WarpStorage.getWarpByName(args[0])
        if (warp == null) {
            Messenger.error(player, Localization.NOT_EXIST)
            return
        }
        if (!storage.warps.contains(warp)) {
            Messenger.error(player, Localization.NOT_OWNED)
            return
        }
        if (args[1].length > 16) {
            Messenger.error(player, Localization.VALID_LENGTH)
            return
        }
        storage.removeWarp(warp)
        val newWarp = Warp(warp.location, args[1], player.uniqueId)
        storage.addWarp(newWarp)
        Messenger.success(player, Localization.RENAMED.replace("{old}", warp.name).replace("{new}", newWarp.name))
    }

    init {
        minArguments = 2
        description = Localization.RENAME_DESCRIPTION
        usage = "<warp> <name>"
        if (!Settings.PERMISSIONS_ENABLED)
            permission = null
    }
}