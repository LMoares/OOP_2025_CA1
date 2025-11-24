/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Classname ConsultationFileWriter.java
 * Date 23/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class ConsultationFileWriter {
    private String path = "./src/responsibleconsumptionapp/Lorenzo_Package/Consultations/";
    private String username;
    
    public ConsultationFileWriter(String username) {
        this.username = username;
    }
    
    public void writeFile(String message) {
        //get current date and time to ensure that file names are unique
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        //file name e.g. alice123_20251124_165330.txt
        //username_date_time
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path+username+"_"+currentTime+".txt"))) {
            bw.append(message);
        }catch (IOException e) {
            System.out.println("Error writing to file: "+e.getMessage());
        }catch (Exception e) {
            System.out.println("Error at file write: "+e.getMessage());
        }
    }
}
