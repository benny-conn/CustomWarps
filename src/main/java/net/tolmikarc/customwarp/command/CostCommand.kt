package net.tolmikarc.customwarp.command

import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand
import java.text.NumberFormat

class CostCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "cost") {
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
        val number = args[1].toDoubleOrNull()
        if (number == null || number < 0.0 || number > Settings.MAX_WARP_COST) {
            Messenger.error(
                player,
                Localization.VALID_NUMBER_BETWEEN.replace("{max}", Settings.MAX_WARP_COST.toString())
            )
            return
        }
        warp.cost = number
        Messenger.success(
            player,
            Localization.SET_COST.replace("{cost}", NumberFormat.getCurrencyInstance().format(warp.cost))
        )
    }

    init {
        minArguments = 2
        description = Localization.COST_DESCRIPTION
        usage = "<warp> <cost>"
        if (!Settings.PERMISSIONS_ENABLED)
            permission = null
    }
}