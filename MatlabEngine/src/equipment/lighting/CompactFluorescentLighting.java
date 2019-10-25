package equipment.lighting;

import equipment.rates.Rates;

public class CompactFluorescentLighting extends Lighting {

	public CompactFluorescentLighting(int powerConsumption, LightingParameter lightingParameter, Rates rates) throws Exception {
		super(powerConsumption, lightingParameter, rates);
	}
	
	@Override
	protected String getParamSuffix() {
		return "_CFL";
	}
}