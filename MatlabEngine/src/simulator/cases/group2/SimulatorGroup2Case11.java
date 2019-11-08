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

public class SimulatorGroup2Case11 extends BaseSimulationCase {
		
	public SimulatorGroup2Case11(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(34, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {1,2})), getRates());
		Thermostat thermostat = new Thermostat(25.8,  new double[] {8,11,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,13,21}, new double[] {0.71,2.65,1.38}), StoveBurnerSize.EIGHT, new double[] {23,51,52,94});
		Room r1 = new Room(2.27,5.3,2.9,1,1.31, RoomWindow.YES);
		Room r2 = new Room(4.33,3.7,3.29,1.91,1.63, RoomWindow.YES);
		Room r3 = new Room(4.52,4.42,3.3,1.44,2.1, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.2, 1.1, 1.2, getRates());
		new IncandescentLighting(93, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,20}, new double[] {4,3})), getRates());
		new Dishwasher(state, 172, new CyclicUsageParameter(2, new FixedTimeOfUse(75, new double[] {9,13})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,21}, new double[] {2,3})), getRates());
		new Clothwasher(state, 242, new CyclicUsageParameter(2, new FixedTimeOfUse(57, new double[] {7,17})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 34779, getRates());
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
