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

public class SimulatorGroup1Case11 extends BaseSimulationCase {
		
	public SimulatorGroup1Case11(Rates rates) throws Exception {
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
		return "SimulationGroup1Case11";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 184, new CyclicUsageParameter(2, new FixedTimeOfUse(57, new double[] {6,14})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {4,18}, new double[] {5,1})), getRates());
		new Dishwasher(state, 183, new CyclicUsageParameter(2, new FixedTimeOfUse(34, new double[] {10,14})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(74, new double[] {8,12,18})));
		new IncandescentLighting(71, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,22}, new double[] {4,2})), getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(52, new double[] {10,17})));
		new Refrigerator(state, 220, 1.3, 1.3, 2, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,15,18}, new double[] {1.8,2.76,1.48}), StoveBurnerSize.SEVEN, new double[] {21,23,19,71});
		new TubeFluorescentLighting(32, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5,23}, new double[] {5,3})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 42000, 36, 131, 0.63f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}