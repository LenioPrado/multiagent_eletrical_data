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

public class SimulatorGroup2Case1 extends BaseSimulationCase {
		
	public SimulatorGroup2Case1(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(36, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {2,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,14}, new double[] {1.1,2.3}), StoveBurnerSize.NINE, new double[] {69,5,83,59});
		Room r1 = new Room(2.51,5.42,2.29,2.4,1.8, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.3, 1.2, 1.1, getRates());
		new IncandescentLighting(78, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {1,4})), getRates());
		new Dishwasher(state, 241, new CyclicUsageParameter(2, new FixedTimeOfUse(48, new double[] {11,13})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(31, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {2,5})), getRates());
		new Clothwasher(state, 168, new CyclicUsageParameter(1, new FixedTimeOfUse(56, new double[] {5})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
