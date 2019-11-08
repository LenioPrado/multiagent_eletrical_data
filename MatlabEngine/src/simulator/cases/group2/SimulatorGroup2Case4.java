package simulator.cases.group2;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.hvac.AirConditioner;
import equipment.lighting.CompactFluorescentLighting;
import equipment.lighting.IncandescentLighting;
import equipment.lighting.LightingParameter;
import equipment.lighting.TubeFluorescentLighting;
import equipment.rates.Rates;
import home.Room;
import home.Thermostat;
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup2Case4 extends BaseSimulationCase {
		
	public SimulatorGroup2Case4(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {4,5})), getRates());
		Thermostat thermostat = new Thermostat(27.4,  new double[] {8,12,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,17,19}, new double[] {0.73,2.7,2.26}), StoveBurnerSize.SEVEN, new double[] {6,25,56,16});
		Room r1 = new Room(4.67,4.75,2.58,1.33,2.31, RoomWindow.YES);
		Room r2 = new Room(3.5,4.43,3.37,2.17,1.42, RoomWindow.YES);
		Room r3 = new Room(5.17,4.05,2.53,1.53,1.65, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.1, 1.2, 1.1, getRates());
		new IncandescentLighting(72, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,23}, new double[] {4,2})), getRates());
		new Dishwasher(state, 181, new CyclicUsageParameter(1, new FixedTimeOfUse(72, new double[] {6})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {1,2})), getRates());
		new Clothwasher(state, 174, new CyclicUsageParameter(1, new FixedTimeOfUse(86, new double[] {5})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 51260, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffFurnace();
		createOffDryer();
		createOffBattery();
	}
}
