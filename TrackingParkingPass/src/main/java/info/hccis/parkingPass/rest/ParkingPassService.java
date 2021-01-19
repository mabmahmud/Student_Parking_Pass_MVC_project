
package info.hccis.parkingPass.rest;

import com.google.gson.Gson;
import info.hccis.parkingPass.jpa.entity.ParkingPass;
import info.hccis.parkingPass.repositories.ParkingPassRepository;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * 
 * @author mrahman2
 * @since 20201113
 */
@Path("/ParkingPassService/parkingpass")
public class ParkingPassService {

    private final ParkingPassRepository ppr;

    @Autowired
    public ParkingPassService(ParkingPassRepository ppr) {
        this.ppr = ppr;

    }

    @GET
    @Produces("application/json")
    public ArrayList<ParkingPass> getAll() {
        ArrayList<ParkingPass> parkingpass = (ArrayList<ParkingPass>) ppr.findAll();
        return parkingpass;
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getParkingPassById(@PathParam("id") int id) throws URISyntaxException {

        Optional<ParkingPass> parkingpass = ppr.findById(id);

        if (!parkingpass.isPresent()) {
            return Response.status(204).build();
        } else {
            return Response
                    .status(200)
                    .entity(parkingpass).build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createParkingPass(String parkingpassJson) 
    {
        
        Gson gson = new Gson();
        ParkingPass parkingpass = gson.fromJson(parkingpassJson, ParkingPass.class);
        
        if(parkingpass.getFirstName() == null || parkingpass.getFirstName().isEmpty()) {
            return Response.status(400).entity("Please provide all mandatory inputs").build();
        }
 
        if(parkingpass.getId() == null){
            parkingpass.setId(0);
        }

        parkingpass = ppr.save(parkingpass);

        String temp = "";
        temp = gson.toJson(parkingpass);

        return Response.status(201).entity(temp).header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();        
    }
}

//
//@RestController
//@RequestMapping("/rest/parkingpass")
//public class ParkingPassService {
//
//    private final ParkingPassRepository pr;
//
//    @Autowired
//    public ParkingPassService(ParkingPassRepository pr) {
//        this.pr = pr;
//
//    }
//
//    @RequestMapping("/")
//    public ArrayList<ParkingPass> getAll() {
//        return (ArrayList<ParkingPass>) pr.findAll();
//    }
//
//    @RequestMapping("/{id}")
//    public ParkingPass getById(@PathVariable int id) {
//        return pr.findById(id).orElseGet(ParkingPass::new);
//    }
//
//    @RequestMapping("/addpass")
//    public ParkingPass addPass(@Valid @RequestBody ParkingPass parkingpass) {
//        if (parkingpass.getId() == null) {
//            parkingpass.setId(0);
//        }
//
//        return pr.save(parkingpass);
//    }
//}
