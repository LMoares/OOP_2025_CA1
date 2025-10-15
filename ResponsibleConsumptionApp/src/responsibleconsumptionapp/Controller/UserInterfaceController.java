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

    public void show() {
        ui.setVisible(true);
    }

    public void repaint() {
        ui.repaint();
        ui.revalidate();
    }

    public void initializePanels() {
        login_panel = new LoginPanel();// login panel testing
        login_panel.setLoginPanelListener(this);
        ui.initializeCards(login_panel, "Login");

        SustConPanel sustCon_panel = new SustConPanel();
        ui.initializeCards(sustCon_panel, "menu2");
    }

    public void onRegisterButtonClicked(MouseEvent evt) {
        
        String username = login_panel.getNewUserUsername().getText();
        String fullname = login_panel.getNewUserFullName().getText();
        char[] passwordChars = login_panel.getNewUserPassword().getPassword();
        String passwordText = new String(passwordChars);
        
        if (!passwordText.equals("")) {
            System.out.println("Registering New User:");
            System.out.println("username: " + username);
            System.out.println("password: " + passwordText);
            System.out.println("fullname: " + fullname);

            LoginService newUserLogin = new LoginService();
            newUserLogin.registerNewUser(
                username,
                passwordText,
                fullname
            );
            //resets input fields to default
            login_panel.resetNewUserUsername();
            login_panel.resetNewUserPassword();
            login_panel.resetNewUserFullName();
        }else {
            System.out.println("Registration requires password");
        }
    }
    
    public void onLoginButtonClicked(MouseEvent evt) {
        
        String username = login_panel.getExistingUserUsername().getText();
        char[] passwordChars = login_panel.getExistingUserPassword().getPassword();
        String passwordText = new String(passwordChars);
        
        if (!passwordText.equals("")) {
            System.out.println("Login Existing User:");
            System.out.println("username: " + username);
            System.out.println("password: " + passwordText);
            //resets input fields to default
            login_panel.resetExistingUserUsername();
            login_panel.resetExistingUserPassword();
        }else {
            System.out.println("Login requires password");
        }
    }
}
