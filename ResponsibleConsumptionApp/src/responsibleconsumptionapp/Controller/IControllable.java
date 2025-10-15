/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Controller;

/*
 * Classname IControllable.java
 * Date 15/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */

//Required for JPanels that require UserInterfaceController to detect and react to user interaction on JPanel
public interface IControllable {
    //panels that implement this interface will require a UserInterfaceController variable to store UICListener reference. eg: private UserInterfaceController UICListener;
    void setPanelListener(UserInterfaceController UICListener);
}
