/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package;
import java.util.ArrayList;
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
    ConsultationFileHandler fw;
    ArrayList<String> filePaths = new ArrayList<String>();
    
    public SustainableConsumption(UserService userService, User user) {
        //passes information to base class constructor
        super(userService, user);
        
    }
    
    //returns filepath for dictionary key
    public String getLastFilePath() {
        return filePaths.getLast();
    }
    
    public void deleteFile(String filePath) {
        fw.deleteFile(filePath);
    }
    
    //Following methods have only been implemented in Consultation class
    public void setFWDetails() {
        fw = new ConsultationFileHandler(user.getUsername());
    }
    
    //consultation form valid, moved to file creation
    public void writeFile(EnergyConsultation ec) {
        filePaths.add(fw.saveConsultation(ec.toString()));
        addCurrentPoints(20);
    }
}
