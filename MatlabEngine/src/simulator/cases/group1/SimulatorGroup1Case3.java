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

public class SimulatorGroup1Case3 extends BaseSimulationCase {
		
	public SimulatorGroup1Case3(Rates rates) throws Exception {
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
		return "SimulationGroup1Case3";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new AirConditioner(state, 30000, getRates());
		new Clothwasher(state, 265, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {9,15})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(33, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {4,4})), getRates());
		new Dishwasher(state, 231, new CyclicUsageParameter(2, new FixedTimeOfUse(49, new double[] {11,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new Dryer(state, new CyclicUsageParameter(2, new FixedTimeOfUse(46, new double[] {8,12})));
		new IncandescentLighting(96, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {1,1})), getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(62, new double[] {11,17,18})));
		new Refrigerator(state, 127, 1.3, 1.2, 1.4, getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,16}, new double[] {2.9,1.25}), StoveBurnerSize.SIX, new double[] {3,20,34,50});
		new TubeFluorescentLighting(37, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {7,23}, new double[] {1,1})), getRates());
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 34000, 60, 83, 0.38f), getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		new Battery(state, new FixedTimeOfUse(50, new double[] {10, 14, 16}), getBatteryHoursPower(), 1500, getRates());				
		new Photovoltaic(state, getPhotovoltaicHoursRadiation(), getRates());			
		new Wind(state, getWindHoursPower(), getRates());
	}
}