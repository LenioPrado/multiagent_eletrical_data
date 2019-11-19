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

public class SimulatorGroup1Case3 extends BaseSimulationCase {
		
	public SimulatorGroup1Case3(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 21, 33000, 36, 140, 0.13f), getRates());
		new TubeFluorescentLighting(39, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {1,19}, new double[] {4,5})), getRates());
		Thermostat thermostat = new Thermostat(24.1,  new double[] {8,12,15,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {6,16,22}, new double[] {0.91,0.68,2.35}), StoveBurnerSize.NINE, new double[] {22,55,61,34});
		Room r1 = new Room(3.37,2.59,2.24,1.52,1.54, RoomWindow.YES);
		Room r2 = new Room(5.46,2.96,3.31,1.74,2.16, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 220, 1, 1.2, 1.2, getRates());
		new PoolPump(state, new CyclicUsageParameter(2, new FixedTimeOfUse(64, new double[] {8,16})));
		new IncandescentLighting(61, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {4,20}, new double[] {1,4})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(77, new double[] {8,17,20})));
		new Dishwasher(state, 236, new CyclicUsageParameter(1, new FixedTimeOfUse(85, new double[] {10})), EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new CompactFluorescentLighting(37, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {0,22}, new double[] {3,3})), getRates());
		new Clothwasher(state, 253, new CyclicUsageParameter(3, new FixedTimeOfUse(67, new double[] {6,17,22})), WaterTemperature.WARM, EquipmentType.ENERGY_STAR, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 50334, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
