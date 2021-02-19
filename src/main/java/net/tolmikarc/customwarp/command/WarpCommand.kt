package net.tolmikarc.customwarp.command

import io.papermc.lib.PaperLib
import net.tolmikarc.customwarp.constants.Permissions
import net.tolmikarc.customwarp.settings.Localization
import net.tolmikarc.customwarp.settings.Settings
import net.tolmikarc.customwarp.storage.WarpStorage
import org.bukkit.Bukkit
import org.mineacademy.fo.Messenger
import org.mineacademy.fo.command.SimpleCommandGroup
import org.mineacademy.fo.command.SimpleSubCommand
import org.mineacademy.fo.model.HookManager
import java.text.NumberFormat

class WarpCommand(parent: SimpleCommandGroup) : SimpleSubCommand(parent, "warp") {
    override fun onCommand() {
        val warp = WarpStorage.getWarpByName(args[0])
        val storage = WarpStorage.getWarpStorage(player.uniqueId)
        if (warp == null) {
            Messenger.error(player, Localization.NOT_EXIST)
            return
        }
        if (HookManager.getBalance(player) - warp.cost < 0) {
            Messenger.error(
                player,
                Localization.NOT_ENOUGH_MONEY_WARP.replace(
                    "{cost}",
                    NumberFormat.getCurrencyInstance().format(warp.cost)
                )
            )
            return
        }
        PaperLib.teleportAsync(player, warp.location).thenAccept { success ->
            if (success) {
                Messenger.success(player, Localization.WARP_SUCCESS.replace("{name}", warp.name))
                if (warp.cost > 0.0 && !storage.warps.contains(warp) && !player.hasPermission(Permissions.OVERRIDE_PERMISSION)) {
                    HookManager.withdraw(player, warp.cost)
                    val warpOwner = Bukkit.getPlayer(warp.owner)
                    if (warpOwner == null) {
                        WarpStorage.getWarpStorage(warp.owner).addPendingBalance(warp.cost)
                    } else {
                        Messenger.success(
                            warpOwner,
                            Localization.WARP_NOTIFICATION.replace("{player}", player.displayName)
                                .replace("{cost}", NumberFormat.getCurrencyInstance().format(warp.cost))
                        )
                        HookManager.deposit(warpOwner, warp.cost)
                    }
                    Messenger.info(
                        player,
                        Localization.COST_NOTIFICATION.replace(
                            "{cost}",
                            NumberFormat.getCurrencyInstance().format(warp.cost)
                        )
                    )
                }
            } else {
                Messenger.error(player, Localization.WARP_ERROR.replace("{name}", warp.name))
            }
        }
    }

    init {
        minArguments = 1
        description = Localization.WARP_DESCRIPTION
        usage = "<name>"
        if (!Settings.PERMISSIONS_ENABLED)
            permission = null
    }

}