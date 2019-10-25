package equipment.lighting;

import equipment.rates.Rates;

public class IncandescentLighting extends Lighting {
	
	public IncandescentLighting(int powerConsumption, LightingParameter lightingParameter, Rates rates) throws Exception {
		super(powerConsumption, lightingParameter, rates);
	}
	
	@Override
	protected String getParamSuffix() {
		return "";
	}
}