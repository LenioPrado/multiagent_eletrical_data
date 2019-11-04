package equipment.lighting;

import equipment.Equipment;
import equipment.rates.Rates;

public abstract class Lighting extends Equipment {

	protected Lighting(int powerConsumption, LightingParameter lightingParameter, Rates rates) throws Exception {
		
		if(lightingParameter == null)
			throw new Exception("LightingParameter cannot be null");
		
		String suffix = getParamSuffix();
		
		addValue("Power"+suffix, powerConsumption);
		addValue("H_Many"+suffix, lightingParameter.getHowManyBulbs());
		addValue("ON_Morning"+suffix, lightingParameter.getTimeOfUse().getMorningClockTimeWhenIsUsed());
		addValue("ON_Night"+suffix, lightingParameter.getTimeOfUse().getNightClockTimeWhenIsUsed());
		addValue("hrs_Morning"+suffix, lightingParameter.getTimeOfUse().getMorningAmountOfPowerOnHours());		
		addValue("hrs_Night"+suffix, lightingParameter.getTimeOfUse().getNightAmountOfPowerOnHours());
	}
	
	protected abstract String getParamSuffix();
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/Lighting/";
	}
}