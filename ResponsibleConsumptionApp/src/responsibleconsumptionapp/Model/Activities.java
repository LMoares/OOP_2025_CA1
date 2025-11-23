/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * Classname Activities.java
 * Date 22/12/2025
 * @Jeán Walton, 23405040
 */
@Deprecated
public class Activities {
    
    private Map<String, Integer> userPoints = new HashMap<>();
    private String fileName = "points.txt";
    
    public Activities() {
        
    }
    
    public void addPoints(String userID, int amount){
        int currentPoints = userPoints.getOrDefault(userID, 0);
        int sumPoints = currentPoints + amount;
        userPoints.put(userID, sumPoints);
            
    }
    //get a user's total 
    public int getTotal(String userID){
        int currentPoints = userPoints.getOrDefault(userID, 0);
        return currentPoints;
    }
    
    //write this to a file(userID, value points)
    public void writeFile(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            //itterate through userPoints array and print line bey line userID|amount
            for(Map.Entry<String, Integer> entry : userPoints.entrySet()){
                String userID = entry.getKey();
                int amount = entry.getValue();
                bw.write(userID + "|" + amount);
                bw.newLine();
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error writing to file " + e.getMessage());
        }
    }
    
    //read the file from points.txt to update
    public void readFile(){
        //read a file line by line 
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = "";
            while((line = br.readLine()) != null){
                String[] array = line.split("\\|");
                String userID = array[0];
                int amount = Integer.parseInt(array[1]);
                userPoints.put(userID, amount);
            }
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error reading from a file " + e.getMessage());
        }
    }
    
}
