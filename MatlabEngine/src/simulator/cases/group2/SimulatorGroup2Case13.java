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

public class SimulatorGroup2Case13 extends BaseSimulationCase {
		
	public SimulatorGroup2Case13(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {7,18}, new double[] {1,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,17,18}, new double[] {2.2,2.58,0.93}), StoveBurnerSize.SIX, new double[] {85,87,3,25});
		Room r1 = new Room(2.6,2.37,2.71,2.42,2.39, RoomWindow.YES);
		Room r2 = new Room(4.8,3.58,2.62,1.56,1.85, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 127, 1.1, 1.1, 1.1, getRates());
		new IncandescentLighting(100, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {3,3})), getRates());
		new Dishwasher(state, 187, new CyclicUsageParameter(1, new FixedTimeOfUse(36, new double[] {11})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(40, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {2,22}, new double[] {1,1})), getRates());
		new Clothwasher(state, 241, new CyclicUsageParameter(2, new FixedTimeOfUse(41, new double[] {5,13})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
