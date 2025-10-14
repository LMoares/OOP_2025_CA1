/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Service;
import responsibleconsumptionapp.Model.User;
/*
 * Classname UserService.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserService {
    private User user;
    
    public UserService(){}
    
    public void registerNewUser(String name, String username, String password) {
        //TODO - requires logic to determine if user already exists
        System.out.println("User service passing user data to user for creation");
        user = new User(name, username, password);
    }
    
    public void loginExistingUser(String name, String username, String password) {
        //TODO requires logic to check if user exists and pull existing information from db
        //default cf_score for existing user 
        int cf_score = 100;
        
        user = new User(name, username, password, cf_score);
    }
}
