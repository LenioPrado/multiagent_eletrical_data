package simulator.cases.group3;

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

public class SimulatorGroup3Case9 extends BaseSimulationCase {
		
	public SimulatorGroup3Case9(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(27, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {4,3})), getRates());
		new Thermostat(23.2,  new double[] {7,10,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,15,18}, new double[] {2.6,2.26,2.66}), StoveBurnerSize.NINE, new double[] {14,7,96,24});
		new Room(3.49,4.44,2.96,2.48,2.46, RoomWindow.YES);
		new Room(2.89,5.22,2.28,2.21,2.36, RoomWindow.YES);
		new Room(3.45,4.21,3.47,1.61,2.4, RoomWindow.YES);
		new Room(2.58,5.02,3.4,2.32,1.94, RoomWindow.YES);
		new Refrigerator(state, 127, 1.3, 1.2, 1.2, getRates());
		new IncandescentLighting(62, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {2,2})), getRates());
		new CompactFluorescentLighting(29, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {4,19}, new double[] {1,4})), getRates());
		new Clothwasher(state, 269, new CyclicUsageParameter(3, new FixedTimeOfUse(77, new double[] {6,17,18})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffWaterHeater();
		createOffPoolPump();
		createOffPhotovoltaic();
		createOffDryer();
		createOffDishwasher();
		createOffBattery();
		createOffAirConditioner();
	}
}
