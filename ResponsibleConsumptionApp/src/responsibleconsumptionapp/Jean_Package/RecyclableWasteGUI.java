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
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import responsibleconsumptionapp.Controller.IControllable;
import responsibleconsumptionapp.Controller.UserInterfaceController;
import responsibleconsumptionapp.Model.Focus;


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
    
    ///lists for  drop down menu(seperated into different types for easy referencing)
    private ArrayList<String> hazFlammable = new ArrayList<String>(List.of("used motor oil", "oil based paints and primers", "uncontaminated solvents(acetone, isopropyl alcohol)", "cooking oil"));
    private ArrayList<String> hazToxic = new ArrayList<String>(List.of("fluorescent light bulbs(contains mercury)", "Mercury thermometers(via mercury recovery"));
    private ArrayList<String> hazMetals = new ArrayList<String>(List.of("car batteries (lead acid)", "rechargeable batteries (ni-cd, li-ion)"));
    private ArrayList<String> hazElectronic = new ArrayList<String>(List.of("ink and toner cartridges", "e-waste(phones, laptops, tablets"));
    private ArrayList<String> hazCosmetic = new ArrayList<String>(List.of("hair dye", "nail polish","empty aerosol cans(non-toxic contents)"));
    private ArrayList<String> hazPlastic_rubber = new  ArrayList<String>(List.of("used tires(rubber recycling)", "plastic tubs/bottles", "plastic tools and scraps"));
    
    
    ///////file .txt names ///////////
    private String flammable_txt = "recyclableFiles/flammable_recyc.txt";
    private String cosmetic_recyc_txt = "recyclableFiles/cosmetic_recyc.txt";
    private String electronic_recyc_txt = "recyclableFiles/electronic_recyc.txt";
    private String metals_recyc_txt = "recyclableFiles/metals_recyc.txt";
    private String plastic_rubber_recyc_txt = "recyclableFiles/plastic_rubber_recyc.txt";
    private String toxic_recyc_txt = "recyclableFiles/toxic_recyc.txt";
    private String user_add_to_list_txt = "user_add_to_list.txt";
    
    //keep score
    private int points = 0;
    //counter for writing to file not on list for easy removal
    private int counter = 0;
    
    private Focus focus;
    /**
     * Creates new form RecyclableWasteGUI
     */
    public RecyclableWasteGUI() {
        initComponents();
        //populating dropdown menu with recycle_waste_list.txt 
        recycleValues();
         
    }
    
    //implements Icontrollable
    @Override
    public void setPanelListener(UserInterfaceController UICListener) {
        this.UICListener = UICListener;
    }
    
    //method for accumulating points for all users
    public void setUserDetails(){
        focus = new Focus(UICListener.getUserService(), UICListener.getUser());
    }
    
   
    //text input from user
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
    
    //choosen radio button before entry & points associated
    private String choiceBtn(){
        String button = "";
        if(flammable.isSelected()){
            button = "Flammable:";
            points += 1;
            flammable.setSelected(false);
        }else if(toxic.isSelected()){
            button = "Toxic:";
            points += 2;
            toxic.setSelected(false);
        }else if(heavy_metals.isSelected()){
            button = "Heavy Metals:";
            points += 1;
            heavy_metals.setSelected(false);
        }else if(electronic_waste.isSelected()){
            button = "Electronic_waste:";
            points += 2;
            electronic_waste.setSelected(false);
        }else if(rubber_plastics.isSelected()){
            button = "Rubber & Plastic:";
            points += 1;
            rubber_plastics.setSelected(false);
        }else if(cosmetic.isSelected()){
            button = "Cosmetic:";
            points += 1;
            cosmetic.setSelected(false);
        }    
        return button;  
    }
    //removes the points when removing data entered by user which was not in the drop down menu
    private String removeChoiceBtn(){
        String button = "";
        if(flammable.isSelected()){
            button = "Flammable:";
            points -= 1;
            flammable.setSelected(false);
        }else if(toxic.isSelected()){
            button = "Toxic:";
            points -= 2;
            toxic.setSelected(false);
        }else if(heavy_metals.isSelected()){
            button = "Heavy Metals:";
            points -= 1;
            heavy_metals.setSelected(false);
        }else if(electronic_waste.isSelected()){
            button = "Electronic_waste:";
            points -= 2;
            electronic_waste.setSelected(false);
        }else if(rubber_plastics.isSelected()){
            button = "Rubber & Plastic:";
            points -= 1;
            rubber_plastics.setSelected(false);
        }else if(cosmetic.isSelected()){
            button = "Cosmetic:";
            points -= 1;
            cosmetic.setSelected(false);
        }    
        return button;  
    }
    
    //method to get the last line details as for the counter (last number) so file does not overwrite the current numbers
    public int getLastLine(){
        int lastNum = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list.txt"))){
            String line = "";
            String item = "";
            
            while((line = br.readLine()) != null){
                item = line;
               
            }
            if(item != ""){
                String[] list = item.split(":");
                lastNum = Integer.parseInt(list[0]);
               
            }
            return lastNum;
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return lastNum;
    }
    
    //reader for file that a section will be deleted bw.write(focus.getUserName() + " added Type: " + choiceBtn() + "\nDiscription: " + data + "\n");
    public String remove(String num){
        String method = "";
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list.txt"))){

            String item = br.readLine();
            
            //check if the user is the same user that inserted the value in the first place
            String[] list = item.split(":");
            String userName = list[1].trim();
            
            //now to verify that the the correct radio button is selected
            String radioBtn = list[2].trim()+":";
            
            
            //System.out.println("testing ABC: " + item);counter + ": " + focus.getUserName() + " added Type: " + choiceBtn() + " Discription: " + data
            while(item != null){
                if(item.trim().startsWith(num) && userName.equalsIgnoreCase(focus.getUserName() + " added Type") && radioBtn.equalsIgnoreCase(removeChoiceBtn().trim())){
                    
                    //remove the points 
                    item = br.readLine();
                    continue; //this will skip writing the line 
                    
               }else if (item.trim().startsWith(num) && !userName.equalsIgnoreCase(focus.getUserName() + " added Type") && radioBtn.equalsIgnoreCase(removeChoiceBtn().trim())){
                    JOptionPane.showMessageDialog(this, "Your username and number selected must match the line to remove");
              
               }else if (item.trim().startsWith(num) && userName.equalsIgnoreCase(focus.getUserName() + " added Type") && !radioBtn.equalsIgnoreCase(removeChoiceBtn().trim())){
                    JOptionPane.showMessageDialog(this, "A radio button of Type must be selected and match");
               } 
               method += item + "\n";
               item = br.readLine();
               //Error handling
               System.out.println(radioBtn);
               System.out.println(removeChoiceBtn());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return method;
    }
    
    //deleting selected lines from file hold data that is not in current list
    public void deleteFile(String num){
        String currentOnFile = remove(num);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list.txt"))){
            bw.write(currentOnFile);
        }
        catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    
    //writting to recycle reycle_waste_list.txt
    private void writeRecycleValues(){
        counter = getLastLine();
        //input to write to file from user
        //String textAreaInput = text_area.getText();
        String textAreaInput = getTextAreaValue();//text box below radio buttons
        //System.out.println(getTextAreaValue());
        try(BufferedWriter wr = new BufferedWriter(new FileWriter("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list.txt", true))){
            counter += 1;
            //this adds the radio button value on top 
            wr.write(counter + ": " + focus.getUserName() + " added Type: " + choiceBtn() + "Discription: " + textAreaInput + "\n");
            
         
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
            //System.out.println("testing ABC: " + item);
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
        points_display.setText("Name: " + focus.getUserName() + "\nCurrent points: " + focus.getCurrentPoints() +  "\nTotal points to be added: " + points);
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
        heading = new javax.swing.JLabel();
        recyc_drop_down = new javax.swing.JComboBox<>();
        text_area_scroll = new javax.swing.JScrollPane();
        text_area = new javax.swing.JTextArea();
        image_box = new javax.swing.JPanel();
        displayTextAreaScroll = new javax.swing.JScrollPane();
        displayTextArea = new javax.swing.JTextArea();
        show_user_added_scroll = new javax.swing.JScrollPane();
        show_user_added = new javax.swing.JTextPane();
        safety_heading = new javax.swing.JLabel();
        returnBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        flammable = new javax.swing.JRadioButton();
        toxic = new javax.swing.JRadioButton();
        electronic_waste = new javax.swing.JRadioButton();
        cosmetic = new javax.swing.JRadioButton();
        heavy_metals = new javax.swing.JRadioButton();
        rubber_plastics = new javax.swing.JRadioButton();
        points_display_scroll = new javax.swing.JScrollPane();
        points_display = new javax.swing.JTextPane();
        not_in_drop_down_heading = new javax.swing.JLabel();
        removeBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        heading.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        heading.setForeground(new java.awt.Color(51, 153, 0));
        heading.setText("Recyclable Chemical Waste");

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
        displayTextAreaScroll.setViewportView(displayTextArea);

        show_user_added.setBackground(new java.awt.Color(204, 204, 204));
        show_user_added_scroll.setViewportView(show_user_added);

        safety_heading.setBackground(new java.awt.Color(204, 255, 255));
        safety_heading.setForeground(new java.awt.Color(0, 0, 0));
        safety_heading.setText("How to safely handle and dispose :");

        javax.swing.GroupLayout image_boxLayout = new javax.swing.GroupLayout(image_box);
        image_box.setLayout(image_boxLayout);
        image_boxLayout.setHorizontalGroup(
            image_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(show_user_added_scroll)
            .addComponent(displayTextAreaScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
            .addGroup(image_boxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(safety_heading, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        image_boxLayout.setVerticalGroup(
            image_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(image_boxLayout.createSequentialGroup()
                .addComponent(safety_heading, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayTextAreaScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(show_user_added_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        flammable.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(flammable);
        flammable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        flammable.setForeground(new java.awt.Color(0, 0, 0));
        flammable.setText("Flammable");

        toxic.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(toxic);
        toxic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        toxic.setForeground(new java.awt.Color(0, 0, 0));
        toxic.setText("Toxic");

        electronic_waste.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(electronic_waste);
        electronic_waste.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        electronic_waste.setForeground(new java.awt.Color(0, 0, 0));
        electronic_waste.setText("Electronic Waste");

        cosmetic.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(cosmetic);
        cosmetic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cosmetic.setForeground(new java.awt.Color(0, 0, 0));
        cosmetic.setText("Cosmetic");

        heavy_metals.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(heavy_metals);
        heavy_metals.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        heavy_metals.setForeground(new java.awt.Color(0, 0, 0));
        heavy_metals.setText("Heavy Metals");

        rubber_plastics.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup_type.add(rubber_plastics);
        rubber_plastics.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rubber_plastics.setForeground(new java.awt.Color(0, 0, 0));
        rubber_plastics.setText("Rubber & Plastics");

        points_display.setBackground(new java.awt.Color(255, 255, 255));
        points_display_scroll.setViewportView(points_display);

        not_in_drop_down_heading.setBackground(new java.awt.Color(204, 204, 255));
        not_in_drop_down_heading.setForeground(new java.awt.Color(0, 0, 0));
        not_in_drop_down_heading.setText("Not In Drop Down List: add below ");

        removeBtn.setBackground(new java.awt.Color(255, 51, 51));
        removeBtn.setForeground(new java.awt.Color(0, 0, 0));
        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

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
                            .addComponent(not_in_drop_down_heading, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(points_display_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(removeBtn)
                        .addGap(77, 77, 77)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(recyc_drop_down, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(heading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(recyc_drop_down, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(not_in_drop_down_heading)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(text_area_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(points_display_scroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(removeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)))))
                .addGap(13, 13, 13))
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
        UICListener.changePanel("ChemWaste");
        focus.addCurrentPoints(points);
        //let the user know what points were added(when done/exit)
        JOptionPane.showMessageDialog(this, "Name: " + focus.getUserName()  +  "\nTotal points to be added: " + points + "\nOverall points: " + focus.getCurrentPoints());
        resetPanel();
    }//GEN-LAST:event_returnBtnActionPerformed
     //if getTextAreaValue != null  (onclick of add) call write recycleValues , 
    // call setTextAreaValue(disposabile file reader(user_add_to_list_txt));
    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        //displays what was just written bottom left window  
        if(!getTextAreaValue().equals("")){//input text area is not empty.
           //ensure type radio button is selected
           if(choiceBtn().equals("")){
                JOptionPane.showMessageDialog(this, "Must select Type using radio button");
                return;
            }
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
            choiceBtn();
            //update and show points
            showPoints();
        }
        //clear selected radio button
        buttonGroup_type.clearSelection();
    }//GEN-LAST:event_addBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        //displays what was written to file with selected heading
        displayUserAdded(user_add_to_list_txt);
        //displayInputContentFromFile();
        String num = JOptionPane.showInputDialog(this, "Enter the number of the line of data to remove").trim();
        if(num != "" && !removeChoiceBtn().equalsIgnoreCase("")){
            removeChoiceBtn();
            deleteFile(num);
            displayUserAdded(user_add_to_list_txt);
            showPoints();
        }else{
            JOptionPane.showMessageDialog(this, "A number of line and radio button of type must be selected to remove line");
        }    
    }//GEN-LAST:event_removeBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.ButtonGroup buttonGroup_type;
    private javax.swing.JRadioButton cosmetic;
    private javax.swing.JTextArea displayTextArea;
    private javax.swing.JScrollPane displayTextAreaScroll;
    private javax.swing.JRadioButton electronic_waste;
    private javax.swing.JRadioButton flammable;
    private javax.swing.JLabel heading;
    private javax.swing.JRadioButton heavy_metals;
    private javax.swing.JPanel image_box;
    private javax.swing.JLabel not_in_drop_down_heading;
    private javax.swing.JTextPane points_display;
    private javax.swing.JScrollPane points_display_scroll;
    private javax.swing.JComboBox<String> recyc_drop_down;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton returnBtn;
    private javax.swing.JRadioButton rubber_plastics;
    private javax.swing.JLabel safety_heading;
    private javax.swing.JTextPane show_user_added;
    private javax.swing.JScrollPane show_user_added_scroll;
    private javax.swing.JTextArea text_area;
    private javax.swing.JScrollPane text_area_scroll;
    private javax.swing.JRadioButton toxic;
    // End of variables declaration//GEN-END:variables
}
