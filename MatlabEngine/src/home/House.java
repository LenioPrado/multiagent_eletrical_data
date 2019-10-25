package home;

import equipment.Equipment;

public abstract class House extends Equipment{

	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/House/";
	}
}