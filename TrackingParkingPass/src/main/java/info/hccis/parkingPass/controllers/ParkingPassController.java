package info.hccis.parkingPass.controllers;

import info.hccis.parkingPass.bo.ParkingPassBO;
import info.hccis.parkingPass.dao.ParkingPassDAO;
import info.hccis.parkingPass.jpa.entity.ParkingPass;
import info.hccis.parkingPass.repositories.ParkingPassRepository;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the parking pass functionality of the site
 *
 * @since 20200930
 * @author CIS2232
 *
 * Modified by Mohammad Rahman Date : 20201114 
 * : 20201025
 */


@Controller
@RequestMapping("/parkingpass")
public class ParkingPassController {
     private final ParkingPassRepository parkingpassRepository;

    public ParkingPassController(ParkingPassRepository ppr) {
        parkingpassRepository = ppr;
    }


    /**
     * Page to allow user to view Parking passes.
     *
     * @since 20200528
     * @author BJM (modified from Fred/Amro's project)
     * @ Modified by Mohammad Rahman Date : 20201114 
     */
    @RequestMapping("/list")
    public String list(Model model) {
         ParkingPassBO parkingPassBO = new ParkingPassBO();

        //Go get the bookings from the database.
        ArrayList<ParkingPass> parkingpasses = parkingPassBO.selectAll();
        //ArrayList<Booking> bookings = (ArrayList<Booking>) bookingRepository.findAll();
        model.addAttribute("parkingpasses", loadParkingPass());
        // Showing rows for parking passes
        model.addAttribute("findNameMessage", "Total number of parking passes loaded: " + parkingpasses.size() + "");


        return "parkingpass/list";
    }
   

    /**
     * Page to allow user to add a parking pass
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project
     * 
     * Modified by Mohammad Rahman Date : 20201114 
     */
    @RequestMapping("/add")
    
   
    public String add(Model model) {
        model.addAttribute("message", "Add a parkingpass");

        ParkingPass parkingpass = new ParkingPass();
        parkingpass.setId(0);
        model.addAttribute("parkingpass", parkingpass);

        //model.addAttribute("parkingpasses", loadParkingPass());
         ArrayList<ParkingPass> found = (ArrayList<ParkingPass>) parkingpassRepository.findAll();
        // calculates row after added

        model.addAttribute("findNameMessage", "Total Number of Parking passes loaded: " + found.size() + "");
        

        return "parkingpass/add";
    }
   

    /**
     * Page to allow user to submit the add a parking pass. It will put the parking pass in
     * the database.
     *
     * @since 20201002
     * @author BJM (modified from Fred/Amro's project)
     * 
     * Modified by Mohammad Rahman Date : 20201114 
     */
    @RequestMapping("/addSubmit")
    
     public String addSubmit(Model model, @Valid @ModelAttribute("parkingpass") ParkingPass parkingpass, BindingResult result) {

        //If errors put the object back in model and send back to the add page.
        if (result.hasErrors()) {
            System.out.println("Validation error");
            return "parkingpass/add";
        }

        //Save that camper to the database
       // ParkingPassBO parkingPassBO = new ParkingPassBO();
       // parkingPassBO.insert(parkingpass);
       // save parking pass using repository
        parkingpassRepository.save(parkingpass);

        //reload the list
       // ArrayList<ParkingPass> parkingpasses = parkingPassBO.selectAll();
       // ArrayList<ParkingPass> parkingpasses = (ArrayList<ParkingPass>) parkingpassRepository.findAll();


        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successAddString = rb.getString("message.parkingpass.saved");

        model.addAttribute("message", successAddString);
        
        model.addAttribute("parkingpasses", loadParkingPass());
         ArrayList<ParkingPass> found = (ArrayList<ParkingPass>) parkingpassRepository.findAll();
        // calculates row 

        model.addAttribute("findNameMessage", "Total Number of Parking passes loaded: " + found.size() + "");

        return "parkingpass/list";

    }
   

    /**
     * Page to allow user to edit a parking pass
     *
     * @since 20201007
     * @author BJM (modified from Fred/Amro's project)
     * 
     * Modified by Mohammad Rahman Date : 20201114 
     */
    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("BJTEST - id=" + id);
        
        Optional<ParkingPass> found = parkingpassRepository.findById(id);


        //reload the list
       // ParkingPassBO parkingPassBO = new ParkingPassBO();

       // ArrayList<ParkingPass> parkingpasses = parkingPassBO.selectAll();

       // ParkingPass found = null;
       // for (ParkingPass current : parkingpasses) {
        //    if (current.getId() == id) {
        //        found = current;
        //        break;
        //    }
       // }

        model.addAttribute("parkingpass", found);
        return "parkingpass/add";
    }
    
    /**
     * Page to allow user to delete a parking pass
     *
     * @since 20201009
     * @author BJM
     * 
     * Modified by Mohammad Rahman Date : 20201114 
     */
    @RequestMapping("/delete")
     public String delete(Model model, HttpServletRequest request) {

        String idString = (String) request.getParameter("id");
        int id = Integer.parseInt(idString);
        System.out.println("BJTEST - id=" + id);
        parkingpassRepository.deleteById(id);
        
        //Optional<ParkingPass> found = parkingpassRepository.findById(id);


        ArrayList<ParkingPass> found = (ArrayList<ParkingPass>) parkingpassRepository.findAll();
        // calculates row after deleted

        model.addAttribute("findNameMessage", "Total Number of Parking passes loaded: " + found.size() + "");

        model.addAttribute("parkingpasses", found);
        String propFileName = "messages";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String successAddString = rb.getString("message.parkingpass.deleted") + " (" + id + ")";

        model.addAttribute("message", successAddString);

      

        return "parkingpass/list";
        //model.addAttribute("parkingpasses", parkingpass);

        //reload the list
        //ParkingPassBO parkingPassBO = new ParkingPassBO();
        //boolean deleted = parkingPassBO.delete(id);

        //String propFileName = "messages";
        //ResourceBundle rb = ResourceBundle.getBundle(propFileName);

        //String successString;
        //try {
        //    parkingpassRepository.deleteById(id);
        //    successString = rb.getString("message.camper.deleted") + " (" + id + ")";
        //} catch (EmptyResultDataAccessException e) {
         //   successString = rb.getString("message.camper.deleted.error") + " (" + id + ")";
        //}

        //model.addAttribute("message", successString);
       
    }
     public ArrayList<ParkingPass> loadParkingPass() {
        ArrayList<ParkingPass> parkingpass = (ArrayList<ParkingPass>) parkingpassRepository.findAll();
        
        return parkingpass;

    }


}
