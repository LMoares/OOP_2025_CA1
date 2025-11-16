/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package.Model;

/*
 * Classname RecyclingConsultation.java
 * Date 16/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class RecyclingConsultation extends EnergyConsultation {
    private int weight, greenBinQuantity, brownBinQuantity;
    private String interval;
    
    public RecyclingConsultation(int weight, int greenBinQuantity, int brownBinQuantity, String interval, String fullname, String add1, String add2, String city, String county, String eircode) {
        super(fullname, add1, add2, city, county, eircode);
        this.weight = weight;
        this.greenBinQuantity = greenBinQuantity;
        this.brownBinQuantity = brownBinQuantity;
        this.interval = interval;
    }
    
    public RecyclingConsultation() {
        this(0, 0, 0, "Undecided", "NA","NA","NA","NA","NA","NA");
    }

    public int getWeight() {
        return weight;
    }

    public int getGreenBinQuantity() {
        return greenBinQuantity;
    }

    public int getBrownBinQuantity() {
        return brownBinQuantity;
    }

    public String getInterval() {
        return interval;
    }
    
    @Override
    public String toString() {
        return super.toString()+"\n--Recycling Consultation--\nGreen Bin Quantity - "+greenBinQuantity+"\nBrown Bin Quantity - "+brownBinQuantity+"\nWeight - "+weight+"kg\nInterval - "+interval+" weeks";
    }
}
