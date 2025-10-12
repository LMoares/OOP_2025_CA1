/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Classname UserInterfaceView.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */


public class UserInterfaceView extends JFrame{
    private JPanel navbar_panel;
    private JLabel navbar_home_label;
    private JLabel navbar_menu1_label;
    private JLabel navbar_menu2_label;
    private JLabel navbar_menu3_label;
    private JPanel cards;
    
    public UserInterfaceView() {
        super("Responsible Consumption");
        //set default window restrictions - Lorenzo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,600); //width,height pixels
        setResizable(false); //prevents user from resizing window
        setLocationRelativeTo(null); //window to appear in middle of screen
        
        cards = new JPanel(new CardLayout());
    }
    
    public void generateNavbar(){
        //create default navbar - Lorenzo
        navbar_panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        navbar_panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); //adds padding within panel
        add(navbar_panel, BorderLayout.NORTH);
        navbar_panel.setBackground(Color.GRAY);
        
        navbar_home_label = new JLabel("Home");
        navbar_menu1_label = new JLabel("Menu1");
        navbar_menu2_label = new JLabel("Menu2");
        navbar_menu3_label = new JLabel("Menu3");
        
        //iterate through all navbar labels and set common attributes - Lorenzo
        JLabel[] labels = new JLabel[]{navbar_home_label, navbar_menu1_label, navbar_menu2_label, navbar_menu3_label};

        for (int i = 0; i < labels.length; i++) {
            JLabel label = labels[i];
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.setForeground(Color.BLUE);
            label.setFont(new Font("Sans-serif", Font.BOLD, 24));
            navbar_panel.add(label);

            //place arrows between each label 
            if (i < labels.length - 1) {
                JLabel arrow = new JLabel(" > ");
                arrow.setFont(new Font("Sans-serif", Font.PLAIN, 20));
                arrow.setForeground(Color.WHITE);
                navbar_panel.add(arrow);
            }
        }

        
    }
    
    public void generateCards(){
        //method saves unique panels to cards and enables mouse click events for user interaction on navbar labels - Lorenzo
        JPanel home = new JPanel();
        home.add(new JLabel("Welcome to the Home Page"));
        JPanel menu1 = new JPanel();
        menu1.add(new JLabel("Welcome to the Menu 1 Page"));
        JPanel menu2 = new JPanel();
        menu2.add(new JLabel("Welcome to the Menu 2 Page"));
        JPanel menu3 = new JPanel();
        menu3.add(new JLabel("Welcome to the Menu 3 Page"));
        cards.add(home, "Home");
        cards.add(menu1, "menu1");
        cards.add(menu2, "menu2");
        cards.add(menu3, "menu3");
        
        //adds event listener for mouse click on navbar to change main window panel 
        CardLayout layout = (CardLayout)(cards.getLayout());
        navbar_home_label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                layout.show(cards, "Home");
            }
        });
        
        navbar_menu1_label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                layout.show(cards, "menu1");
            }
        });
        
        navbar_menu2_label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                layout.show(cards, "menu2");
            }
        });
        
        navbar_menu3_label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                layout.show(cards, "menu3");
            }
        });
        
        add(cards, BorderLayout.CENTER);
    }
    
}
