package info.hccis.parkingPass.dao;

import info.hccis.parkingPass.jpa.entity.ParkingPass;
import info.hccis.parkingPass.util.DatabaseUtility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Camper database access object
 *
 * @author bjm
 * @since 20200921
 */

public class ParkingPassDAO {

    public static boolean checkConnection() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cis2232_parking",
                    "root",
                    ""); //--No password.  For assignment submission this would be expected to be empty.
        } catch (Exception e) {
            return false;
        }

        return true;

    }

    /**
     * Select the records from the database
     *
     * @return 
     * @since 20201001
     * @author BJM
     */
    public ArrayList<ParkingPass> selectAll() {
        ArrayList<ParkingPass> parkingPasses = new ArrayList();

        //Select from the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cis2232_parking",
                    "root",
                    ""); //--No password.  For assignment submission this would be expected to be empty.
        } catch (Exception e) {
            System.out.println("Could not make a connection to the database");
            return null;
        }

        try {

            Statement statement = conn.createStatement();

            //***************************************************
            // Select using statement
            //***************************************************
            //Next selectAll all the rows and display them here...
            ResultSet rs = statement.executeQuery("select * from ParkingPass");

            //Show all the campers
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String licensePlate = rs.getString("licensePlate");
                String program = rs.getString("program");

                ParkingPass parkingPass = new ParkingPass(id, firstName, lastName, licensePlate, program);
                parkingPasses.add(parkingPass);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ParkingPassDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                //Could not close.  
                System.out.println("Error closing the connection.");
            }
        }
        return parkingPasses;

    }

    /**
     * Insert into the database.  Note the id is expected to be 0.  If it is not 
     * 0 then the update will be used.
     *
     * @param parkingPass Parking pass object to be inserted
     * @return true if no exception occurs
     * @since 20201001
     * @author BJM
     */
    public boolean insert(ParkingPass parkingPass) {

        Connection conn = null;

        try {
            //BJM 20200925 Removed the password.
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cis2232_parking",
                    "root",
                    "");
        } catch (SQLException ex) {
            System.out.println("SQL exception happned!");
            return false;
        }

        if (parkingPass.getId() == 0) {

            //***************************************************
            // INSERT
            //***************************************************
            try {
                String theStatement = "INSERT INTO ParkingPass(firstName,lastName,licensePlate, program) "
                        + "VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(theStatement);
                stmt.setString(1, parkingPass.getFirstName());
                stmt.setString(2, parkingPass.getLastName());
                stmt.setString(3, parkingPass.getLicensePlate());
                stmt.setString(4, parkingPass.getProgram());
                stmt.executeUpdate();
                return true;
            } catch (SQLException sqle) {
                System.out.println("sql exception caught");
                sqle.printStackTrace();
                return false;
            }
        } else {
            return update(parkingPass);
        }

    }

    /**
     * Update into the database.
     *
     * @param parkingPass Parking pass object to be updated
     * @return true if no exception occurs
     * @since 20201001
     * @author BJM
     */
    public boolean update(ParkingPass parkingPass) {

        Connection conn = null;

        try {
            //BJM 20200925 Removed the password.
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cis2232_parking",
                    "root",
                    "");
        } catch (SQLException ex) {
            System.out.println("SQL exception happned!");
            return false;
        }

        if (parkingPass.getId() == 0) {
            return false;
        } else {

            //***************************************************
            // update
            //***************************************************
            try {
                String theStatement = "UPDATE ParkingPass set firstName=?, lastName=?, "
                        + "licensePlate=?, program=? where id=?";
                PreparedStatement stmt = conn.prepareStatement(theStatement);
                stmt.setString(1, parkingPass.getFirstName());
                stmt.setString(2, parkingPass.getLastName());
                stmt.setString(3, parkingPass.getLicensePlate());
                stmt.setString(4, parkingPass.getProgram());
                stmt.setInt(5, parkingPass.getId());
                stmt.executeUpdate();
                return true;
            } catch (SQLException sqle) {
                System.out.println("sql exception caught");
                sqle.printStackTrace();
                return false;
            }
        }
    }

    public boolean delete(int id) {
     
               Connection conn = null;

        //***************************************************
        // INSERT
        //***************************************************
        try {
            conn = DatabaseUtility.getConnection("");
            String theStatement = "DELETE FROM ParkingPass WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(theStatement);
            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("sql exception caught");
            sqle.printStackTrace();
            return false;
        }

        return true;


    }

}
//public class ParkingPassDAO {
//
//    public static boolean checkConnection() {
//
//        Connection conn = null;
//        try {
//            conn = DatabaseUtility.getConnection("");
//        } catch (Exception e) {
//            return false;
//        }
//
//        return true;
//
//    }
//
//    /**
//     * Select the records from the database
//     *
//     * @since 20200923
//     * @author BJM
//     */
//    public ArrayList<Camper> select() {
//        ArrayList<Camper> campers = new ArrayList();
//
//        Connection conn = null;
//        try {
//
//            //Select the campers from the database
//            conn = DatabaseUtility.getConnection("");
//            Statement statement = conn.createStatement();
//
//            //***************************************************
//            // Select using statement
//            //***************************************************
//            //Next select all the rows and display them here...
//            ResultSet rs = statement.executeQuery("select * from Camper");
//
//            //Show all the campers
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String firstName = rs.getString("firstName");
//                String lastName = rs.getString("lastName");
//                String dob = rs.getString("dob");
//
//                Camper camper = new Camper(id);
//                camper.setFirstName(firstName);
//                camper.setLastName(lastName);
//                camper.setDob(dob);
//                campers.add(camper);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ParkingPassDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException ex) {
//                //Could not close.  
//                System.out.println("Error closing the connection.");
//            }
//        }
//        return campers;
//
//    }
//
//    /**
//     * Insert the camper into the database.
//     *
//     * @since 20200923
//     * @author BJM
//     *
//     * 20200925 BJM Modifications to get running using standard password.
//     */
//    public Camper insert(Camper camper) {
//
//        Connection conn = null;
//
//        //***************************************************
//        // INSERT
//        //***************************************************
//        try {
//            conn = DatabaseUtility.getConnection("");
//            String theStatement = "INSERT INTO Camper(firstName,lastName,dob) "
//                    + "VALUES (?,?,?)";
//            PreparedStatement stmt = conn.prepareStatement(theStatement);
//            stmt.setString(1, camper.getFirstName());
//            stmt.setString(2, camper.getLastName());
//            stmt.setString(3, camper.getDob());
//
//            stmt.executeUpdate();
//
//        } catch (SQLException sqle) {
//            System.out.println("sql exception caught");
//            sqle.printStackTrace();
//        }
//
//        return camper;
//    }
//
//}
