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

public class SimulatorGroup2Case12 extends BaseSimulationCase {
		
	public SimulatorGroup2Case12(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(22, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {2,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,15,19}, new double[] {1.6,1.83,1.16}), StoveBurnerSize.NINE, new double[] {61,60,91,50});
		Room r1 = new Room(3.07,3.41,2.79,1.76,1.88, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.3, 1.1, 1.1, getRates());
		new IncandescentLighting(66, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {2,21}, new double[] {5,3})), getRates());
		new Dishwasher(state, 188, new CyclicUsageParameter(3, new FixedTimeOfUse(60, new double[] {5,15,18})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(29, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {5,1})), getRates());
		new Clothwasher(state, 279, new CyclicUsageParameter(1, new FixedTimeOfUse(75, new double[] {9})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
