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
		new TubeFluorescentLighting(30, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,21}, new double[] {4,3})), getRates());
		Thermostat thermostat = new Thermostat(18.4,  new double[] {8,10,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,13,21}, new double[] {2.76,2.36,2.18}), StoveBurnerSize.SIX, new double[] {96,39,99,15});
		Room r1 = new Room(3.55,3.81,2.89,2.21,2.47, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1, 1.3, 1, getRates());
		new IncandescentLighting(63, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,18}, new double[] {5,2})), getRates());
		new Dishwasher(state, 241, new CyclicUsageParameter(3, new FixedTimeOfUse(75, new double[] {7,16,22})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(26, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,18}, new double[] {1,1})), getRates());
		new Clothwasher(state, 176, new CyclicUsageParameter(3, new FixedTimeOfUse(39, new double[] {6,12,19})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 49339, getRates());
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
