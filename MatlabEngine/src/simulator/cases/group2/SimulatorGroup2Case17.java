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
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup2Case17 extends BaseSimulationCase {
		
	public SimulatorGroup2Case17(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(25, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {3,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,22}, new double[] {1.91,1.75,1.26}), StoveBurnerSize.SIX, new double[] {11,86,96,93});
		Room r1 = new Room(5.5,5.15,2.21,2.08,1.07, RoomWindow.YES);
		Room r2 = new Room(4.65,2.45,2.1,1.83,1.5, RoomWindow.YES);
		Room r3 = new Room(2.13,3.15,2.84,1.41,1.79, RoomWindow.YES);
		Room r4 = new Room(2.48,3.95,2.62,1.67,1.62, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 220, 1.2, 1.3, 1.7, getRates());
		new IncandescentLighting(86, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {5,5})), getRates());
		new Dishwasher(state, 204, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {10,17,22})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(23, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {2,1})), getRates());
		new Clothwasher(state, 155, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {9,12,21})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, rooms, 30000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffBattery();
	}
}
