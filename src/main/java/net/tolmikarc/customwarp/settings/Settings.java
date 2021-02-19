package net.tolmikarc.customwarp.settings;

import org.mineacademy.fo.settings.SimpleSettings;

public class Settings extends SimpleSettings {

	public static Boolean PERMISSIONS_ENABLED;
	public static Double SET_WARP_COST;
	public static Double MAX_WARP_COST;
	public static Integer MAX_PLAYER_WARPS;

	private static void init() {
		PERMISSIONS_ENABLED = getBoolean("Permissions_Enabled");
		SET_WARP_COST = getDouble("Set_Warp_Cost");
		MAX_WARP_COST = getDouble("Max_Warp_Cost");
		MAX_PLAYER_WARPS = getInteger("Max_Player_Warps");

	}


	@Override
	protected int getConfigVersion() {
		return 1;
	}


}
