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
		new TubeFluorescentLighting(30, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,5})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,13}, new double[] {1.38,0.88}), StoveBurnerSize.SIX, new double[] {71,26,35,69});
		Room r1 = new Room(5.38,4.03,2.47,2.35,2.11, RoomWindow.YES);
		Room r2 = new Room(2.06,4.95,2.11,1.48,2.46, RoomWindow.YES);
		Room r3 = new Room(2.04,3.11,2.92,1.58,1.86, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 127, 1.1, 1, 1.6, getRates());
		new IncandescentLighting(75, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {2,1})), getRates());
		new Dishwasher(state, 150, new CyclicUsageParameter(1, new FixedTimeOfUse(60, new double[] {9})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(35, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {3,5})), getRates());
		new Clothwasher(state, 193, new CyclicUsageParameter(1, new FixedTimeOfUse(61, new double[] {6})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
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
