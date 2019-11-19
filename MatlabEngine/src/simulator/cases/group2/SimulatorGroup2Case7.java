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

public class SimulatorGroup2Case7 extends BaseSimulationCase {
		
	public SimulatorGroup2Case7(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(20, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {2,1})), getRates());
		Thermostat thermostat = new Thermostat(24.7,  new double[] {7,12,14,18});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,16,21}, new double[] {2.68,1.71,0.6}), StoveBurnerSize.SEVEN, new double[] {67,3,20,10});
		Room r1 = new Room(3.18,2.42,2.77,2.17,1.9, RoomWindow.YES);
		Room r2 = new Room(4.2,2.94,3.41,2.32,2.04, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 220, 1.1, 1.3, 1.1, getRates());
		new IncandescentLighting(94, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,19}, new double[] {1,1})), getRates());
		new Dishwasher(state, 197, new CyclicUsageParameter(2, new FixedTimeOfUse(74, new double[] {10,14})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(32, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {5,5})), getRates());
		new Clothwasher(state, 200, new CyclicUsageParameter(3, new FixedTimeOfUse(32, new double[] {5,14,22})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 46094, getRates());
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
