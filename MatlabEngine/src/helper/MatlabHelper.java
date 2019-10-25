package helper;

import java.util.Arrays;
import java.util.concurrent.Future;

import com.mathworks.engine.MatlabEngine;
import com.mathworks.matlab.types.Struct;

public class MatlabHelper {
	
	private static MatlabEngine _eng;	
	
	public static void loadSimulinkModel(String modelFolder, String modelName) throws Exception {		
		String modelPath = modelFolder + modelName;
		String toLoad = String.format("load_system('%s')", modelPath);
		String waitMessage = String.format("Loading Simulink model: %s", modelName);

		executeAndWait(toLoad, waitMessage);
		System.out.println("Simulink model loaded!!!");
	}	

	public static void runSimulinkModel(String modelName) throws Exception {
		String options = "'SaveOutput','on','OutputSaveName','yOut','SaveTime','on','TimeSaveName','tOut');";
		String toLoad = String.format("simOut = sim('%s',%s", modelName, options);
		String waitMessage = "Running Simulation...";

		executeAndWait(toLoad, waitMessage, 5000);
		System.out.println("Simulink terminated!!!");
	}

	public static void changeSimulationState(String modelName, String state) throws Exception {
		String toLoad = String.format("set_param('%s','SimulationCommand','%s');", modelName, state);
		getEngine().eval(toLoad);
	}	
		
	public static void executeAndWait(String action, String waitMessage, Integer... waitTime) throws Exception {		
		Future<Void> fSim = getEngine().evalAsync(action);
		System.out.println(waitMessage);
		while (!fSim.isDone()) {			
			int sleepTime = waitTime.length > 0 ? waitTime[0] : 10000;
			sleep(sleepTime);
			System.out.println(waitMessage);
		}
	}
	
	public static <T> T executeAsync(String action, Object param, Integer... waitTime) throws Exception {
		T result = null;
		Future<T> fSim = getEngine().fevalAsync(action, param);
		while (!fSim.isDone()) {
			System.out.println("Executing action: " + action);
			int sleepTime = waitTime.length > 0 ? waitTime[0] : 10000;
			sleep(sleepTime);
		}
		try {
			result = fSim.get();
		} catch (Exception e) {
			System.out.println("Error executing action async: " + action + " - " + param.toString());
			e.printStackTrace();
		}
		return result;
	}

	public static <T> void setParam(String obj, String parameter, T value) throws Exception {
		System.out.println(String.format("Setting parameter \t Model: '%-50s' \t Parameter: '%-20s' \t\t Value: '%s'", obj, parameter, value));
		String eval = String.format("set_param('%s','%s','%s');", obj, parameter, value);
		getEngine().eval(eval);
	}
	
	public static void eval(String expression) {
		try {
			System.out.println(String.format("Evaluating expression '%s'.", expression));
			getEngine().eval(expression);
		} catch (Exception e) {
			System.out.println(String.format("Error evaluating expression '%s'.", expression));
			e.printStackTrace();
		}
	}
	
	public static <T> T feval(String function) {
		T variable = null;
		try {
			System.out.println(String.format("Evaluating function '%s'.", function));
			variable = getEngine().feval(function);
		} catch (Exception e) {
			System.out.println(String.format("Error evaluating function '%s'.", function));
			e.printStackTrace();
		}
		return variable;
	}
	
	public static <T> T getVariable(String variableName) {
		T variable = null;
		try {
			System.out.println(String.format("Getting variable '%s'.", variableName));
			variable = getEngine().getVariable(variableName);
		} catch (Exception e) {
			System.out.println(String.format("Error getting variable '%s'.", variableName));
			e.printStackTrace();
		}
		return variable;
	}

	public static <T> T putVariable(String variableName, T value) {
		T variable = null;
		try {
			getEngine().putVariable(variableName, value);
		} catch (Exception e) {
			System.out.println(String.format("Error putting variable '%s' and value '%s'.", variableName, value));
			e.printStackTrace();
		}
		return variable;
	}

	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			System.out.println("Error on thread sleep!");
			e.printStackTrace();
		}
	}
	
	public static MatlabEngine getEngine() throws Exception {
		if (_eng == null) {
			_eng = startEngine().get();
			System.out.println("Matlab Engine Started!!!");
		}
		return _eng;
	}
	
	private static Future<MatlabEngine> startEngine() throws Exception {
		System.out.println("Starting Matlab Engine...");
		try {
			return MatlabEngine.startMatlabAsync();
		} catch (Exception e) {
			System.out.println("Error while starting Matlab engine!!!");
			e.printStackTrace();
			throw e;
		}
	}
	
	public static void closeEngine() {
		System.out.println("Closing Matlab Engine...");
		try {
			getEngine().close();
		} catch (Exception e) {
			System.out.println("Error while closing Matlab engine!!!");
			e.printStackTrace();
		}
	}
	
	public static void who() {
		try {
			String[] w = getEngine().feval("who");
			System.out.println("MATLAB workspace variables " + Arrays.toString(w));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void changeToFolder(String folder) {
		String toFolder = String.format("cd '%s'", folder);
		System.out.println("Changing to folder: " + folder);
		eval(toFolder);
	}
	
	public static void showStructValues(Struct struct) {		
		for (String key : struct.keySet()) {
			Object value = struct.get(key);
			if (value.getClass() == Struct.class) {
				Struct structValue = (Struct) value;
				showStructValues(structValue);
			} else {
				String valueContent = "";
				if (value.getClass() == double[].class) {
					double[] doubleValue = (double[]) value;
					valueContent = Arrays.toString(doubleValue);
				} else {
					valueContent = value.toString();
				}
				System.out.println("Key: '" + key + "' Value: " + valueContent);
			}
		}
	}
}
