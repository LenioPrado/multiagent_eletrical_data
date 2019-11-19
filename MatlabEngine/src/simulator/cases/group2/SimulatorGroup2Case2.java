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

public class SimulatorGroup2Case2 extends BaseSimulationCase {
		
	public SimulatorGroup2Case2(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {1,22}, new double[] {1,2})), getRates());
		Thermostat thermostat = new Thermostat(20,  new double[] {6,12,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,17,22}, new double[] {2.11,1.76,1.4}), StoveBurnerSize.SIX, new double[] {42,64,97,10});
		Room r1 = new Room(5.41,2.39,2.54,1.68,1.37, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.2, 1.1, 1.9, getRates());
		new IncandescentLighting(67, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {3,4})), getRates());
		new Dishwasher(state, 177, new CyclicUsageParameter(1, new FixedTimeOfUse(46, new double[] {11})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(34, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {9,21}, new double[] {3,3})), getRates());
		new Clothwasher(state, 162, new CyclicUsageParameter(2, new FixedTimeOfUse(44, new double[] {5,17})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 53077, getRates());
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
