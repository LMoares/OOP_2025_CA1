/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Service;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import responsibleconsumptionapp.Model.User;
/*
 * Classname UserService.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserService {
    private User user;
    private ArrayList<User> users;
    
    public UserService(){
        getUsersData();
    }
    
    public void registerNewUser(String name, String username, String password) {
        //TODO - requires logic to determine if user already exists
        System.out.println("User service passing user data to user for creation");
        user = new User(name, username, password);
        System.out.println("Adding user: "+user.getName()+" to list of users");
        users.add(user);
    }
    
    public boolean loginExistingUser(String username, String password) {
        boolean userFound = false;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(password)) {
                user = users.get(i);
                userFound = true;
                break;
            }
        }
        return userFound;
    }
    
    public User getUser(){
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public void setCFScore(int cfscore) {
        user.setCf_score(cfscore);
    }
    
    public void getUsersData() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/responsibleconsumptionapp/Data/users.dat"))) {
            users = (ArrayList<User>) ois.readObject();
        }catch (IOException e) {
            System.out.println("Error reading file: "+e.getMessage());
            System.out.println("List of users set to empty arraylist");
            users = new ArrayList<User>();
        }catch (Exception e) {
            System.out.println("Error at file read: "+e.getMessage());
        }
    }
    
    public void saveChanges() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/responsibleconsumptionapp/Data/users.dat"))) {
            System.out.println("Saving users... first user in list: "+users.get(0).getName());
            oos.writeObject(users);
        }catch (IOException e) {
            System.out.println("Error writing file: "+e.getMessage());
        }catch (Exception e) {
            System.out.println("Error at file write: "+e.getMessage());
        }
    }
}
