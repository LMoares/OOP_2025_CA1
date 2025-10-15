/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Controller;

import java.awt.event.MouseEvent;
import responsibleconsumptionapp.View.*;
import responsibleconsumptionapp.Lorenzo_Package.*;
import responsibleconsumptionapp.Service.LoginService;

/*
 * Classname UserInterfaceController.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserInterfaceController {

    private UserInterfaceView ui;
    private LoginPanel login_panel;

    public UserInterfaceController() {
        ui = new UserInterfaceView();

        initializePanels();
        ui.generateNavbar();
        ui.generateCards();
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
        login_panel.setPanelListener(this); //gives uicontroller reference to login_panel obj for event listening
        ui.initializeCards(login_panel, "Login");

        SustConPanel sustCon_panel = new SustConPanel();
        ui.initializeCards(sustCon_panel, "menu2");
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
                    username,
                    password,
                    fullname
            );
            //resets input fields to default
            login_panel.resetNewUserUsername();
            login_panel.resetNewUserPassword();
            login_panel.resetNewUserFullName();
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
        } else {
            System.out.println("Login requires password");
        }
    }
}
