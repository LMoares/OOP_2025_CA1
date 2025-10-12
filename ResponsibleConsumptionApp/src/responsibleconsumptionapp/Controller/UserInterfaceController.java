/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Controller;

import responsibleconsumptionapp.View.UserInterfaceView;

/*
 * Classname UserInterfaceController.java
 * Date 11/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
public class UserInterfaceController {
    private UserInterfaceView ui;
    
    public UserInterfaceController() {
        ui = new UserInterfaceView();
        ui.generateNavbar();
        ui.generateCards();
    }
    
    public void show(){
        ui.setVisible(true);
    }
    
    public void repaint(){
        ui.repaint();
        ui.revalidate();
    }
    
}
