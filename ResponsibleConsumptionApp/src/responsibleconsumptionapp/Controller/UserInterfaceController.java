/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import responsibleconsumptionapp.Jean_Package.ChemicalWasteGUI;
import responsibleconsumptionapp.Jean_Package.NonRecyclableWasteGUI;
import responsibleconsumptionapp.Jean_Package.RecyclableWasteGUI;
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
    private Map<String, IControllable> panels = new HashMap<>() {
        {
            put("Login", new LoginPanel());
            put("ChemWaste", new ChemicalWasteGUI());
            put("Recyclable", new RecyclableWasteGUI());
            put("NonRecyclable", new NonRecyclableWasteGUI());
            put("UserPortal", new UserPortal());
            put("SusCon", new SusConPanel());
            put("SusConQuestionaire", new SusConQuestionnaire());
            put("NewUserRegistration", new NewUserRegistration());
            put("SusConConsultation", new SusConConsultation());
        }
    };

    public UserInterfaceController() {
        ui = new UserInterfaceView(this);
        
        initializePanels();
        ui.generateNavbar();
        ui.generateCards();
        //initial ui view panel
        ui.showPanel("Login");
    }

    public void showWindow() {
        ui.setVisible(true);
    }

    public void repaint() {
        ui.repaint();
        ui.revalidate();
    }

    public void initializePanels() {

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

    public void changePanel(String panel) {
        //TODO abstract following code to work for all panels
        //ensures SusCon panel data is reset when user navigates to panel
        if (panel.equals("SusCon")) {
            //create reference to suscon panel - runs reset method on activation
            SusConPanel suscon = (SusConPanel) panels.get("SusCon");
            suscon.resetText();
        }else if (panel.equals("SusConConsultation")) {
            SusConConsultation susconCon = (SusConConsultation) panels.get("SusConConsultation");
            susconCon.setUser();
        }
        ui.showPanel(panel);
    }

    public void removeNavbar() {
        ui.removeNavbar();
    }
    
    public User getUser() {
        return user;
    }

    public void registrationComplete(int carbonFootprintScore) {
        user.setCf_score(carbonFootprintScore);

        //on successful login, panel changes to home panel, navbar made visible
        ui.displayNavbar();
        changePanel("UserPortal");
    }

    public void onRegisterButtonClicked(String username, String fullname, String password) {
        //this method runs once login panel detects user interaction with register button

        //ensures password is not empty and is not a series of whitespaces
        //if (password != null && !password.trim().isEmpty()) {
        if(true) { //placeholder for login logic
            System.out.println("Registering New User:");

            LoginService newUserLogin = new LoginService();
            newUserLogin.registerNewUser(fullname, username, password);

            user = newUserLogin.getUser();
            //on successful login, panel changes to home panel, navbar made visible
            changePanel("NewUserRegistration");
        } else {
            System.out.println("Registration requires password");
        }
    }

    public void onLoginButtonClicked(String username, String password) {
        //this method runs once login panel detects user interaction with login button
        //ensures password is not empty and is not a series of whitespaces
        //if (password != null && !password.trim().isEmpty()) {
        
        if(true) { //placeholder for login logic
            System.out.println("Login Existing User:");
            LoginService userLogin = new LoginService();
            userLogin.loginExistingUser(username, password);
            user = userLogin.getUser();
            //on successful login, panel changes to home panel, navbar made visible 
            ui.displayNavbar();
            UserPortal up = (UserPortal)panels.get("UserPortal");
            up.setUser();
            changePanel("UserPortal");
        } else {
            System.out.println("Login requires password");
        }
    }
}
