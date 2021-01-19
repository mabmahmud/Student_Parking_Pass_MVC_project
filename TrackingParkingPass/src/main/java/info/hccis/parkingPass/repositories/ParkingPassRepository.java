
package info.hccis.parkingPass.repositories;

import info.hccis.parkingPass.jpa.entity.ParkingPass;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface ParkingPassRepository extends CrudRepository<ParkingPass, Integer>{
    
    
}
