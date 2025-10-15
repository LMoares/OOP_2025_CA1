/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Controller;

import java.awt.event.MouseEvent;
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
    private LoginPanel login_panel;
    private UserPortal up_panel;

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
        login_panel = new LoginPanel();
        login_panel.setPanelListener(this); //gives uicontroller reference to login_panel obj for event listening - requires panel to implement IControllable interace
        ui.initializeCards(login_panel, "Login");

        SustConPanel sustCon_panel = new SustConPanel();
        ui.initializeCards(sustCon_panel, "menu2");
        
        up_panel = new UserPortal();
        ui.initializeCards(up_panel, "UserPortal");
    }

    public void onRegisterButtonClicked(MouseEvent evt) {
        //this method runs once login panel detects user interaction with register button
        String username = login_panel.getNewUserUsername();
        String fullname = login_panel.getNewUserFullName();

        String password = login_panel.getNewUserPassword();

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
            up_panel.setUser(user);
            up_panel.setFullName();
            
            
            //resets input fields to default
            login_panel.resetNewUserUsername();
            login_panel.resetNewUserPassword();
            login_panel.resetNewUserFullName();
            
            //on successful login, panel changes to home panel, navbar made visible
            ui.displayNavbar();
            ui.showCard("UserPortal");
            //repaints userinterface view to display new panels
            repaint();
        } else {
            System.out.println("Registration requires password");
        }
    }

    public void onLoginButtonClicked(MouseEvent evt) {
        //this method runs once login panel detects user interaction with login button

        String username = login_panel.getExistingUserUsername();
        String password = login_panel.getExistingUserPassword();

        if (!password.equals("")) {
            System.out.println("Login Existing User:");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            //resets input fields to default
            login_panel.resetExistingUserUsername();
            login_panel.resetExistingUserPassword();
            
            //on successful login, panel changes to home panel, navbar made visible 
            ui.displayNavbar();
            ui.showCard("UserPortal");
            repaint();
            
        } else {
            System.out.println("Login requires password");
        }
    }
}
