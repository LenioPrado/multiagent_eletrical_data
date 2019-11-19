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

public class SimulatorGroup2Case20 extends BaseSimulationCase {
		
	public SimulatorGroup2Case20(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(38, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {9,22}, new double[] {3,1})), getRates());
		Thermostat thermostat = new Thermostat(18.2,  new double[] {6,12,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,16,18}, new double[] {2.05,0.7,2.85}), StoveBurnerSize.NINE, new double[] {75,66,69,33});
		Room r1 = new Room(5.31,5.27,3.43,2.31,1.32, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.1, 1, 2, getRates());
		new IncandescentLighting(76, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {3,5})), getRates());
		new Dishwasher(state, 180, new CyclicUsageParameter(2, new FixedTimeOfUse(55, new double[] {10,12})), EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new CompactFluorescentLighting(24, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {8,23}, new double[] {4,5})), getRates());
		new Clothwasher(state, 158, new CyclicUsageParameter(1, new FixedTimeOfUse(62, new double[] {11})), WaterTemperature.HOT, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 45869, getRates());
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
