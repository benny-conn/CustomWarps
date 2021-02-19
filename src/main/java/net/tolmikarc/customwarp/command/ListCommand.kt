package net.tolmikarc.customwarp.command

import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Common
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand

class ListCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "list") {
    override fun onCommand() {
        val warps = WarpStorage.byName.keys.toList().map { it.capitalize() }
        if (warps.isEmpty()) {
            Messenger.error(player, Localization.NO_WARPS)
            return
        }
        val page = if (args.isNotEmpty()) findNumber(0, 0, Int.MAX_VALUE, Localization.VALID_NUMBER) else 1
        var min = page * 20 - 20
        var max = page * 20
        if (max > warps.size) {
            max = warps.size
        }
        if (min > warps.size) {
            min = 0
        }
        val warpsSublist = warps.subList(min, max)
        Messenger.info(player, "Warps ($page): ${Common.join(warpsSublist, ", ")}")
    }

    init {
        description = Localization.LIST_DESCRIPTION
        usage = "[page]"
        if (!Settings.PERMISSIONS_ENABLED)
            permission = null
    }
}