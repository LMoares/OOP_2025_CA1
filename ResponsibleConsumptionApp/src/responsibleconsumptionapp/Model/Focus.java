/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Model;

import responsibleconsumptionapp.Service.UserService;

/*
 * Classname Focus.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class Focus {
    private UserService userService;
    private User user;
    
    public Focus(UserService userService, User user ){
        this.userService = userService;
        this.user = user;
    }
    
    //get name of current user
    public String getUserName(){
        String currentUserName = user.getUsername();
        return currentUserName;
    }
    
    
    
    //get points to current user
    public int getCurrentPoints(){
      
      String currentUserName = user.getUsername();
      if(currentUserName != null){
          int currentPoints = user.getEf_score();// change to points in userServices
          return currentPoints;
      }
      return 0;
    }
    
    //add points to current user
    public void addCurrentPoints(int points){
        String currentUserName = user.getUsername();
        if(currentUserName != null){
            int currentPoints = user.getEf_score() + points;
            user.setEf_score(currentPoints); //updating the user object
            userService.saveChanges();//saving updated user object to user.dat file
        }
    }
        
}
