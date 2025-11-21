/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Service;

import responsibleconsumptionapp.Model.User;

/*
 * Classname LoginService.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class LoginService {
    private UserService userService;
    
    //requires some connection to leaderboard service to query for existing users
    public LoginService(){
        userService = new UserService();
    }
    
    public User registerNewUser(String name, String username, String password) {
        System.out.println("Login passing user data to user service");
        userService.registerNewUser(name, username, password);
        
        return userService.getUser();
    }
    
    public User loginExistingUser(String username, String password) {
        userService.loginExistingUser(username,password);
        return userService.getUser();
    }
    
    public User getUser() {
        return userService.getUser();
    }
    
    
}
