/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.Controller;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import responsibleconsumptionapp.Aaron_Package.bikeBookingGUI;
import responsibleconsumptionapp.Aaron_Package.custFeedbackGUI;
import responsibleconsumptionapp.Aaron_Package.tourismHomePanel;
import responsibleconsumptionapp.Jean_Package.ChemicalWasteGUI;
import responsibleconsumptionapp.Jean_Package.NonRecyclableWasteGUI;
import responsibleconsumptionapp.Jean_Package.RecyclableWasteGUI;
import responsibleconsumptionapp.View.*;
import responsibleconsumptionapp.Lorenzo_Package.*;
import responsibleconsumptionapp.Model.User;
import responsibleconsumptionapp.Service.UserService;

/*
 * Classname UserInterfaceController.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserInterfaceController {

    private UserInterfaceView ui;
    private UserService userHandler;
    private Map<String, IControllable> panels = new HashMap<>() {
        {
            put("Login", new LoginPanel());
            put("ChemWaste", new ChemicalWasteGUI());
            put("Recyclable", new RecyclableWasteGUI());
            put("NonRecyclable", new NonRecyclableWasteGUI());
            put("UserPortal", new UserPortal());
            put("SusCon", new SusConPanel());
            put("SusConQuestionnaire", new SusConQuestionnaire());
            put("NewUserRegistration", new NewUserRegistration());
            put("SusConConsultation", new SusConConsultation());
            put("tourism", new tourismHomePanel());
            put("bikeBooking", new bikeBookingGUI());
            put("customerFeedback", new custFeedbackGUI());
        }
    };

    public UserInterfaceController() {
        ui = new UserInterfaceView(this);

        initializePanels();
        ui.generateNavbar();
        ui.generateCards();
        //initial ui view panel
        ui.showPanel("Login");
        userHandler = new UserService();
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
        if(panel.equals("UserPortal")) {
            UserPortal up = (UserPortal) panels.get("UserPortal");
            up.updateEFScore();
        }else if (panel.equals("SusCon")) {
            //create reference to suscon panel - runs reset method on activation
            SusConPanel suscon = (SusConPanel) panels.get("SusCon");
            suscon.resetText();
        }else if (panel.equals("SusConConsultation")) {
            SusConConsultation susconCon = (SusConConsultation) panels.get("SusConConsultation");
            susconCon.setUser();
        }else if (panel.equals("Login")) {
            //user has been logged out
            userHandler.setUser(null);
        }else if(panel.equals("NonRecyclable")){
            //call get user information(method)
            NonRecyclableWasteGUI NRW = (NonRecyclableWasteGUI) panels.get("NonRecyclable");
            NRW.setUserDetails();
        }else if(panel.equals("bikeBooking")){
            bikeBookingGUI bikeBookGUI = (bikeBookingGUI) panels.get("bikeBooking");
            bikeBookGUI.setUserDetails();
        }else if(panel.equals("SusConQuestionnaire")) {
            SusConQuestionnaire scq = (SusConQuestionnaire) panels.get("SusConQuestionnaire");
            scq.setUserDetails();
        }
        ui.showPanel(panel);
    }

    public void removeNavbar() {
        ui.removeNavbar();
    }

    public User getUser() {
        return userHandler.getUser();
    }
    
    public UserService getUserService(){
        return userHandler;
    }

    public void saveUserChanges() {
        userHandler.saveChanges();        
    }

    public void registrationComplete(int cfscore) {
        userHandler.setCFScore(cfscore);
        //create userportal reference to userportal panel and set user before panel change
        UserPortal userportal = (UserPortal) panels.get("UserPortal");
        userportal.setUser();
        //on successful login, panel changes to userportal panel, navbar made visible
        ui.displayNavbar();
        changePanel("UserPortal");
    }

    public void onRegisterButtonClicked(String username, String name, String password) {
        //this method runs once login panel detects user interaction with register button

        //ensures password is not empty and is not a series of whitespaces
        //if (password != null && !password.trim().isEmpty()) {
        if (true) { //placeholder for login logic

            //TODO create logic to ensure that new user information does not conflict with other users
            //creates reference in user service to current user
            userHandler.registerNewUser(name, username, password);

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

        if (true) { //placeholder for login logic
            userHandler.loginExistingUser(username, password);

            if (userHandler.getUser() == null) {
                JOptionPane.showMessageDialog(null, "Username and/or password does not match any existing users. Please try again or Register.");
            } else {
                //on successful login, panel changes to home panel, navbar made visible 
                ui.displayNavbar();
                UserPortal up = (UserPortal) panels.get("UserPortal");
                up.setUser();
                changePanel("UserPortal");
            }

        } else {
            System.out.println("Login requires password");
        }
    }
}
