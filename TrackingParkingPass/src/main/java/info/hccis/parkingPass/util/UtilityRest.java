package info.hccis.parkingPass.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author bjmaclean
 * @since Nov 17, 2017
 */
public class UtilityRest {

    /**
     * This method will call the rest web service and give back the json
     *
     * @since 20171117
     * @author BJM
     */
    public static String getJsonFromRest(String urlString) {

        String content = "";
        try {

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("BJM - 200 wasn't returned from the service!");
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server (response="+conn.getResponseCode()+").... \n");
            while ((output = br.readLine()) != null) {
                content += output;
            }

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("BJM content from rest="+content);
        return content;
    }
}
