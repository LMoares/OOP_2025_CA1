/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Service;

import java.io.FileInputStream;
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

    public UserService() {
        //retrieve user data stored in file users.dat and saves to local ArrayList users variable
        getUsersData();
    }

    public void registerNewUser(String name, String username, String password) {
        //placeholder user for checking registration status
        User tempUser = users.get(searchUsers(username));
        if (tempUser == null) {
            //user does not exist -- create new user
            System.out.println("User service passing user data to user for creation");
            user = new User(name, username, password);
            System.out.println("Adding user: " + user.getName() + " to list of users");
            users.add(user);
        }else {
            System.out.println("User already exists. Please choose another Username");
        }

    }

    public boolean loginExistingUser(String username, String password) {
        //placeholder user for checking login status
        User tempUser = users.get(searchUsers(username));
        if (tempUser == null) {
            //user does not exist
            return false;
        } else if (tempUser.getPassword().equals(password)) {
            //user exists and password does match
            user = tempUser;
            return true;
        } else {
            //user exists but password does not match
            return false;
        }
    }

    public int searchUsers(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCFScore(int cfscore) {
        user.setCf_score(cfscore);
    }

    public void getUsersData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./src/responsibleconsumptionapp/Data/users.dat"))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            //if users.dat file is empty, or class is different than one stored in file (e.g. new data member in User class) list of users set to new users - previous data is lost
            users = new ArrayList<User>();
        } catch (Exception e) {
            System.out.println("Error at file read: " + e.getMessage());
        }
    }

    public void saveChanges() {
        //overwrite's previous user data stored in users.dat to new user data
        users.set(searchUsers(user.getUsername()), user);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./src/responsibleconsumptionapp/Data/users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error at file write: " + e.getMessage());
        }
    }
}
