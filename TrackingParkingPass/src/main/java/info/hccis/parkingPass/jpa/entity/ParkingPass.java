/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.hccis.parkingPass.jpa.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author mrahman2
 */
@Entity
@Table(name = "parkingpass")
//@NamedQueries({
//    @NamedQuery(name = "ParkingPass.findAll", query = "SELECT p FROM Parkingpass p")})
public class ParkingPass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 20)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 6)
    @Column(name = "licensePlate")
    private String licensePlate;
    @Size(max = 140)
    @Column(name = "program")
    private String program;
    @Size(max = 20)
    @Column(name = "createdDateTime")
    private String createdDateTime;

    public ParkingPass() {
    }

    public ParkingPass(Integer id) {
        this.id = id;
    }

//    public ParkingPass(int id, String firstName, String lastName, String licensePlate, String program) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public ParkingPass(Integer id, String firstName, String lastName, String licensePlate, String program) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licensePlate = licensePlate;
        this.program = program;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParkingPass)) {
            return false;
        }
        ParkingPass other = (ParkingPass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.hccis.parkingPass.jpa.entity.ParkingPass[ id=" + id + " ]";
    }

}
