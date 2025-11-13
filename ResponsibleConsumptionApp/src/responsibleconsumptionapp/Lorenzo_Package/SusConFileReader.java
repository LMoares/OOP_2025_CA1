/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Classname SusConFileReader.java
 * Date 13/11/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class SusConFileReader {
    private String SusConIntro = new String();
    private String SusConSP = new String();
    private String SusConRB = new String();
    private String SusConCT = new String();
    
    public SusConFileReader() {
        StringBuilder sb = new StringBuilder();
        String SusConData;
        //Sustainable Consumption Default Text Area Data
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConIntro.txt"))){
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                sb.append(SusConData+"\n");
                SusConData = br.readLine();
            }
            SusConIntro = sb.toString();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConSP.txt"))){
            
            sb = new StringBuilder();
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                sb.append(SusConData+"\n");
                SusConData = br.readLine();
            }
            SusConSP = sb.toString();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConRB.txt"))){
            
            sb = new StringBuilder();
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                sb.append(SusConData+"\n");
                SusConData = br.readLine();
            }
            SusConRB = sb.toString();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConCT.txt"))){
            
            sb = new StringBuilder();
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                sb.append(SusConData+"\n");
                SusConData = br.readLine();
            }
            SusConCT = sb.toString();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
    }

    public String getSusConSP() {
        return SusConSP;
    }

    public String getSusConRB() {
        return SusConRB;
    }

    public String getSusConCT() {
        return SusConCT;
    }
    
    public String getSusConIntro(){
        return SusConIntro;
    }
}
