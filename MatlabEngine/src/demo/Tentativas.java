package demo;
import java.util.Arrays;
import java.util.Random;

import com.mathworks.matlab.types.HandleObject;
import com.mathworks.matlab.types.Struct;

import helper.MatlabHelper;

public class Tentativas {

	public Tentativas() throws Exception {
	}

	public static void main(String[] args) throws Exception {
				
		generateRandom();
		//Tentativas t = new Tentativas();
		//t.runVdp();
		// t.callEvalFunction();
		// t.callFunction();
	}

	public static void generateRandom() {
		Double[] hoursPower = { 
				2965.0, 4220.0, 2825.0, 4555.0, 3220.0, 3711.0, 2430.0, 2000.0, 2650.0, 1700.0, 2440.0, 2250.0, 
				2680.0, 3100.0, 4500.0, 5150.0, 4550.0, 3200.0, 4100.0, 3600.0, 4000.0, 2600.0, 5220.0, 3600.0
		};		
		
		//1700 - 5220
		
		Random r = new Random(1000);
		String res = "";
		for (int i=0; i<hoursPower.length;i++) {			
			hoursPower[i] = Double.valueOf(String.valueOf(r.nextInt(3520)+1700));
			res += hoursPower[i];
			if(i != hoursPower.length-1)
				res += ",";
		}		
		
		System.out.println(String.format("HoursPower: \n%s", res));
	}	

	public void PutVar() throws Exception {		
		MatlabHelper.putVariable("x", 7.0);
		MatlabHelper.putVariable("y", 3.0);
		MatlabHelper.eval("z = complex(x, y);");
		String[] w = MatlabHelper.feval("who");
		System.out.println("MATLAB workspace variables " + Arrays.toString(w));
		MatlabHelper.closeEngine();
	}

	public void PassStruct() throws Exception {
		double[] y = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		HandleObject h = MatlabHelper.getEngine().feval("plot", y);
		MatlabHelper.eval("pause(5)");
		double[] color = { 1.0, 0.5, 0.7 };
		Struct s = new Struct("Color", color, "LineWidth", 2);

		MatlabHelper.getEngine().feval("set", h, s);
		MatlabHelper.eval("print('myPlot', '-djpeg')");
		MatlabHelper.closeEngine();
	}

	public void callEvalFunction() throws Exception {
		//double[] y = { 1.0, 2.0, 3.0, 4.0, 5.0 };
		MatlabHelper.eval("cd 'E:\\Develop\\PhD Softwares\\Simulink Models\\'");
		MatlabHelper.eval("InSig = defineInputs();");
		Struct InSig = MatlabHelper.getVariable("InSig");
		System.out.println("Simulation result:");
		MatlabHelper.showStructValues(InSig);
		MatlabHelper.closeEngine();
	}
	
	public void callFunction() throws Exception {
		double[] y = { 7.0, 2.0, 3.0, 4.0, 5.0 };
		// proxy.eval("addpath('C:\\some\\path')");
		MatlabHelper.eval("cd 'E:\\Develop\\PhD Softwares\\Simulink Models\\'");
		Object[] h = MatlabHelper.getEngine().feval(2, "firstfunction", y);
		System.out.println("Simulation result " + Arrays.toString(h));
		MatlabHelper.closeEngine();
	}

	public void runVdp() throws Exception {
		//MatlabHelper.loadAndRunSimulinkModel("", "vdp");

		// Get simulation data
		MatlabHelper.eval("y = simOut.get('yOut');");
		MatlabHelper.eval("t = simOut.get('tOut');");
		// Graph results and create image file
		MatlabHelper.eval("plot(t,y);");
		MatlabHelper.sleep(5000);
		MatlabHelper.eval("print('vdpPlot','-djpeg');");
		// Return results to Java
		double[][] y = MatlabHelper.getVariable("y");
		double[] t = MatlabHelper.getVariable("t");
		// Display results
		System.out.println("Simulation result " + Arrays.deepToString(y));
		System.out.println("Time vector " + Arrays.toString(t));
		MatlabHelper.closeEngine();
	}
	
	
	public void passStructValue(String functionName) {
		Struct s = new Struct("YearSeason",1,"Off_Peak","6.2","Mid_Peak","9.2","On_Peak","10.8");
		try {
			Object result = MatlabHelper.getEngine().feval(functionName, s);
			System.out.println(String.format("Result of function %s: %s", functionName, result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exampleHandleObject() {
		MatlabHelper.eval("cm = containers.Map({'id','name'},{11,'mw'});");
		HandleObject handle = MatlabHelper.getVariable("cm");
		try {
			String[] cells = MatlabHelper.getEngine().feval("keys", handle);
			System.out.println("Handle Object: " + Arrays.deepToString(cells));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getResults() {
		System.out.println("Get simulation data...");
		MatlabHelper.eval("y = simOut.get('yOut');");
		MatlabHelper.eval("t = simOut.get('tOut');");

		System.out.println("Return results to Java...");
		double[][] y = MatlabHelper.getVariable("y");
		double[] t = MatlabHelper.getVariable("t");

		System.out.println("Display results...");
		System.out.println("Simulation result " + Arrays.deepToString(y));
		System.out.println("Time vector " + Arrays.toString(t));

		System.out.println("Graph results and create image file...");
		MatlabHelper.eval("plot(t,y);");
		MatlabHelper.sleep(5000);
		MatlabHelper.eval("print('Residencial','-djpeg');");

		System.out.println("Ending App...");
		MatlabHelper.sleep(20000);
		MatlabHelper.closeEngine();
	}
}