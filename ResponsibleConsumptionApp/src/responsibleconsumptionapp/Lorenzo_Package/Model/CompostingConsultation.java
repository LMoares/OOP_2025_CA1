/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package.Model;

/*
 * Classname CompostingConsultation.java
 * Date 16/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class CompostingConsultation extends EnergyConsultation {
    private double length,width;
    private String frame;
    
    public CompostingConsultation(double length, double width, String frame, String fullname, String add1, String add2, String city, String county, String eircode) {
        super(fullname, add1, add2, city, county, eircode);
        this.length = length;
        this.width = width;
        this.frame = frame;
    }
    
    public CompostingConsultation() {
        this(0.0,0.0,"Undecided","NA","NA","NA","NA","NA","NA");
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public String getFrame() {
        return frame;
    }
    
    @Override
    public String toString() {
        return super.toString()+"\n--Composting Consultation--\nFrame Type - "+frame+"\nLength - "+length+"\nWidth - "+width;
    }
}
