/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package responsibleconsumptionapp.Jean_Package;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JRadioButton;
import responsibleconsumptionapp.Controller.IControllable;
import responsibleconsumptionapp.Controller.UserInterfaceController;


/**
 * RecyclableWasteGui.java
 * Date 11/11/2025
 * @author Jeán Walton
 */
public class RecyclableWasteGUI extends javax.swing.JPanel implements IControllable{
    //declare data
    private UserInterfaceController UICListener;
    private String selectedWaste;
    private String recyc_method;
    
    private ArrayList<String> hazFlammable = new ArrayList<String>(List.of("used motor oil", "oil based paints and primers", "uncontaminated solvents(acetone, isopropyl alcohol)", "cooking oil"));
    private ArrayList<String> hazToxic = new ArrayList<String>(List.of("fluorescent light bulbs(contains mercury)", "Mercury thermometers(via mercury recovery"));
    private ArrayList<String> hazMetals = new ArrayList<String>(List.of("car batteries (lead acid)", "rechargeable batteries (ni-cd, li-ion)"));
    private ArrayList<String> hazElectronic = new ArrayList<String>(List.of("ink and toner cartridges", "e-waste(phones, laptops, tablets"));
    private ArrayList<String> hazCosmetic = new ArrayList<String>(List.of("hair dye", "nail polish","empty aerosol cans(non-toxic contents)"));
    private ArrayList<String> hazPlastic_rubber = new  ArrayList<String>(List.of("used tires(rubber recycling)", "plastic tubs/bottles", "plastic tools and scraps"));
    
    
    //file names used
    private String flammable_txt = "recyclableFiles/flammable_recyc.txt";
    private String cosmetic_recyc_txt = "recyclableFiles/cosmetic_recyc.txt";
    private String electronic_recyc_txt = "recyclableFiles/electronic_recyc.txt";
    private String metals_recyc_txt = "recyclableFiles/metals_recyc.txt";
    private String plastic_rubber_recyc_txt = "recyclableFiles/plastic_rubber_recyc.txt";
    private String toxic_recyc_txt = "recyclableFiles/toxic_recyc.txt";
    private String user_add_to_list_txt = "recyclableFiles/user_add_to_list.txt";
    
    
    private int points = 0;
    /**
     * Creates new form RecyclableWasteGUI
     */
    public RecyclableWasteGUI() {
        initComponents();
        //populating dropdown menu with recycle_waste_list.txt 
        recycleValues();
         
    }
    
    
    @Override
    public void setPanelListener(UserInterfaceController UICListener) {
        this.UICListener = UICListener;
    }
    
    
    private String getTextAreaValue(){
        text_area.setEditable(true);
        return text_area.getText().trim();
    }
   
    //show_details top left window from drop down selection.
    private void displayRecycMethod(String method){
        
        displayTextArea.setText(method);
    }
    
    //show the detail entered manually if not on list
    private void displayUserAdded(String fileName){
        show_user_added.setText(disposalFileReader(fileName));
    }
    
    //choosen radio button before entry 
    private String choicBtn(){
        String button = "";
        if(flammable.isSelected()){
            button = "Flammable:";
            points += 1;
        }else if(toxic.isSelected()){
            button = "Toxic:";
            points += 2;
        }else if(heavy_metals.isSelected()){
            button = "Heavy Metals:";
            points += 1;
        }else if(electronic_waste.isSelected()){
            button = "Electronic_waste:";
            points += 2;
        }else if(rubber_plastics.isSelected()){
            button = "Rubber & Plastic:";
            points += 1;
        }else if(cosmetic.isSelected()){
            button = "Cosmetic:";
            points += 1;
        }    
        return button;  
    }
    
    //writting to recycle reycle_waste_list.txt
    private void writeRecycleValues(){
        //input to write to file from user
        //String textAreaInput = text_area.getText();
        String textAreaInput = getTextAreaValue();//text box below radio buttons
        System.out.println(getTextAreaValue());
        try(BufferedWriter wr = new BufferedWriter(new FileWriter("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list.txt", true))){
            //this adds the radio button value on top 
            wr.write("\n" + choicBtn()  + "\n");
            wr.write(textAreaInput); 
        }
        catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }
    
