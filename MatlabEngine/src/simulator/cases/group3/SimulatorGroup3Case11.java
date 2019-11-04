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

public class SimulatorGroup3Case11 extends BaseSimulationCase {
		
	public SimulatorGroup3Case11(Rates rates) throws Exception {
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
		return "SimulationGroup3Case11";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new Clothwasher(state, 258, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {10,14})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {2,1})), getRates());
		new IncandescentLighting(69, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {1,21}, new double[] {3,3})), getRates());
		new Refrigerator(state, 220, 1.2, 1, 1.3, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9}, new double[] {1.38}), StoveBurnerSize.SEVEN, new double[] {80,89,90,24});
		new TubeFluorescentLighting(33, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {5,2})), getRates());
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