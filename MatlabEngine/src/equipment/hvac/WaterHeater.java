package equipment.hvac;

import enums.EquipmentState;
import enums.WaterHeaterType;
import equipment.RatedConnectableEquipment;
import equipment.rates.Rates;

public class WaterHeater extends RatedConnectableEquipment {
	
	public WaterHeater(EquipmentState state, WaterHeaterParameters waterHeaterParameters, Rates rates) throws Exception {
		super(state, rates);

		if(waterHeaterParameters == null)
			throw new Exception("WaterHeaterParameters cannot be null");
			
		doCalculations(waterHeaterParameters);
	}

	private void doCalculations(WaterHeaterParameters waterHeaterParameters) throws Exception {
		WaterHeaterType waterHeaterType = waterHeaterParameters.getWaterHeaterType();
		float thermalConductivity = 6837.48f;
		float massOfWater = 999.4f;
		float heatOfWater = 4186.8f;
		double thermalCapacitanceOfWater = (waterHeaterParameters.getLiterCapacity() * 0.001) * heatOfWater * massOfWater;
		float jouleWatts = 1 / (3600 * waterHeaterParameters.getEfficiency());
		float heatMassRatio = heatOfWater * massOfWater;
		float Q_WH_EF = waterHeaterParameters.getBTUPower() * waterHeaterParameters.getEfficiency() * 3600;		
		boolean isElectric = waterHeaterType == WaterHeaterType.ELECTRIC;
		
		if (waterHeaterType == WaterHeaterType.GAS) {
			Q_WH_EF *= 0.293083f;
		}		
		
		addValue("Water Heater/Rates/Rate_Ele_WH", isElectric ? 1 : 0);
		addValue("Water Heater/Rates/Rate_Gas_WH", isElectric ? 0 : 1);
		
		addValue("Set Point WH", waterHeaterParameters.getTemperatureSetPoint());
		addValue("T_amb", waterHeaterParameters.getAmbientTemperature());

		addValue("Water Heater/Q_WH*EF", Q_WH_EF);
		addValue("Water Heater/UA_WH", thermalConductivity);
		addValue("Water Heater/UA_WH1", thermalConductivity);		
		addValue("Water Heater/Cp_WH*mdot_WH", heatMassRatio);
		
		addInitialCondition("Water Heater/Integrator1", String.valueOf(waterHeaterParameters.getTemperatureSetPoint()));
		
		addGain("Water Heater/1_(3600*EF)", jouleWatts);
		addGain("Water Heater/Tinlet", waterHeaterParameters.getInletTemperature());
		addGain("Water Heater/1_Cw_WH", 1 / thermalCapacitanceOfWater);
		addGain("Water Heater/mdot_WH*Cp_WH", heatMassRatio);
	}
	
	@Override
	protected String getEquipmentOnOffKey() {
		return "Water Heater/OnOff WH";
	}
	
	@Override
	protected String getBaseParamPath() {
		return "Energy_Cost/";
	}

	@Override
	protected void setEquipmentRates() throws Exception {
		addValue("Water Heater/Rates/Ele_off", getRates().getOffPeak());
		addValue("Water Heater/Rates/Ele_mid", getRates().getMidPeak());
		addValue("Water Heater/Rates/Ele_peak", getRates().getOnPeak());
		
		addValue("Water Heater/Rates/Gas rate", getRates().getGas());
	}
}