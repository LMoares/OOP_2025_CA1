/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package.Model;

/*
 * Classname EnergyConsultation.java
 * Date 16/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public abstract class EnergyConsultation {
    private String fullname,add1,add2,city,county,eircode;
    
    public EnergyConsultation(String fullname, String add1, String add2, String city, String county, String eircode) {
        this.fullname = fullname;
        this.add1 = add1;
        this.add2 = add2;
        this.city = city;
        this.county = county;
        this.eircode = eircode;
    }
    
    public EnergyConsultation() {
        this("NA","NA","NA","NA","NA","NA");
    }

    public String getAdd1() {
        return add1;
    }

    public String getAdd2() {
        return add2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getEircode() {
        return eircode;
    }
    
    @Override
    public String toString() {
        return "Fullname - "+fullname+"\nAddress 1 - "+add1+"\nAddress 2 - "+add2+"\nCity - "+city+"\nCounty - "+county+"\nEircode - "+eircode;
    }
    
}
