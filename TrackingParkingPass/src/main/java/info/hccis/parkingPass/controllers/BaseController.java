package info.hccis.parkingPass.controllers;

import info.hccis.parkingPass.util.UtilityRest;
import info.hccis.parkingPass.util.CisUtility;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {

    @RequestMapping("/")
    public String home(HttpSession session) {
        
        String urlString = "http://localhost:8081/api/ParkingPassService/parkingpass";
                try {
            UtilityRest.getJsonFromRest(urlString);
        } catch (Exception e) {
            System.out.println("There was an error accessing that rest service.");
        }

                   
        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate); 
        
        
        return "index";
    }

    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
