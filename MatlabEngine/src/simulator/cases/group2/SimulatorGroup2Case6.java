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

public class SimulatorGroup2Case6 extends BaseSimulationCase {
		
	public SimulatorGroup2Case6(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(24, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,19}, new double[] {3,3})), getRates());
		Thermostat thermostat = new Thermostat(27.5,  new double[] {7,11,13,16});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {8,16,21}, new double[] {2.48,2.61,1.76}), StoveBurnerSize.NINE, new double[] {20,95,65,44});
		Room r1 = new Room(3.59,4.44,2.63,2.1,2.45, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 127, 1.3, 1.3, 1.5, getRates());
		new IncandescentLighting(92, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {2,4})), getRates());
		new Dishwasher(state, 233, new CyclicUsageParameter(3, new FixedTimeOfUse(38, new double[] {6,13,21})), EquipmentType.LOW_EFFICIENCY, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(20, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {6,20}, new double[] {5,1})), getRates());
		new Clothwasher(state, 271, new CyclicUsageParameter(2, new FixedTimeOfUse(51, new double[] {7,12})), WaterTemperature.WARM, EquipmentType.LOW_EFFICIENCY, Hotwater.CONNECTED);
		new AirConditioner(state, thermostat, rooms, 50743, getRates());
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
