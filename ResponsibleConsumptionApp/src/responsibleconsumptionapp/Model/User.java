/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Model;

import java.io.Serializable;

/*
 * Classname User.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class User implements Serializable{
    private String name, username, password;
    private int cf_score, ef_score;
    
    //constructor for registering new users that requires their cfscore to be set after registry - Lorenzo
    public User(String name, String username, String password){
        System.out.println("New user created");
        this.name = name;
        this.username = username;
        this.password = password;
        this.cf_score = 0;
        this.ef_score = 0;
        
    }
    //constructor for existing users whose information will be provided by leaderboard data - Lorenzo
    public User(String name, String username, String password, int cf_score) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.cf_score = cf_score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCf_score() {
        return cf_score;
    }

    public void setCf_score(int cf_score) {
        this.cf_score = cf_score;
    }

    public int getEf_score() {
        return ef_score;
    }

    public void setEf_score(int ef_score) {
        this.ef_score += ef_score;
    }
    
    
}
