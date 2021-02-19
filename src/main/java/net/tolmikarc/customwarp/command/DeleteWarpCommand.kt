package net.tolmikarc.customwarp.command

import net.tolmikarc.customwarp.constants.Permissions
import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand

class DeleteWarpCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "delwarp|deletewarp") {
    override fun onCommand() {
        var storage = WarpStorage.getWarpStorage(player.uniqueId)
        val warp = WarpStorage.getWarpByName(args[0])
        if (warp == null) {
            Messenger.error(player, Localization.NOT_EXIST)
            return
        }
        if (player.hasPermission(Permissions.OVERRIDE_PERMISSION)) {
            storage = WarpStorage.getWarpStorage(warp.owner)
        }
        if (!storage.warps.contains(warp)) {
            Messenger.error(player, Localization.NOT_OWNED)
            return
        }
        storage.removeWarp(warp)
    }

    init {
        minArguments = 1
        description = Localization.DELETE_DESCRIPTION
        usage = "<name>"
        if (!Settings.PERMISSIONS_ENABLED)
            permission = null
    }
}