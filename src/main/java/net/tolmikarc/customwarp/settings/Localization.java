/*
 * Copyright (c) 2021-2021 Tolmikarc All Rights Reserved
 */

package net.tolmikarc.customwarp.settings;

import org.mineacademy.fo.settings.SimpleLocalization;

public class Localization extends SimpleLocalization {


	public static String NOT_EXIST;
	public static String NOT_OWNED;
	public static String VALID_NUMBER_BETWEEN;
	public static String VALID_NUMBER;
	public static String SET_COST;
	public static String COST_DESCRIPTION;
	public static String DELETE_DESCRIPTION;
	public static String NO_WARPS;
	public static String LIST_DESCRIPTION;
	public static String VALID_LENGTH;
	public static String RENAMED;
	public static String RENAME_DESCRIPTION;
	public static String NAME_TAKEN;
	public static String NOT_ENOUGH_MONEY_SET;
	public static String TOO_MANY_WARPS;
	public static String SET_WARP;
	public static String SET_WARP_DESCRIPTION;
	public static String NOT_ENOUGH_MONEY_WARP;
	public static String WARP_SUCCESS;
	public static String WARP_NOTIFICATION;
	public static String COST_NOTIFICATION;
	public static String WARP_ERROR;
	public static String PAYOUT_NOTIFICATION;
	public static String SOLID_GROUND;
	public static String WARP_DESCRIPTION;


	@Override
	protected int getConfigVersion() {
		return 1;
	}


	private static void init() {
		pathPrefix(null);
		NOT_EXIST = getString("Not_Exist");
		NOT_OWNED = getString("Not_Owned");
		VALID_NUMBER_BETWEEN = getString("Valid_Number_Between");
		VALID_NUMBER = getString("Valid_Number");
		SET_COST = getString("Set_Cost");
		COST_DESCRIPTION = getString("Cost_Description");
		DELETE_DESCRIPTION = getString("Delete_Description");
		NO_WARPS = getString("No_Warps");
		LIST_DESCRIPTION = getString("List_Description");
		VALID_LENGTH = getString("Valid_Length");
		RENAMED = getString("Renamed");
		RENAME_DESCRIPTION = getString("Rename_Description");
		NAME_TAKEN = getString("Name_Taken");
		NOT_ENOUGH_MONEY_SET = getString("Not_Enough_Money_Set");
		TOO_MANY_WARPS = getString("Too_Many_Warps");
		SET_WARP = getString("Set_Warp");
		SET_WARP_DESCRIPTION = getString("Set_Warp_Description");
		NOT_ENOUGH_MONEY_WARP = getString("Not_Enough_Money_Warp");
		WARP_SUCCESS = getString("Warp_Success");
		WARP_NOTIFICATION = getString("Warp_Notification");
		COST_NOTIFICATION = getString("Cost_Notification");
		WARP_ERROR = getString("Warp_Error");
		PAYOUT_NOTIFICATION = getString("Payout_Notification");
		SOLID_GROUND = getString("Solid_Ground");
		WARP_DESCRIPTION = getString("Warp_Description");
	}


}
