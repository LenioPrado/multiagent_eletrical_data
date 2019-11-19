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
		new TubeFluorescentLighting(31, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {8,20}, new double[] {3,4})), getRates());
		Thermostat thermostat = new Thermostat(28,  new double[] {7,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {10,14,20}, new double[] {0.96,1.26,2.35}), StoveBurnerSize.SEVEN, new double[] {24,30,70,18});
		Room r1 = new Room(4.29,2.16,3.12,1.97,1.98, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.1, 1.1, 1.1, getRates());
		new IncandescentLighting(80, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {4,2})), getRates());
		new Dishwasher(state, 264, new CyclicUsageParameter(2, new FixedTimeOfUse(86, new double[] {8,16})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(34, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {3,4})), getRates());
		new Clothwasher(state, 199, new CyclicUsageParameter(3, new FixedTimeOfUse(68, new double[] {7,16,21})), WaterTemperature.HOT, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 35371, getRates());
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
