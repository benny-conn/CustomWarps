package net.tolmikarc.customwarp.entity

import org.bukkit.Location
import org.mineacademy.fo.collection.SerializedMap
import org.mineacademy.fo.model.ConfigSerializable
import java.util.*

class Warp(val location: Location, var name: String, val owner: UUID) : ConfigSerializable {

    var cost = 0.0


    override fun serialize(): SerializedMap {
        val map = SerializedMap()
        map.put("Location", location)
        map.put("Name", name)
        map.put("Owner", owner)
        map.put("Cost", cost)
        return map
    }

    companion object {
        @JvmStatic
        fun deserialize(map: SerializedMap): Warp {
            val warp = Warp(map.getLocation("Location"), map.getString("Name"), map.get("Owner", UUID::class.java))
            warp.cost = map.getDouble("Cost")
            return warp
        }
    }

}