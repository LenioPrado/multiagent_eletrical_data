package simulator.cases.group2;

import enums.EquipmentState;
import enums.EquipmentType;
import enums.Hotwater;
import enums.RoomWindow;
import enums.StoveBurnerSize;
import enums.WaterHeaterType;
import enums.WaterTemperature;
import equipment.appliances.Refrigerator;
import equipment.appliances.Stove;
import equipment.cyclicusage.Clothwasher;
import equipment.cyclicusage.CyclicUsageParameter;
import equipment.cyclicusage.Dishwasher;
import equipment.hvac.AirConditioner;
import equipment.hvac.WaterHeater;
import equipment.hvac.WaterHeaterParameters;
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 48000, 59, 112, 0.92f), getRates());
		new TubeFluorescentLighting(27, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {5,19}, new double[] {5,2})), getRates());
		Thermostat thermostat = new Thermostat(22.2,  new double[] {6,10,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,13,19}, new double[] {2.16,1.43,1.03}), StoveBurnerSize.EIGHT, new double[] {8,78,35,51});
		Room r1 = new Room(4.75,2.74,2.45,1.91,2.37, RoomWindow.YES);
		Room r2 = new Room(4.51,2.6,2.28,2.48,1.84, RoomWindow.YES);
		Room r3 = new Room(5.29,4.05,3.05,2.14,2.01, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.3, 1.2, 1.8, getRates());
		new IncandescentLighting(97, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {7,19}, new double[] {3,5})), getRates());
		new Dishwasher(state, 262, new CyclicUsageParameter(1, new FixedTimeOfUse(69, new double[] {9})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(25, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {5,5})), getRates());
		new Clothwasher(state, 156, new CyclicUsageParameter(3, new FixedTimeOfUse(49, new double[] {6,14,20})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 41223, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffBattery();
	}
}
