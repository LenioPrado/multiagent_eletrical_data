package equipment.lighting;

import equipment.rates.Rates;

public class TubeFluorescentLighting extends Lighting {
	
	public TubeFluorescentLighting(int powerConsumption, LightingParameter lightingParameter, Rates rates) throws Exception {
		super(powerConsumption, lightingParameter, rates);
	}
	
	@Override
	protected String getParamSuffix() {
		return "_FLU";
	}
}