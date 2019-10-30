package simulator;
import java.util.ArrayList;
import java.util.List;

import enums.WaterHeaterType;
import enums.YearSeason;
import equipment.hvac.WaterHeaterParameters;
import equipment.rates.Rates;
import helper.MatlabHelper;
import simulator.cases.AirConditionerSimulation;
import simulator.cases.BatterySimulation;
import simulator.cases.ClothwasherSimulation;
import simulator.cases.DishwasherSimulation;
import simulator.cases.DryerSimulation;
import simulator.cases.FurnaceSimulation;
import simulator.cases.PVSimulation;
import simulator.cases.PoolPumpSimulation;
import simulator.cases.RefrigeratorSimulation;
import simulator.cases.SimulatorCase1;
import simulator.cases.SimulatorCase2;
import simulator.cases.StoveSimulation;
import simulator.cases.WaterHeaterSimulation;
import simulator.cases.WindSimulation;

public class ResidencialSimulator {
	
	public static void main(String[] args) throws Exception {
		ResidencialSimulator simulator = new ResidencialSimulator();
		simulator.executeResidencialSimulator("E:\\Develop\\PhD Softwares\\SRLS - Smart Residential Load Simulator\\SRLS2\\","Energy_Cost.mdl");
	}

	private void executeResidencialSimulator(String modelFolder, String modelName) throws Exception {
		MatlabHelper.changeToFolder(modelFolder);	
		boolean runModel = true;
		if(runModel) {
			MatlabHelper.loadSimulinkModel(modelFolder, modelName);
			List<BaseSimulationCase> simulationCases = getSimulationCases();
			
			for (int i=0; i<simulationCases.size(); i++) {
				try {					
					System.out.println("Simulation: " + (i+1));
					BaseSimulationCase sim = simulationCases.get(i);
					sim.setSimulationValues();
					MatlabHelper.runSimulinkModel(modelName);
					MatlabHelper.feval("processResults", i);
					MatlabHelper.getEngine().eval(String.format("plotLoadValues('%s_%d', %d)", sim.getSimulationDescription(), i, i));					
					sim.resetSimulationValues();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}				
			}				
			
			System.out.println("Ending App...");
		}
	}
	
	//MatlabHelper.getEngine().eval(String.format("plotGenerationValues('%s_%d')", sim.getSimulationDescription(), i));
	//MatlabHelper.getEngine().eval(String.format("plotApplianceValues('%s_%d')", sim.getSimulationDescription(), i));
	//MatlabHelper.getEngine().eval(String.format("plotStateOfChargeValues('%s_%d')", sim.getSimulationDescription(), i));	
	
	private List<BaseSimulationCase> getSimulationCases() throws Exception {
		List<BaseSimulationCase> simulationCases = new ArrayList<BaseSimulationCase>();		
		
		Rates rates = new Rates(6.2, 9.2, 10.8, 35.7, YearSeason.SUMMER);
		
		simulationCases.add(new SimulatorCase1(rates));
		simulationCases.add(new SimulatorCase2(rates));
		
		//addSimulationCases(simulationCases, rates);
		
		return simulationCases;
	}
	
	public void addSimulationCases(List<BaseSimulationCase> simulationCases, Rates rates) throws Exception {
		simulationCases.add(new AirConditionerSimulation(rates));
		simulationCases.add(new FurnaceSimulation(rates));
		simulationCases.add(new WaterHeaterSimulation(new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 10, 20, 40000, 55, 184, 0.62f), rates));
		simulationCases.add(new WaterHeaterSimulation(new WaterHeaterParameters(WaterHeaterType.ELECTRIC, 5, 13, 60000, 68, 284, 0.52f), rates));
		
		simulationCases.add(new DryerSimulation(rates));		
		simulationCases.add(new ClothwasherSimulation(rates));
		simulationCases.add(new DishwasherSimulation(rates));
		simulationCases.add(new PoolPumpSimulation(rates));		
		simulationCases.add(new RefrigeratorSimulation(rates));
		simulationCases.add(new StoveSimulation(rates));

		simulationCases.add(new BatterySimulation(rates));		
		simulationCases.add(new PVSimulation(rates));
		simulationCases.add(new WindSimulation(rates));		
	}
}