    //read recycle values for drop down menu
    private void recycleValues(){
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/recycle_waste_list.txt"))){
            String item = br.readLine();
           
            while(item!=null){
                recyc_drop_down.addItem(item);
                item = br.readLine();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("The file recycle_waste_list.txt was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error found " + e.getMessage());
        }
    }
    
    //file reader to read from a file how to dispose of goods
    public String disposalFileReader(String fileName){
        //reset recyc_method as it's a global variable
        String method = "";
        try (BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/" + fileName))){
            String item = br.readLine();
            System.out.println("testing ABC: " + item);
            while(item != null){
                method += item + "\n";
                item = br.readLine();
            }

        }
        catch(FileNotFoundException e){
            System.out.println( fileName + " was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error found " + e.getMessage());
        }
        return method;
    }
    /////////////////////reset/////////////////////////////////////////
    public void resetPanel(){
        displayUserAdded(""); //display text area bottom left reset
        displayRecycMethod(""); //display text area top left reset
        text_area.setText(""); //display text input area bottom left reset
        ///radio buttons
        flammable.setSelected(false);
        toxic.setSelected(false);
        heavy_metals.setSelected(false);
        electronic_waste.setSelected(false);
        cosmetic.setSelected(false);
        rubber_plastics.setSelected(false);
        show_user_added.setText("");
        points = 0;
        showPoints();
   }
    
