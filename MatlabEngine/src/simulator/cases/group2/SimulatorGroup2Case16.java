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

public class SimulatorGroup2Case16 extends BaseSimulationCase {
		
	public SimulatorGroup2Case16(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(37, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {5,2})), getRates());
		Thermostat thermostat = new Thermostat(15.1,  new double[] {8,12,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,13,21}, new double[] {2.93,1.06,2.76}), StoveBurnerSize.NINE, new double[] {17,58,36,46});
		Room r1 = new Room(3.71,2.2,2.63,2.09,1.44, RoomWindow.YES);
		Room r2 = new Room(2.56,3.5,2.9,1.05,2.04, RoomWindow.YES);
		Room r3 = new Room(5.17,3.99,3.46,1.4,1.63, RoomWindow.YES);
		Room r4 = new Room(3.26,4.28,3.18,2.06,1.18, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1, 1.1, 1.5, getRates());
		new IncandescentLighting(60, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5,21}, new double[] {5,1})), getRates());
		new Dishwasher(state, 273, new CyclicUsageParameter(2, new FixedTimeOfUse(36, new double[] {5,12})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(39, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {3,2})), getRates());
		new Clothwasher(state, 205, new CyclicUsageParameter(1, new FixedTimeOfUse(44, new double[] {5})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 49221, getRates());
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
