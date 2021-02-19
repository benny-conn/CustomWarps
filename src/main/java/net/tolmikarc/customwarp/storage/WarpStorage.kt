package net.tolmikarc.customwarp.storage

import net.tolmikarc.customwarp.entity.Warp
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent
import org.mineacademy.fo.Common
import org.mineacademy.fo.model.HookManager
import org.mineacademy.fo.settings.YamlSectionConfig
import java.util.*

class WarpStorage(uuid: UUID) : YamlSectionConfig(uuid.toString()), Listener {


    var warps = mutableSetOf<Warp>()
    private var pendingBalance = 0.0


    init {
        loadConfiguration(null, "data.db")
    }

    override fun onLoadFinish() {
        if (isSet("Warps")) {
            val savedWarps = getSet("Warps", Warp::class.java)
            savedWarps.forEach { addWarp(it) }
        }
        if (isSet("Pending_Balance")) {
            pendingBalance = getDouble("Pending_Balance")
        }
    }

    fun addPendingBalance(amount: Double) {
        pendingBalance += amount
        save("Pending_Balance", pendingBalance)
    }

    fun hasPendingBalance(): Boolean {
        return pendingBalance > 0.0
    }

    fun payOutBalance(player: Player): Double {
        val amount = pendingBalance
        HookManager.deposit(player, amount)
        pendingBalance = 0.0
        save("Pending_Balance", pendingBalance)
        return amount
    }

    fun addWarp(warp: Warp) {
        warps.add(warp)
        save("Warps", warps)
        allWarps.add(warp)
        byName[warp.name.toLowerCase()] = warp
    }

    fun removeWarp(warp: Warp) {
        warps.remove(warp)
        save("Warps", warps)
        allWarps.remove(warp)
        byName.remove(warp.name.toLowerCase())
    }

    // -------------------------------------------------------------
    // Event Listener for loading all caches upon server load
    @EventHandler(priority = EventPriority.HIGH)
    fun loadCaches(event: ServerLoadEvent) {
        Common.log("Loading Warp Caches...")
        val set = config.getKeys(false)
        for (key in set) {
            if (!key.toLowerCase().contains("meta")) {
                getWarpStorage(UUID.fromString(key))
            }
        }
        Common.log("Warp Cache Loading Successful!")
    }

    companion object {

        val cacheMap = mutableMapOf<UUID, WarpStorage>()
        val allWarps = mutableSetOf<Warp>()
        val byName = mutableMapOf<String, Warp>()

        fun getWarpStorage(uuid: UUID): WarpStorage {
            var warpStorage = cacheMap[uuid]
            if (warpStorage == null) {
                warpStorage = WarpStorage(uuid)
                cacheMap[uuid] = warpStorage
            }
            return warpStorage
        }

        fun getWarpByName(name: String): Warp? {
            return byName[name.toLowerCase()]
        }

        fun isNameUnique(name: String): Boolean {
            return getWarpByName(name) != null
        }

    }


}