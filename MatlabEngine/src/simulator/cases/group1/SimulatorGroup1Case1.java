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
import simulator.BaseSimulationCase;
import time.AllDayPeriodsTimeOfUse;
import time.FixedTimeOfUse;
import time.MorningNightTimeOfUse;

public class SimulatorGroup1Case1 extends BaseSimulationCase {
		
	public SimulatorGroup1Case1(Rates rates) throws Exception {
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
		new WaterHeater(state, new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184,  0.62f), getRates());
		new TubeFluorescentLighting(15, new LightingParameter(4, new MorningNightTimeOfUse(new double[] {5, 15}, new double[] {3, 6})), getRates());
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,14,20.30}, new double[] {.66667,1.5,.5}), StoveBurnerSize.SIX, new double[] {60,40,40,60});
		Room r1 = new Room(4.21,5.12,2.06,2.01,2.22, RoomWindow.YES);
		Room r2 = new Room(5.07,3.97,3.01,1.67,1.69, RoomWindow.YES);
		Room[] rooms = {r1, r2};
		new Refrigerator(state, 220, 0.9, 0.8, 1.7, getRates());
		new PoolPump(state, new CyclicUsageParameter(3, new FixedTimeOfUse(180, new double[] {5, 14, 22})));
		new IncandescentLighting(100, new LightingParameter(3, new MorningNightTimeOfUse(new double[] {5, 18}, new double[] {2, 5})), getRates());
		new Dryer(state, new CyclicUsageParameter(3, new FixedTimeOfUse(80, new double[] {10, 15, 20})));
		new Dishwasher(state, 250, new CyclicUsageParameter(3, new FixedTimeOfUse(120, new double[] {10, 15, 21.5})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(20, new LightingParameter(6, new MorningNightTimeOfUse(new double[] {5.5, 15}, new double[] {2.5, 5.5})), getRates());
		new Clothwasher(state, 154, new CyclicUsageParameter(3, new FixedTimeOfUse(60, new double[] {8, 13, 18})), WaterTemperature.COLD, EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new AirConditioner(state, rooms, 48000, getRates());
	}
	
	private void setOffCaseValues(EquipmentState state) throws Exception {		
		createOffWind();
		createOffPhotovoltaic();
		createOffBattery();
	}
}
