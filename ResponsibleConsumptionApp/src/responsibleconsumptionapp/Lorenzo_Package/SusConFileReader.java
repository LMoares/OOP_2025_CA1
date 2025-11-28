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

//class responsible for reading SusConData to build information stored in textarea on Sustainable Consumption panel
public class SusConFileReader {

    private String SusConIntro = new String();
    private String SusConSP = new String();
    private String SusConRB = new String();
    private String SusConCT = new String();

    public SusConFileReader() {
        //Sustainable Consumption Default Text Area Data
        setTextData();
    }

    private void setTextData() {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Lorenzo_Package/SusConData.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (!line.equals("end_of_intro")) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            //set intro string to sb value
            SusConIntro = sb.toString();
            //reset reader and stringbuilder object
            line = br.readLine();
            sb = new StringBuilder();

            while (!line.equals("end_of_sp")) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            SusConSP = sb.toString();
            //reset reader and stringbuilder object
            line = br.readLine();
            sb = new StringBuilder();

            while (!line.equals("end_of_rb")) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            SusConRB = sb.toString();
            //reset reader and stringbuilder object
            line = br.readLine();
            sb = new StringBuilder();

            //last file segment
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            SusConCT = sb.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
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

    public String getSusConIntro() {
        return SusConIntro;
    }
}
