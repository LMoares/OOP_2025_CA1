/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package;
import responsibleconsumptionapp.Lorenzo_Package.Model.EnergyConsultation;
import responsibleconsumptionapp.Model.Focus;
import responsibleconsumptionapp.Model.User;
import responsibleconsumptionapp.Service.UserService;

/*
 * Classname SustainableConsumption.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class SustainableConsumption extends Focus {
    ConsultationFileWriter fw;
    
    public SustainableConsumption(UserService userService, User user) {
        //passes information to base class constructor
        super(userService, user);
    }
    
    //Following methods have only been implemented in Consultation class
    public void setFWDetails() {
        fw = new ConsultationFileWriter(user.getUsername());
    }
    
    public void writeFile(EnergyConsultation ec) {
        fw.writeFile(ec.toString());
        addCurrentPoints(20);
    }
}
