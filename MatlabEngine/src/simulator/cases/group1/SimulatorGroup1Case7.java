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

public class SimulatorGroup1Case7 extends BaseSimulationCase {
		
	public SimulatorGroup1Case7(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 22, 54000, 57, 113, 0.9f), getRates());
		new TubeFluorescentLighting(27, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {1,4})), getRates());
		Thermostat thermostat = new Thermostat(18.7,  new double[] {8,12,15,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,13,22}, new double[] {1.15,1.75,0.56}), StoveBurnerSize.NINE, new double[] {90,46,43,83});
		Room r1 = new Room(3.68,2.9,3.24,1.33,2.17, RoomWindow.YES);
		Room r2 = new Room(2.72,4.34,2.82,1.55,1.09, RoomWindow.YES);
		Room r3 = new Room(2.03,5.38,2.24,2.36,1.24, RoomWindow.YES);
		Room r4 = new Room(3.5,2.79,2.45,1.19,2.12, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3, r4};
		new Refrigerator(state, 127, 1.2, 1.2, 1.7, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(87, new double[] {9,12})));
		new IncandescentLighting(84, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {4,3})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(40, new double[] {6,16,22})));
		new Dishwasher(state, 157, new CyclicUsageParameter(1, new FixedTimeOfUse(72, new double[] {6})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(27, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {6,19}, new double[] {4,4})), getRates());
		new Clothwasher(state, 208, new CyclicUsageParameter(1, new FixedTimeOfUse(49, new double[] {8})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 42540, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
