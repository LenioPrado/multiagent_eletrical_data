package unifei;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ServiceClient {

	public static void main(String args[]) {
		Client client = ClientBuilder.newClient();
		ServiceClient service = new ServiceClient();

		System.out.println("Doing Get Request...");
		service.getRequest(client);
		
		System.out.println("\nDoing Put Request...");		
		service.putRequest(client);
	}
	
	public WebTarget getWebTarget(Client client) {
		WebTarget webTarget = client.target("http://localhost:8080/JavaWebService");

		WebTarget resourceWebTarget = webTarget.path("rest");
		WebTarget pathdWebTarget = resourceWebTarget.path("measure");
		
		return pathdWebTarget;
	}

	public void getRequest(Client client) {

		WebTarget pathdWebTargetQuery = getWebTarget(client).queryParam("id", 1);

		Invocation.Builder invocationBuilder = pathdWebTargetQuery.request(MediaType.APPLICATION_JSON_TYPE);

		Response response = invocationBuilder.get();

		System.out.println(response.getStatus());
		Measurement measurement = response.readEntity(Measurement.class);

		System.out.println(measurement.toString());
	}
	
	public void putRequest(Client client) {
		Measurement measurement = new Measurement();
        measurement.setId(1);
        measurement.setName("Medição 1234");
        measurement.setCurrent(300);

        WebTarget deleteWebTarget = getWebTarget(client);

        Invocation.Builder deleteInvocationBuilder = deleteWebTarget.request();
        Response putResponse = deleteInvocationBuilder.put(Entity.entity(measurement, MediaType.APPLICATION_JSON_TYPE));

        System.out.println(putResponse.getStatus());
        
        Measurement insertedMeasurement = putResponse.readEntity(Measurement.class);
        System.out.println(insertedMeasurement.toString());
	}
}
