package net.tolmikarc.customwarp.command

import net.tolmikarc.customwarp.entity.Warp
import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand
import org.mineacademy.fo.model.HookManager
import java.text.NumberFormat

class SetWarpCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "setwarp") {
    override fun onCommand() {
        val storage = WarpStorage.getWarpStorage(player.uniqueId)

        if (WarpStorage.isNameUnique(args[0])) {
            Messenger.error(player, Localization.NAME_TAKEN.replace("{name}", args[0]))
            return
        }
        if (args[0].length > 16) {
            Messenger.error(player, Localization.VALID_LENGTH)
            return
        }
        if (HookManager.getBalance(player) - Settings.SET_WARP_COST < 0) {
            Messenger.error(
                player,
                Localization.NOT_ENOUGH_MONEY_SET.replace("{cost}", Settings.SET_WARP_COST.toString())
            )
            return
        }
        if (storage.warps.size + 1 > Settings.MAX_PLAYER_WARPS) {
            Messenger.error(player, Localization.TOO_MANY_WARPS.replace("{max}", Settings.MAX_PLAYER_WARPS.toString()))
            return
        }

        checkBoolean(!player.location.subtract(0.0, 1.0, 0.0).block.type.isAir, Localization.SOLID_GROUND)

        val warp = Warp(player.location, args[0], player.uniqueId)
        if (args.size > 1) {
            val cost = args[1].toDoubleOrNull()
            if (cost != null) {
                warp.cost = cost
            }
        }
        storage.addWarp(warp)
        HookManager.withdraw(player, Settings.SET_WARP_COST)
        Messenger.success(
            player,
            Localization.SET_WARP.replace("{name}", warp.name)
                .replace("{cost}", NumberFormat.getCurrencyInstance().format(Settings.SET_WARP_COST))
        )
    }

    init {
        minArguments = 1
        description = Localization.SET_WARP_DESCRIPTION
        usage = "<name> [cost]"
    }
}