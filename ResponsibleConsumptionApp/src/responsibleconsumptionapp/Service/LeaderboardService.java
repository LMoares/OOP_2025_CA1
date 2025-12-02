/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Service;

import responsibleconsumptionapp.Controller.UserInterfaceController;
import responsibleconsumptionapp.Model.User;

/*
 * Classname LeaderboardService.java
 * Date 1/12/2025
 * @author Aaron Byrne 24437136
 */
public class LeaderboardService {

    private UserInterfaceController UICListener;//Controller reference 
    private int userEfScore = 0;//Initalisation
    private String userName = "";
    private String message = "";

    User user;

    public void setUserDetails(User user) {
        this.user = user;
    }

    public void scoreSet() {
        userEfScore = user.getEf_score();//Getting users score and inputted name
        userName = user.getName();

        if (userEfScore >= 0 && userEfScore <= 99) {
            message = "You are 5th in the leaderboard\n"
                    + "CURRENT LEADERBOARD:\n"
                    + "1.Jane 400 points\n"
                    + "2.Lucas 300 points\n"
                    + "3.Dustin 200 points\n"
                    + "4.Steve 100 points\n"
                    + "5. " + userName +" "+ userEfScore + "points";

        } else if (userEfScore >= 100 && userEfScore <= 199) {
            message = "You are 4th in the leaderboard\n"
                    + "CURRENT LEADERBOARD:\n"
                    + "1.Jane 400 points\n"
                    + "2.Lucas 300 points\n"
                    + "3.Dustin 200 points\n"
                    + "4. " + userName +" "+ userEfScore + " points \n"
                    + "5.Steve 100 points";

        } else if (userEfScore >= 200 && userEfScore <= 299) {
            message = "You are 3rd in the leaderboard\n"
                    + "CURRENT LEADERBOARD:\n"
                    + "1.Jane 400 points\n"
                    + "2.Lucas 300 points\n"
                    + "3. " + userName +" "+ userEfScore + "points \n"
                    + "4.Dustin 200  points\n"
                    + "5.Steve 100 points\n";

        } else if (userEfScore >= 300 && userEfScore <= 399) {
            message = "You are 2rd in the leaderboard\n"
                    + "CURRENT LEADERBOARD:\n"
                    + "1.Jane 400 points\n"
                    + "2. " + userName +" "+ userEfScore + "points \n"
                    + "3.Lucas 300 points\n"
                    + "4.Dustin 200 points\n"
                    + "5.Steve 100 points\n";

        } else if (userEfScore >= 400) {
            message = "You are 1st in the leaderboard\n"
                    + "CURRENT LEADERBOARD:\n"
                    + "1." + userName +" "+ userEfScore + " points \n"
                    + "2.Jane 400 points\n"
                    + "3.Lucas 300 points\n"
                    + "4.Dustin 200 points\n"
                    + "5.Steve 100 points\n";

        } else {
            message = "An error has occured";

        }

    }

    public String getLeaderboardMessage() {
        return message;//Method to return value of message based on users score
    }
}
