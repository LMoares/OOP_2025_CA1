/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Lorenzo_Package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Classname ConsultationFileHandler.java
 * Date 23/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class ConsultationFileHandler {

    private String path = "./src/responsibleconsumptionapp/Lorenzo_Package/Consultations/";
    private String username;

    public ConsultationFileHandler(String username) {
        this.username = username;
    }

    public String saveConsultation(String message) {
        //get current date and time to ensure that file names are unique
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filepath = path + username + "_" + currentTime + ".txt";
        //file name e.g. alice123_20251124_165330.txt
        //username_date_time
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + username + "_" + currentTime + ".txt"))) {
            bw.append(message);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error at file write: " + e.getMessage());
        }
        
        //file path saved for deletion logic
        return filepath;
    }
    
    //File deleted if user decides to remove consultation added in current session
    public void deleteFile(String filePath) {
        File file = new File(filePath);
        file.delete();
    }

}
