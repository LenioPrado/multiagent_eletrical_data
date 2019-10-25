package agents.phd.unifei.services;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ElectricalDataServiceClient extends BaseServiceClient {

	public boolean insertMeasurement(int placeId, String measurement) {
		List<Entry<String, Object>> entries = new ArrayList<Entry<String, Object>>();
		
		entries.add(new AbstractMap.SimpleEntry<String, Object>("placeId", placeId));
		
		Response response = doPutRequest("/electricaldata/insertMeasurement", measurement, entries);
		return response.getStatus() == Status.OK.getStatusCode();		
	}
}
