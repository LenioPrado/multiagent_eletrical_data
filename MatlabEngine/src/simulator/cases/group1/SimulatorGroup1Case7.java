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

public class SimulatorGroup1Case7 extends BaseSimulationCase {
		
	public SimulatorGroup1Case7(Rates rates) throws Exception {
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
		return "SimulationGroup1Case7";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 229, new CyclicUsageParameter(3, new FixedTimeOfUse(50, new double[] {6,12,20})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,3})), getRates());
		new Dishwasher(state, 215, new CyclicUsageParameter(1, new FixedTimeOfUse(86, new double[] {7})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {5,17})));
		new IncandescentLighting(66, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {3,3})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(77, new double[] {6,14,22})));
		new Refrigerator(state, 220, 1.2, 1.3, 1.3, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,15}, new double[] {1.65,2.91}), StoveBurnerSize.SEVEN, new double[] {73,23,9,67});
		new TubeFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {4,5})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 53000, 50, 120, 0.59f), getRates());

	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}