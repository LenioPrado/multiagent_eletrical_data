package simulator.cases.group3;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.Equipment;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
import equipment.energysources.Battery;
import equipment.energysources.Photovoltaic;
import equipment.energysources.Wind;
import equipment.hvac.AirConditioner;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
import equipment.lighting.CompactFluorescentLighting;
import equipment.lighting.IncandescentLighting;
import equipment.lighting.LightingParameter;
import equipment.lighting.TubeFluorescentLighting;
import equipment.rates.Rates;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup3Case12 extends BaseSimulationCase {
		
	public SimulatorGroup3Case12(Rates rates) throws Exception {
		super(rates);
	}
	
	@Override
	protected Equipment getEquipment(EquipmentState state) throws Exception {
		return null;
	}

	@Override
	public void setSimulationValues() throws Exception {		
		setOnCaseValues(EquipmentState.ON);
		setOffCaseValues(EquipmentState.OFF);
	}

	@Override
	public void resetSimulationValues() throws Exception {
		setOnCaseValues(EquipmentState.OFF);
		setOffCaseValues(EquipmentState.OFF);
		
		setRoomsValues();
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulationGroup3Case12";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new Clothwasher(state, 161, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {8,14})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {4,2})), getRates());
		new IncandescentLighting(81, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {3,3})), getRates());
		new Refrigerator(state, 127, 1.1, 1, 1.7, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5}, new double[] {1.03}), StoveBurnerSize.SIX, new double[] {61,88,34,78});
		new TubeFluorescentLighting(30, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {2,18}, new double[] {5,2})), getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
		
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10, 15, 20})));
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184,  0.62f), getRates());
		new Dishwasher(state, 241, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {11,13})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, 30000, getRates());
	}
}