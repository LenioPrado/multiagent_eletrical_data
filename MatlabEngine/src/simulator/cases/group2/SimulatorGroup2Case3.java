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

public class SimulatorGroup2Case3 extends BaseSimulationCase {
		
	public SimulatorGroup2Case3(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(30, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {0,19}, new double[] {2,3})), getRates());
		Thermostat thermostat = new Thermostat(21.6,  new double[] {6,11,14,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {11,16,21}, new double[] {2.41,2.71,2.8}), StoveBurnerSize.NINE, new double[] {73,8,58,16});
		Room r1 = new Room(2.99,3.21,2.28,1.5,1.08, RoomWindow.YES);
		Room r2 = new Room(2.21,4.04,2.7,2.12,1.92, RoomWindow.YES);
		Room r3 = new Room(5.3,5.22,2.99,1.91,1.96, RoomWindow.YES);
		Room[] rooms = {r1, r2, r3};
		new Refrigerator(state, 220, 1.3, 1.3, 1.3, getRates());
		new IncandescentLighting(67, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {6,18}, new double[] {2,5})), getRates());
		new Dishwasher(state, 247, new CyclicUsageParameter(1, new FixedTimeOfUse(79, new double[] {8})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(37, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,18}, new double[] {2,3})), getRates());
		new Clothwasher(state, 187, new CyclicUsageParameter(2, new FixedTimeOfUse(65, new double[] {11,17})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 35589, getRates());
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
