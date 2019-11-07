package simulator.cases.group1;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
import equipment.hvac.AirConditioner;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
import equipment.lighting.CompactFluorescentLighting;
import equipment.lighting.IncandescentLighting;
import equipment.lighting.LightingParameter;
import equipment.lighting.TubeFluorescentLighting;
import equipment.rates.Rates;
import home.Room;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup1Case17 extends BaseSimulationCase {
		
	public SimulatorGroup1Case17(Rates rates) throws Exception {
		super(rates);
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
	}
	
	@Override
	public String getSimulationDescription() {
		return "SimulationGroup1Case1";
	}
	
	private void setOnCaseValues(EquipmentState state) throws Exception {
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 39000, 47, 123, 0.61f), getRates());
		new TubeFluorescentLighting(24, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,23}, new double[] {2,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,15}, new double[] {1.36,2.13}), StoveBurnerSize.NINE, new double[] {90,59,84,1});
		Room r1 = new Room(2.7,5.44,2.48,1.24,1.09, RoomWindow.YES);
		Room r2 = new Room(3.64,4.33,3.27,1.06,1.45, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1, 1.3, 1.1, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(73, new double[] {8,13,22})));
		new IncandescentLighting(91, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {1,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(1, new FixedTimeOfUse(48, new double[] {5})));
		new Dishwasher(state, 191, new CyclicUsageParameter(3, new FixedTimeOfUse(81, new double[] {8,17,19})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(22, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,23}, new double[] {1,4})), getRates());
		new Clothwasher(state, 271, new CyclicUsageParameter(3, new FixedTimeOfUse(77, new double[] {10,16,20})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
