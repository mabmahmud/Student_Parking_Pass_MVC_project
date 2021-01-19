
package info.hccis.parkingPass.rest;

/**
 *Used if all attributes not provided
 * 
 * @author mrahman2
 * @since 6-Nov-2020
 * 
 */
public class AllAttributesNeededException extends Exception{
    public AllAttributesNeededException(String message){
        super(message);
    }
}
