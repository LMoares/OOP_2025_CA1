/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsibleconsumptionapp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import responsibleconsumptionapp.Controller.UserInterfaceController;

/*
 * Classname UserInterfaceView.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserInterfaceView extends JFrame {

    private JPanel navbar_panel;
    private JLabel navbar_UserPortal_label;
    //private JLabel navbar_login_label;
    private JLabel navbar_menu2_label;
    private JLabel navbar_menu3_label;
    private JLabel navbar_menu4_label;
    private JPanel cards;
    private CardLayout layout;
    private UserInterfaceController UIC;

    public UserInterfaceView(UserInterfaceController UIC) {
        super("Responsible Consumption");
        this.UIC = UIC;
        //set default window restrictions - Lorenzo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); //width,height pixels
        setResizable(false); //prevents user from resizing window
        setLocationRelativeTo(null); //window to appear in middle of screen

        cards = new JPanel(new CardLayout());
        layout = (CardLayout) (cards.getLayout());

    }

    public void generateNavbar() {
        //create default navbar - Lorenzo
        //allows user to navigate between panel sections via mouse clicks on labels
        navbar_panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navbar_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //adds padding within panel
        navbar_panel.setBackground(Color.LIGHT_GRAY);

        navbar_UserPortal_label = new JLabel("User Portal");
        navbar_menu2_label = new JLabel("Chemical Waste");
        navbar_menu3_label = new JLabel("Tourism & Travel");
        navbar_menu4_label = new JLabel("Sustainable Consumption");

        //iterate through all navbar labels and set common attributes - Lorenzo
        JLabel[] labels = new JLabel[]{navbar_UserPortal_label, navbar_menu2_label, navbar_menu3_label, navbar_menu4_label};

        for (int i = 0; i < labels.length; i++) {
            JLabel label = labels[i];
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setForeground(Color.BLUE);
            label.setFont(new Font("Sans-serif", Font.BOLD, 20));
            navbar_panel.add(label);

            //place arrows between each label 
            if (i < labels.length - 1) {
                JLabel arrow = new JLabel(" | ");
                arrow.setFont(new Font("Sans-serif", Font.PLAIN, 20));
                arrow.setForeground(Color.WHITE);
                navbar_panel.add(arrow);
            }
        }

    }

    public void generateCards() {
        //method saves unique panels to cards and enables mouse click events for user interaction on navbar labels - Lorenzo
        //JPanel chemWaste = new JPanel();
        //chemWaste.add(new JLabel("Panel for Chemical Waste"));
        JPanel tourism = new JPanel();
        tourism.add(new JLabel("Panel for Tourism"));
        //cards.add(chemWaste, "chemWaste");
        cards.add(tourism, "tourism");

        //adds event listener for mouse click on navbar to change main window panel 
        navbar_UserPortal_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UIC.changePanel("UserPortal");
            }
        });

        navbar_menu2_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UIC.changePanel("ChemWaste");
            }
        });

        navbar_menu3_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UIC.changePanel("tourism");
            }
        });

        navbar_menu4_label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UIC.changePanel("SusCon");
            }
        });

        add(cards, BorderLayout.CENTER);
    }

    public void initializeCards(JPanel panel, String title) {
        cards.add(panel, title);
    }

    public void displayNavbar() {
        add(navbar_panel, BorderLayout.NORTH);
    }

    public void removeNavbar() {
        remove(navbar_panel);
    }

    public void showPanel(String cardTitle) {
        layout.show(cards, cardTitle);
    }
}
