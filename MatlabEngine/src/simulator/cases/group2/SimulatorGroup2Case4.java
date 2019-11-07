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
		new TubeFluorescentLighting(34, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {4,4})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,15}, new double[] {1.26,2.45}), StoveBurnerSize.SEVEN, new double[] {88,9,18,50});
		Room r1 = new Room(5.28,3.57,2.96,2.36,2.49, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.2, 1.2, 2, getRates());
		new IncandescentLighting(74, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {4,1})), getRates());
		new Dishwasher(state, 173, new CyclicUsageParameter(1, new FixedTimeOfUse(71, new double[] {5})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(40, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {9,20}, new double[] {2,1})), getRates());
		new Clothwasher(state, 153, new CyclicUsageParameter(3, new FixedTimeOfUse(66, new double[] {5,16,19})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
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
