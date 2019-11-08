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
		new TubeFluorescentLighting(30, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {8,21}, new double[] {1,4})), getRates());
		Thermostat thermostat = new Thermostat(25.6,  new double[] {7,12,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {5,17,19}, new double[] {1.76,2.66,2.85}), StoveBurnerSize.SEVEN, new double[] {16,72,46,89});
		Room r1 = new Room(5.17,3.12,3.4,2.15,1.85, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1, 1.1, 1, getRates());
		new IncandescentLighting(71, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {3,20}, new double[] {2,3})), getRates());
		new Dishwasher(state, 214, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {7,13})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(38, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {4,21}, new double[] {2,4})), getRates());
		new Clothwasher(state, 193, new CyclicUsageParameter(3, new FixedTimeOfUse(49, new double[] {9,16,19})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 48085, getRates());
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
