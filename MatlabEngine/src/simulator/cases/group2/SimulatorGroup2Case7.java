package simulator.cases.group2;

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

public class SimulatorGroup2Case7 extends BaseSimulationCase {
		
	public SimulatorGroup2Case7(Rates rates) throws Exception {
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
		return "SimulationGroup2Case7";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 266, new CyclicUsageParameter(2, new FixedTimeOfUse(86, new double[] {5,14})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,22}, new double[] {1,4})), getRates());
		new Dishwasher(state, 213, new CyclicUsageParameter(3, new FixedTimeOfUse(69, new double[] {9,13,19})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new IncandescentLighting(85, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {5,5})), getRates());
		new Refrigerator(state, 220, 1.2, 1.1, 1.4, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11}, new double[] {2.35}), StoveBurnerSize.SEVEN, new double[] {49,87,28,92});
		new TubeFluorescentLighting(24, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,18}, new double[] {4,3})), getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
		
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10, 15, 20})));
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184,  0.62f), getRates());		
	}
}