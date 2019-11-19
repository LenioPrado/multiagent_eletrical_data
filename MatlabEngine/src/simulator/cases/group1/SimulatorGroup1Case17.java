package simulator.cases.group1;

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
import equipment.cyclicusage.Dryer;
import equipment.cyclicusage.PoolPump;
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

public class SimulatorGroup1Case17 extends BaseSimulationCase {
		
	public SimulatorGroup1Case17(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 48000, 46, 129, 0.41f), getRates());
		new TubeFluorescentLighting(32, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {1,3})), getRates());
		Thermostat thermostat = new Thermostat(22.4,  new double[] {7,12,15,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,16,19}, new double[] {1.41,2.08,1.6}), StoveBurnerSize.SIX, new double[] {37,25,65,65});
		Room r1 = new Room(2.41,2.08,3.42,1.92,1.9, RoomWindow.YES);
		Room r2 = new Room(3.65,3.67,2.47,1.33,1.74, RoomWindow.YES);
		Room r3 = new Room(2.96,5.08,2.41,1.59,1.81, RoomWindow.YES);
		Room r4 = new Room(4.58,3.38,3.01,1.7,1.93, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1, 1, 1.5, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(90, new double[] {5,14,22})));
		new IncandescentLighting(84, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,23}, new double[] {5,1})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(47, new double[] {11,14,19})));
		new Dishwasher(state, 279, new CyclicUsageParameter(2, new FixedTimeOfUse(30, new double[] {5,13})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(27, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {5,2})), getRates());
		new Clothwasher(state, 219, new CyclicUsageParameter(2, new FixedTimeOfUse(34, new double[] {6,17})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 53623, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
