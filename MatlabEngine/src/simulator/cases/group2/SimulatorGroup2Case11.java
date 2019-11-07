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
		new TubeFluorescentLighting(38, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,23}, new double[] {1,3})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,12}, new double[] {1.86,1.78}), StoveBurnerSize.SIX, new double[] {80,37,40,75});
		Room r1 = new Room(3.25,4.47,2.92,2.12,1.16, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.2, 1.3, 1.2, getRates());
		new IncandescentLighting(73, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5,20}, new double[] {5,1})), getRates());
		new Dishwasher(state, 254, new CyclicUsageParameter(2, new FixedTimeOfUse(33, new double[] {10,14})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(34, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {4,5})), getRates());
		new Clothwasher(state, 231, new CyclicUsageParameter(1, new FixedTimeOfUse(85, new double[] {11})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
