package equipment;

import helper.MatlabHelper;

public abstract class Equipment {

	protected <T> void addValue(String paramName, T value) throws Exception {
		add(paramName, "Value", String.valueOf(value));
	}
	
	protected void addInitialCondition(String paramName, String value) throws Exception {
		add(paramName, "InitialCondition", value);
	}
	
	protected void addGain(String paramName, double value) throws Exception {
		add(paramName, "Gain", String.valueOf(value));
	}
	
	protected void addSpecificKeyValue(String paramName, String key, double value) throws Exception {
		add(paramName, key, String.valueOf(value));
	}	
	
	private void add(String paramName, String key, String value) throws Exception {
		MatlabHelper.setParam(getBaseParamPath()+paramName, key, value);
	}	
	
	protected abstract String getBaseParamPath();
}