/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Controller;

import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import responsibleconsumptionapp.View.*;
import responsibleconsumptionapp.Lorenzo_Package.*;
import responsibleconsumptionapp.Model.User;
import responsibleconsumptionapp.Service.LoginService;

/*
 * Classname UserInterfaceController.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserInterfaceController {

    private UserInterfaceView ui;
    private User user;
    private Map<String, IControllable> panels = new HashMap<>(){{
       put("Login", new LoginPanel());
       put("UserPortal", new UserPortal());
    }};

    public UserInterfaceController() {
        ui = new UserInterfaceView();

        initializePanels();
        ui.generateNavbar();
        ui.generateCards();
        //initial ui view panel
        ui.showCard("Login");
    }

    public void showWindow() {
        ui.setVisible(true);
    }

    public void repaint() {
        ui.repaint();
        ui.revalidate();
    }

    public void initializePanels() {
        SustConPanel sustCon_panel = new SustConPanel();
        ui.initializeCards(sustCon_panel, "menu2");

        //iterate through panel dictionary to set panelListener and intialize panels in card layout
        for (Map.Entry<String, IControllable> card : panels.entrySet()) {
            String key = card.getKey();
            IControllable panel = card.getValue();
            
            panel.setPanelListener(this);
            //Cast panel from IControllable to JPanel for card initialization
            JPanel panelfix = (JPanel) panel;
            ui.initializeCards(panelfix, key);
        }
    }

    public void onRegisterButtonClicked(String username, String fullname, String password) {
        //this method runs once login panel detects user interaction with register button
        /*String username = login_panel.getNewUserUsername();
        String fullname = login_panel.getNewUserFullName();

        String password = login_panel.getNewUserPassword();*/

        if (!password.equals("")) {
            System.out.println("Registering New User:");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println("fullname: " + fullname);

            LoginService newUserLogin = new LoginService();
            newUserLogin.registerNewUser(
                    fullname,
                    username,
                    password
            );
            
            user = newUserLogin.getUser();
            
            //cast UserPortal object from IControllable reference back to UserPortal for method calls
            UserPortal up = (UserPortal) panels.get("UserPortal");
            up.setUser(user);
            up.setFullName();
            
            //on successful login, panel changes to home panel, navbar made visible
            ui.displayNavbar();
            ui.showCard("UserPortal");
            //repaints userinterface view to display new panels
            repaint();
        } else {
            System.out.println("Registration requires password");
        }
    }

    public void onLoginButtonClicked(String username, String password) {
        //this method runs once login panel detects user interaction with login button

        if (!password.equals("")) {
            System.out.println("Login Existing User:");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            
            //on successful login, panel changes to home panel, navbar made visible 
            ui.displayNavbar();
            ui.showCard("UserPortal");
            repaint();
            
        } else {
            System.out.println("Login requires password");
        }
    }
}
