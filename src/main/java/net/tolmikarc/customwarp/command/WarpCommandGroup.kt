package net.tolmikarc.customwarp.command

import org.mineacademy.fo.command.SimpleCommandGroup

class WarpCommandGroup : SimpleCommandGroup() {
    override fun registerSubcommands() {
        registerSubcommand(WarpCommand(this))
        registerSubcommand(SetWarpCommand(this))
        registerSubcommand(DeleteWarpCommand(this))
        registerSubcommand(CostCommand(this))
        registerSubcommand(RenameCommand(this))
        registerSubcommand(ListCommand(this))
    }

    override fun getCredits(): String {
        return ""
    }
}