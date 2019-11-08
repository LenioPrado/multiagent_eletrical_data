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

public class SimulatorGroup2Case14 extends BaseSimulationCase {
		
	public SimulatorGroup2Case14(Rates rates) throws Exception {
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
		new TubeFluorescentLighting(37, new LightingParameter(5, new MorningNightTimeOfUse(new double[] {0,21}, new double[] {1,4})), getRates());
		Thermostat thermostat = new Thermostat(27.7,  new double[] {8,10,14,17});
		new Stove(state, new AllDayPeriodsTimeOfUse(new double[] {7,13,19}, new double[] {1.86,1.71,0.65}), StoveBurnerSize.SEVEN, new double[] {64,52,24,72});
		Room r1 = new Room(3.57,5.17,2.46,1.52,1.89, RoomWindow.YES);
		Room[] rooms = {r1};
		new Refrigerator(state, 220, 1.1, 1.3, 1.5, getRates());
		new IncandescentLighting(79, new LightingParameter(2, new MorningNightTimeOfUse(new double[] {2,20}, new double[] {3,1})), getRates());
		new Dishwasher(state, 276, new CyclicUsageParameter(2, new FixedTimeOfUse(61, new double[] {7,14})), EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new CompactFluorescentLighting(25, new LightingParameter(1, new MorningNightTimeOfUse(new double[] {3,23}, new double[] {5,2})), getRates());
		new Clothwasher(state, 159, new CyclicUsageParameter(3, new FixedTimeOfUse(41, new double[] {11,14,18})), WaterTemperature.COLD, EquipmentType.ENERGY_STAR, Hotwater.DISCONNECTED);
		new AirConditioner(state, thermostat, rooms, 37050, getRates());
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