    //show ui point accumulated
    private void showPoints(){
        points_display.setText("Total points: " + points);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_type = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        recyc_drop_down = new javax.swing.JComboBox<>();
        text_area_scroll = new javax.swing.JScrollPane();
        text_area = new javax.swing.JTextArea();
        image_box = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        show_user_added = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        returnBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        flammable = new javax.swing.JRadioButton();
        toxic = new javax.swing.JRadioButton();
        electronic_waste = new javax.swing.JRadioButton();
        cosmetic = new javax.swing.JRadioButton();
        heavy_metals = new javax.swing.JRadioButton();
        rubber_plastics = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        points_display = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 153, 0));
        jLabel1.setText("Recyclable Chemical Waste");

        recyc_drop_down.setBackground(new java.awt.Color(255, 255, 255));
        recyc_drop_down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recyc_drop_downActionPerformed(evt);
            }
        });

        text_area.setBackground(new java.awt.Color(204, 204, 204));
        text_area.setColumns(20);
        text_area.setForeground(new java.awt.Color(0, 0, 0));
        text_area.setRows(5);
        text_area.setText("\n\n");
        text_area_scroll.setViewportView(text_area);

        image_box.setBackground(new java.awt.Color(204, 204, 204));
        image_box.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        displayTextArea.setBackground(new java.awt.Color(204, 204, 204));
        displayTextArea.setColumns(20);
        displayTextArea.setRows(5);
        jScrollPane1.setViewportView(displayTextArea);

        show_user_added.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setViewportView(show_user_added);

        jLabel2.setBackground(new java.awt.Color(204, 255, 255));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("How to safely handle and dispose :");

        javax.swing.GroupLayout image_boxLayout = new javax.swing.GroupLayout(image_box);
        image_box.setLayout(image_boxLayout);
        image_boxLayout.setHorizontalGroup(
            image_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
            .addGroup(image_boxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        image_boxLayout.setVerticalGroup(
            image_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_boxLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        returnBtn.setBackground(new java.awt.Color(0, 153, 153));
        returnBtn.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(0, 0, 0));
        returnBtn.setText("return");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        addBtn.setBackground(new java.awt.Color(204, 0, 204));
        addBtn.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        addBtn.setForeground(new java.awt.Color(0, 0, 0));
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        buttonGroup_type.add(flammable);
        flammable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        flammable.setText("Flammable");

        buttonGroup_type.add(toxic);
        toxic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        toxic.setText("Toxic");

        buttonGroup_type.add(electronic_waste);
        electronic_waste.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        electronic_waste.setText("Electronic Waste");

        buttonGroup_type.add(cosmetic);
        cosmetic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cosmetic.setText("Cosmetic");

        buttonGroup_type.add(heavy_metals);
        heavy_metals.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        heavy_metals.setText("Heavy Metals");

        buttonGroup_type.add(rubber_plastics);
        rubber_plastics.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rubber_plastics.setText("Rubber & Plastics");

        jScrollPane3.setViewportView(points_display);

        jLabel3.setText("Not In Drop Down List: add below ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(image_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(flammable, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                        .addGap(60, 60, 60))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(toxic, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rubber_plastics))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(electronic_waste, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(cosmetic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(heavy_metals)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(text_area_scroll)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(233, 233, 233)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(recyc_drop_down, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(recyc_drop_down, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(image_box, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(flammable)
                                    .addComponent(electronic_waste))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rubber_plastics)
                                    .addComponent(cosmetic))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(toxic)
                                    .addComponent(heavy_metals))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(text_area_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void recyc_drop_downActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recyc_drop_downActionPerformed
        // TODO add your handling code here:
        String selected = (String)recyc_drop_down.getSelectedItem();
        if(hazFlammable.contains(selected.toLowerCase())){ 
            //automatically set radio 
            flammable.setSelected(true);
            //display to user/call to read from specific file (recyc_flammable.txt)
            displayRecycMethod(disposalFileReader(flammable_txt));
        }else if(hazToxic.contains(selected.toLowerCase())){
            //automatically set radio
            toxic.setSelected(true);
            //display to user/call to read from specific file (toxic_recyc.txt)
            displayRecycMethod(disposalFileReader(toxic_recyc_txt));
        }else if(hazMetals .contains(selected.toLowerCase())){ 
            //automatically set radio
            heavy_metals.setSelected(true);
            //display to user/call to read from specific file (metals_recyc.txt)
            displayRecycMethod(disposalFileReader(metals_recyc_txt));
        }else if(hazElectronic.contains(selected.toLowerCase())){
            //automatically set radio
            electronic_waste.setSelected(true);
            //display to user/call to read from specific file (electronic_recyc.txt)
            displayRecycMethod(disposalFileReader(electronic_recyc_txt));
        }else if(hazCosmetic.contains(selected.toLowerCase())){
            //automatically set radio
            cosmetic.setSelected(true);
            //display to user/call to read from specific file (cosmetic_recyc.txt)
            displayRecycMethod(disposalFileReader(cosmetic_recyc_txt));
        }else if(hazPlastic_rubber.contains(selected.toLowerCase())){
            //automatically set radio
            rubber_plastics.setSelected(true);
            //display to user/call to read from specific file (plastic_rubber_recyc.txt)
            displayRecycMethod(disposalFileReader(plastic_rubber_recyc_txt));
        }
    }//GEN-LAST:event_recyc_drop_downActionPerformed
    
    //return button back to Chemical Waste panel
    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        // TODO add your handling code here:
        resetPanel();
        UICListener.changePanel("ChemWaste");
       
    }//GEN-LAST:event_returnBtnActionPerformed
     //if getTextAreaValue != null  (onclick of add) call write recycleValues , 
    // call setTextAreaValue(disposabile file reader(user_add_to_list_txt));
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        //displays what was just written bottom left window  
        if(!getTextAreaValue().equals("")){//input text area is empty.
           //write to file user_add_to_list.txt 
           writeRecycleValues(); 
           //displays what was written to file with selected heading
           displayUserAdded(user_add_to_list_txt);
           //update and show points
           showPoints();
           //after input has been written to a file  input cleared 
           text_area.setText(""); 
        }else{
            //selected radio btn choice
            choicBtn();
            //update and show points
            showPoints();
        }
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.ButtonGroup buttonGroup_type;
    private javax.swing.JRadioButton cosmetic;
    private javax.swing.JTextArea displayTextArea;
    private javax.swing.JRadioButton electronic_waste;
    private javax.swing.JRadioButton flammable;
    private javax.swing.JRadioButton heavy_metals;
    private javax.swing.JPanel image_box;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane points_display;
    private javax.swing.JComboBox<String> recyc_drop_down;
    private javax.swing.JButton returnBtn;
    private javax.swing.JRadioButton rubber_plastics;
    private javax.swing.JTextPane show_user_added;
    private javax.swing.JTextArea text_area;
    private javax.swing.JScrollPane text_area_scroll;
    private javax.swing.JRadioButton toxic;
    // End of variables declaration//GEN-END:variables
}
