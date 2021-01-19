
package info.hccis.parkingPass.entity;

import info.hccis.parkingPass.entity.*;
import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

/**
 * Represents a student parking pass entity
 *
 * @author bjm
 * @since 20201001
 */
public class ParkingPass implements Serializable{


    private String firstName, lastName, licensePlate, program;

    public ParkingPass() {
    }

    public ParkingPass(String firstName, String lastName, String licensePlate, String program) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licensePlate = licensePlate;
        this.program = program;
    }
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
      
    public void display(){
        System.out.println(this.toString());
    }
    
    public String toString() {
        return ") Name: "+firstName+" "+lastName+" License Plate: "+licensePlate+" Program: "+program;
    }

}
