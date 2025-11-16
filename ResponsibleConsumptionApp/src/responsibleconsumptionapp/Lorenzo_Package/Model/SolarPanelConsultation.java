/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package.Model;

/*
 * Classname SolarPanelConsultation.java
 * Date 16/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class SolarPanelConsultation extends EnergyConsultation {
    private int quantity;
    private String type;
    
    public SolarPanelConsultation(int quantity, String type, String fullname, String add1, String add2, String city, String county, String eircode) {
        super(fullname, add1, add2, city, county, eircode);
        this.quantity = quantity;
        this.type = type;
    }
    
    public SolarPanelConsultation() {
        this(0,"Undecided","NA","NA","NA","NA","NA","NA");
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
    
    @Override
    public String toString() {
        return super.toString()+"\n--Solar Panel Consultation--\nQuantity: "+quantity+"\nType: "+type;
    }
}
