package agents.phd.unifei.services;

import java.util.List;
import java.util.Map.Entry;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class BaseServiceClient {
	
	private static String baseURL = "http://localhost:8080/ElectricalData/rest/";
	//private static String baseURL = "http://localhost:8080/JavaWebService/rest/";
	
	public Response doGetRequest(String path) throws Exception {
		WebTarget pathdWebTargetQuery = getWebTarget(path).queryParam("id", 1);

		try {
			Invocation.Builder invocationBuilder = pathdWebTargetQuery.request(MediaType.APPLICATION_JSON_TYPE);
			Response response = invocationBuilder.get();
			return response;			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public Response doPutRequest(String path, Object obj, List<Entry<String, Object>> parameters) {
        WebTarget putWebTarget = getWebTarget(path);
        
        if(parameters != null && parameters.size() > 0) {
        	for (Entry<String, Object> parameter : parameters) {
        		putWebTarget = putWebTarget.queryParam(parameter.getKey(), parameter.getValue());
			}
        }
        Invocation.Builder invocationBuilder = putWebTarget.request().header("User-Agent", "Android");
        Response putResponse = invocationBuilder.post(Entity.entity(obj, MediaType.APPLICATION_JSON_TYPE));
        return putResponse;
	}
	
	public Response doPutRequest(String path, String entity) {
        WebTarget putWebTarget = getWebTarget(path);
        Invocation.Builder invocationBuilder = putWebTarget.request();
        Response putResponse = invocationBuilder.put(Entity.entity(entity, MediaType.APPLICATION_JSON_TYPE));
        return putResponse;
	}
	
	private WebTarget getWebTarget(String path) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(baseURL);
		WebTarget resourceWebTarget = webTarget.path(path);
		
		return resourceWebTarget;
	}
}