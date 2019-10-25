package unifei;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/measure")
public class MeasurementService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Measurement getMeasurement(@QueryParam("id") int id) {
		Measurement measurement = new Measurement(1, "Nova medição", 100);
		return measurement;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMeasurement(Measurement m) {
		if (m != null) {
			System.out.println(m.toString());
			m.setId(m.getId() + 1000);
			m.setCurrent(m.getCurrent() + 1000);
			m.setName(m.getName() + " Inserted");
		} else {
			System.out.println("Measurement is null!");
		}
		return Response.status(Status.OK).entity(m).build();
	}
}
