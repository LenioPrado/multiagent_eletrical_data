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

public class SimulatorGroup2Case5 extends BaseSimulationCase {
		
	public SimulatorGroup2Case5(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(35, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {5,18}, new double[] {3,1})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11}, new double[] {0.76}), StoveBurnerSize.NINE, new double[] {35,27,44,2});
		Room r1 = new Room(5.2,4.84,2.14,1.51,1.44, RoomWindow.YES);
		Room r2 = new Room(3.29,4.09,2.68,1.82,1.77, RoomWindow.YES);
		Room r3 = new Room(5.38,2.22,3.49,1.16,1.35, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.1, 1, 1.8, getRates());
		new IncandescentLighting(67, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,23}, new double[] {5,1})), getRates());
		new Dishwasher(state, 175, new CyclicUsageParameter(3, new FixedTimeOfUse(64, new double[] {7,14,18})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(35, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5,18}, new double[] {2,5})), getRates());
		new Clothwasher(state, 235, new CyclicUsageParameter(3, new FixedTimeOfUse(40, new double[] {11,17,21})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
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
