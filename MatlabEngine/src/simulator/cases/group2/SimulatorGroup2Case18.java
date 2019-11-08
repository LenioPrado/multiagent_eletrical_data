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

public class SimulatorGroup2Case18 extends BaseSimulationCase {
		
	public SimulatorGroup2Case18(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {5,18}, new double[] {2,4})), getRates());
		Thermostat thermostat = new Thermostat(23.5,  new double[] {9,11,13,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {9,14,22}, new double[] {2.98,2.66,1.4}), StoveBurnerSize.SIX, new double[] {2,80,68,33});
		Room r1 = new Room(5.09,4.81,3.46,1.77,2.34, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1, 1.1, 1.5, getRates());
		new IncandescentLighting(60, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {6,22}, new double[] {3,4})), getRates());
		new Dishwasher(state, 202, new CyclicUsageParameter(2, new FixedTimeOfUse(68, new double[] {9,17})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(30, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,21}, new double[] {4,2})), getRates());
		new Clothwasher(state, 159, new CyclicUsageParameter(3, new FixedTimeOfUse(37, new double[] {5,13,21})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 39647, getRates());
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
