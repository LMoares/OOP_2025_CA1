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
    private String SusConData;
    private String SusConIntro = new String();
    private String SusConSP = new String();
    private String SusConRB = new String();
    private String SusConCT = new String();
    
    public SusConFileReader() {
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConIntro.txt"))){
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                SusConIntro = SusConIntro +"\n"+ SusConData;
                SusConData = br.readLine();
            }
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConSP.txt"))){
            
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                SusConSP = SusConSP +"\n"+ SusConData;
                SusConData = br.readLine();
            }
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConRB.txt"))){
            
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                SusConRB = SusConRB +"\n"+ SusConData;
                SusConData = br.readLine();
            }
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }catch(IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConCT.txt"))){
            
            SusConData = new String(); //reset SusConData
            SusConData = br.readLine();
            while(SusConData != null) {
                SusConCT = SusConCT +"\n"+ SusConData;
                SusConData = br.readLine();
            }
            
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
