package net.tolmikarc.customwarp.listener

import net.tolmikarc.customwarp.storage.WarpStorage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.mineacademy.fo.Messenger
import java.text.NumberFormat

class PlayerListener : Listener {

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        val storage = WarpStorage.getWarpStorage(player.uniqueId)
        if (storage.hasPendingBalance()) {
            val amount = storage.payOutBalance(player)
            Messenger.success(
                player,
                "You earned ${NumberFormat.getCurrencyInstance().format(amount)} from your warps while you were away"
            )
        }
    }


}