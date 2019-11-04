package simulator.cases.group1;

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

public class SimulatorGroup1Case13 extends BaseSimulationCase {
		
	public SimulatorGroup1Case13(Rates rates) throws Exception {
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
		return "SimulationGroup1Case13";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 175, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {8,17,20})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(28, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {5,5})), getRates());
		new Dishwasher(state, 256, new CyclicUsageParameter(2, new FixedTimeOfUse(66, new double[] {9,17})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {6,16})));
		new IncandescentLighting(74, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {1,23}, new double[] {1,4})), getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(89, new double[] {10,12})));
		new Refrigerator(state, 220, 1.3, 1.1, 1.1, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,22}, new double[] {0.78,2.9,2.81}), StoveBurnerSize.NINE, new double[] {1,17,56,45});
		new TubeFluorescentLighting(36, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {4,1})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 51000, 52, 87, 0.91f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}