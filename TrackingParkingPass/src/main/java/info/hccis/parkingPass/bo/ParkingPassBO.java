package info.hccis.parkingPass.bo;
import info.hccis.parkingPass.dao.ParkingPassDAO;
import info.hccis.parkingPass.jpa.entity.ParkingPass;
import java.util.ArrayList;

/**
 * Parking pass business object
 *
 * @author CIS2232
 * @since 20201001
 */
public class ParkingPassBO {

    /**
     * Select all records from the database
     *
     * @return List of the parking passes
     * @since 20200923
     * @author BJM
     */
    public static ArrayList<ParkingPass> selectAll() {

        //Read from the database
        ParkingPassDAO parkingPassDAO = new ParkingPassDAO();
        ArrayList<ParkingPass> parkingPasses = parkingPassDAO.selectAll();
        return parkingPasses;
    }

    /**
     * Insert the parking pass into the database
     * @return 
     * @since 20201001
     * @author BJM
     */
    public boolean insert(ParkingPass parkingPass) {
        ParkingPassDAO parkingPassDAO = new ParkingPassDAO();
        return parkingPassDAO.insert(parkingPass);
    }

    /**
     * Update the parking pass into the database
     * @since 20201001
     * @author BJM
     */
    public static boolean update(ParkingPass parkingPass) {
        ParkingPassDAO parkingPassDAO = new ParkingPassDAO();
        return parkingPassDAO.update(parkingPass);
    }
//
//    public static void load() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    public ParkingPass save(ParkingPass parkingPass) {
//        ParkingPassDAO parkingPassDAO = new ParkingPassDAO();
//
//        //NOTE:  The id attribute generated from the database is an Integer not an
//        //       int.  The default for an Integer is null so can't compare to 0.
//        
//        if (parkingPass.getId() == null ) {
//            parkingPass = parkingPass.insert(parkingPass);
//        } else {
//            parkingPassDAO.update(parkingPass);
//        }
//
//        return parkingPass;
//    }
    
        public boolean delete(int id) {
        ParkingPassDAO ParkingPassDAO = new ParkingPassDAO();
        return ParkingPassDAO.delete(id);
    }
}
