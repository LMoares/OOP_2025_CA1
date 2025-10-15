/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package responsibleconsumptionapp.Lorenzo_Package;

/*
 * Classname ImagePanel.java
 * Date 14/10/2025
 * @author Lorenzo Moares Nunez, 23378441
 */
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

public class ImagePanel extends Panel {
    private Image image;

    public ImagePanel(String imagePath) {
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            // Scale image to panel size
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

