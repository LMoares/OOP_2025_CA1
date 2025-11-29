package responsibleconsumptionapp.Jean_Package;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 * NonRecyclableWasteGui.java
 * Date 11/11/2025
 * @author Jeán Walton
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import responsibleconsumptionapp.Controller.IControllable;
import responsibleconsumptionapp.Controller.UserInterfaceController;
import responsibleconsumptionapp.Model.Focus;

public class NonRecyclableWasteGUI extends javax.swing.JPanel implements IControllable {
    private UserInterfaceController UICListener;
    private String selectedWaste;
    private String recyc_method;
    
    ///lists for  drop down menu(seperated into different types for easy referencing) 
    private ArrayList<String> toxic_contaminated_list = new ArrayList<String>(List.of("mixed or contaminated solvents", "bleach and ammonia(especially if mixed)", "mold removers and disinfectants", "expired or unknown cleaning products", "contaminated rags or paper towels", "contaminated motor oil(mixed with other fluids"));
    private ArrayList<String> corrosive_list = new ArrayList<String>(List.of("oven cleaners", "toilet bowl cleaners", "rust removers"));
    private ArrayList<String> flammable_volatile_list = new ArrayList<String>(List.of("old fuel(petrol or diesel)", "nail polish remover(acetone, if contaminated)", "perfumes and colognes (alcohol based)"));
    private ArrayList<String> biohazard_pharma_list = new ArrayList<String>(List.of("expired medications", "hair relaxers and chemical mixtures"));
    private ArrayList<String> reactive_list = new ArrayList<String>(List.of("aerosol cans with toxic contents", "unknown chemical mixtures"));
    private ArrayList<String> pesticides_poisons_list = new ArrayList<String>(List.of("pesticides and herbicides", "rodent poisons"));
    private ArrayList<String> radioactive_list = new ArrayList<String>(List.of("smoke detectors with americium-241"));
    private ArrayList<String> asbestos_sludge_list = new ArrayList<String>(List.of("asbestos containing materials", "mixed chemical sludge"));
     
    ///////file .txt names ///////////
    private String toxic_non_recyc_txt = "NonRecyclableFiles/toxic_non_recyc.txt";  
    private String corrosive_non_recyc_txt = "NonRecyclableFiles/corrosive_non_recyc.txt";
    private String flammable_volatile_non_recyc_txt = "NonRecyclableFiles/flammable_volatile_non_recyc.txt";
    private String biohazard_pharma_non_recyc_txt = "NonRecyclableFiles/biohazard_pharma_non_recyc.txt";
    private String reactive_non_recyc_txt = "NonRecyclableFiles/reactive_non_recyc.txt";
    private String pesticides_poisons_non_recyc_txt = "NonRecyclableFiles/pesticides_poisons_non_recyc.txt";
    private String radioactive_non_recyc_txt = "NonRecyclableFiles/radioactive_non_recyc.txt";
    private String asbestos_sludge_non_recyc_txt = "NonRecyclableFiles/asbestos_sludge_non_recyc.txt";
    private String user_add_to_list_non_recyclable_txt = "user_add_to_list_non_recyclable.txt";
    
    
    
    //keep score
    private int points = 0;
    //counter for line input reference
    private int counter = 0;
    //instatiate a focus object to keep track of the user details and score
    Focus focus;
    
    /**
     * Creates new form NonRecyclableWasteGUI
     */
    public NonRecyclableWasteGUI() {
        initComponents();
        readNon_recyclable();
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
    private String getTextAreaValue() {
        text_area_input.setEditable(true);
        return text_area_input.getText().trim();
    }
    //display choice content from drop down (top left window)
    private void displayContent(String method){
        display_Dmenu_text.setText(method);
    }
    
    //display input content (bottom left window) can be use by any method in future
    private void displayInputContentFromFile(){
        display_input_text.setText(nonRecycDisposalReader("user_add_to_list_non_recyclable.txt"));
    }
      
    //create a way read and write to populate drop menu from file non_recycle_waste.txt
    public void readNon_recyclable(){
        String item;
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/non_recycle_list.txt"))){
           item = br.readLine();
           while(item != null){
            drop_menu.addItem(item);
            item = br.readLine();
           }
        }
        catch(FileNotFoundException e){
            System.out.println("The file non_recycle_waste_list.txt was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("The file non_recycle_waste.txt is not found " + e.getMessage());
        }
    }
    
    //method for reading different files some will be used to display top left window
    public String nonRecycDisposalReader(String fileName){
        String method ="";
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/" + fileName))){

            String item;
           
            while((item = br.readLine()) != null){
               method += item + "\n";
               
            }
        }
        catch(FileNotFoundException e){
            System.out.println(fileName + " was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return method;
    }
    
    //method to get the last line details as for the counter (last number) so file does not overwrite the current numbers
    public int getLastLine(){
        int lastNum = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list_non_recyclable.txt"))){
            String line = "";
            String item = "";
            
            while((line = br.readLine()) != null){
                item = line;
               
            }
            if(!item.equals("")){
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
    public String remove(String fileName, String num){
        String method = "";
        try(BufferedReader br = new BufferedReader(new FileReader("./src/responsibleconsumptionapp/Jean_Package/disposal_list/" + fileName))){

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
               //System.out.println(radioBtn);
               //System.out.println(removeChoiceBtn());
            }
        }
        catch(FileNotFoundException e){
            System.out.println(fileName + " was not found." + e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return method;
    }
    
    
    
    //write to a file user_add_to_list_non_recyclable.txt
    public void writeToNonRecyc(String data){
        counter = getLastLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list_non_recyclable.txt", true))){
            counter +=1;
            bw.write(counter + ": " + focus.getUserName() + " added Type: " + choiceBtn() + " Discription: " + data + "\n");
        }
        catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    //deleting selected lines from file hold data that is not in current list
    public void deleteFile(String num){
        String currentOnFile = remove(user_add_to_list_non_recyclable_txt, num);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("./src/responsibleconsumptionapp/Jean_Package/disposal_list/user_add_to_list_non_recyclable.txt"))){
            bw.write(currentOnFile);
        }
        catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    //choice of type of waste (radio buttons) & points associated 
    private String choiceBtn(){
        String button = "";
        if(toxic_contaminated_btn.isSelected()){
           button = "Toxic contaminated: ";
           points += 5;
           
        }else if(corrosive_btn.isSelected()){
           button = "Corrosive:";
           points += 3;
           
        }else if(flammable_volatile_btn.isSelected()){
           button = "Flammable Volatile:";
           points += 2;
          
        }else if(biohazard_btn.isSelected()){
           button = "Biohazard:";
           points += 4;
           
        }else if(reactive_btn.isSelected()){
           button = "Reactive:";
           points += 2;
           
        }else if(pesticides_btn.isSelected()){
           button = "Pesticides & Poisons:";
           points += 3;
          
        }else if(radioactive_btn.isSelected()){
           button = "Radioactive:";
           points += 10;
        }else if(asbestos_sludge_btn.isSelected()){
           button = "Asbestos & Sludge:";
           points += 6;
        }
        return button;
         
    }
    // to minus the values being removed from points
    private String removeChoiceBtn(){
        String button = "";
        if(toxic_contaminated_btn.isSelected()){
           button = "Toxic contaminated: ";
           points -= 5;
           
        }else if(corrosive_btn.isSelected()){
           button = "Corrosive:";
           points -= 3;
           
        }else if(flammable_volatile_btn.isSelected()){
           button = "Flammable Volatile:";
           points -= 2;
          
        }else if(biohazard_btn.isSelected()){
           button = "Biohazard:";
           points -= 4;
           
        }else if(reactive_btn.isSelected()){
           button = "Reactive:";
           points -= 2;
           
        }else if(pesticides_btn.isSelected()){
           button = "Pesticides & Poisons:";
           points -= 3;
          
        }else if(radioactive_btn.isSelected()){
           button = "Radioactive:";
           points -= 10;
        }else if(asbestos_sludge_btn.isSelected()){
           button = "Asbestos & Sludge:";
           points -= 6;
        }
        return button;
         
    }
    
    //reset panel
    public void resetPanel(){
        //setting top left window to an empty string
        display_Dmenu_text.setText("");
        //setting bottom left window to an empty string
        display_input_text.setText("");
        //setting the text area input bottom right window to an empty string
        text_area_input.setText("");
        //set the points display 
        points_display_ui.setText("");
        //setting the 8 radio buttons to false
        toxic_contaminated_btn.setSelected(false);
        corrosive_btn.setSelected(false);
        flammable_volatile_btn.setSelected(false);
        biohazard_btn.setSelected(false);
        reactive_btn.setSelected(false);
        pesticides_btn.setSelected(false);
        radioactive_btn.setSelected(false);
        asbestos_sludge_btn.setSelected(false);
        //reset points accumulated
        points = 0;
        showPoints();
    }
    
    //show ui point accumulated
    private void showPoints(){
        points_display_ui.setText("Name: " + focus.getUserName() + "\nCurrent points: " + focus.getCurrentPoints() +  "\nTotal points to be added: " + points);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hazardButtonGroup = new javax.swing.ButtonGroup();
        heading = new javax.swing.JLabel();
        drop_menu = new javax.swing.JComboBox<>();
        block_holding_output = new javax.swing.JPanel();
        display_Dmenu_text_scroll = new javax.swing.JScrollPane();
        display_Dmenu_text = new javax.swing.JTextArea();
        display_input_text_scroll = new javax.swing.JScrollPane();
        display_input_text = new javax.swing.JTextArea();
        safety_heading = new javax.swing.JLabel();
        text_area_input_scroll = new javax.swing.JScrollPane();
        text_area_input = new javax.swing.JTextArea();
        addBTN = new javax.swing.JButton();
        returnBtn = new javax.swing.JButton();
        toxic_contaminated_btn = new javax.swing.JRadioButton();
        corrosive_btn = new javax.swing.JRadioButton();
        flammable_volatile_btn = new javax.swing.JRadioButton();
        biohazard_btn = new javax.swing.JRadioButton();
        reactive_btn = new javax.swing.JRadioButton();
        pesticides_btn = new javax.swing.JRadioButton();
        radioactive_btn = new javax.swing.JRadioButton();
        asbestos_sludge_btn = new javax.swing.JRadioButton();
        not_in_list_heading = new javax.swing.JLabel();
        points_display_ui_scroll = new javax.swing.JScrollPane();
        points_display_ui = new javax.swing.JTextPane();
        remove_BTN = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));

        heading.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        heading.setForeground(new java.awt.Color(255, 0, 0));
        heading.setText("Non - Recyclable Waste");

        drop_menu.setBackground(new java.awt.Color(255, 255, 255));
        drop_menu.setForeground(new java.awt.Color(0, 0, 0));
        drop_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drop_menuActionPerformed(evt);
            }
        });

        block_holding_output.setBackground(new java.awt.Color(204, 204, 204));
        block_holding_output.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        display_Dmenu_text.setBackground(new java.awt.Color(204, 204, 204));
        display_Dmenu_text.setColumns(20);
        display_Dmenu_text.setForeground(new java.awt.Color(0, 0, 0));
        display_Dmenu_text.setRows(5);
        display_Dmenu_text_scroll.setViewportView(display_Dmenu_text);

        display_input_text.setBackground(new java.awt.Color(204, 204, 204));
        display_input_text.setColumns(20);
        display_input_text.setForeground(new java.awt.Color(0, 0, 0));
        display_input_text.setRows(5);
        display_input_text.setText("\n");
        display_input_text_scroll.setViewportView(display_input_text);

        safety_heading.setBackground(new java.awt.Color(204, 204, 204));
        safety_heading.setForeground(new java.awt.Color(0, 0, 0));
        safety_heading.setText("How to safely handle and dispose:");

        javax.swing.GroupLayout block_holding_outputLayout = new javax.swing.GroupLayout(block_holding_output);
        block_holding_output.setLayout(block_holding_outputLayout);
        block_holding_outputLayout.setHorizontalGroup(
            block_holding_outputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(block_holding_outputLayout.createSequentialGroup()
                .addGroup(block_holding_outputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(display_Dmenu_text_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                    .addComponent(display_input_text_scroll))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(block_holding_outputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(safety_heading, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        block_holding_outputLayout.setVerticalGroup(
            block_holding_outputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(block_holding_outputLayout.createSequentialGroup()
                .addComponent(safety_heading, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(display_Dmenu_text_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(display_input_text_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        text_area_input.setBackground(new java.awt.Color(204, 204, 204));
        text_area_input.setColumns(20);
        text_area_input.setForeground(new java.awt.Color(0, 0, 0));
        text_area_input.setRows(5);
        text_area_input_scroll.setViewportView(text_area_input);

        addBTN.setBackground(new java.awt.Color(204, 0, 204));
        addBTN.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        addBTN.setForeground(new java.awt.Color(0, 0, 0));
        addBTN.setText("ADD");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });

        returnBtn.setBackground(new java.awt.Color(0, 153, 153));
        returnBtn.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        returnBtn.setForeground(new java.awt.Color(0, 0, 0));
        returnBtn.setText("Return");
        returnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBtnActionPerformed(evt);
            }
        });

        toxic_contaminated_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(toxic_contaminated_btn);
        toxic_contaminated_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        toxic_contaminated_btn.setForeground(new java.awt.Color(0, 0, 0));
        toxic_contaminated_btn.setText("Toxic / Contaminated");

        corrosive_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(corrosive_btn);
        corrosive_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        corrosive_btn.setForeground(new java.awt.Color(0, 0, 0));
        corrosive_btn.setText("Corrosive");

        flammable_volatile_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(flammable_volatile_btn);
        flammable_volatile_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        flammable_volatile_btn.setForeground(new java.awt.Color(0, 0, 0));
        flammable_volatile_btn.setText("Flammable & Volatile");

        biohazard_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(biohazard_btn);
        biohazard_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        biohazard_btn.setForeground(new java.awt.Color(0, 0, 0));
        biohazard_btn.setText("Biohazard / Pharmaceutical");

        reactive_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(reactive_btn);
        reactive_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        reactive_btn.setForeground(new java.awt.Color(0, 0, 0));
        reactive_btn.setText("Reactive");

        pesticides_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(pesticides_btn);
        pesticides_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pesticides_btn.setForeground(new java.awt.Color(0, 0, 0));
        pesticides_btn.setText("Pesticides & Poisons");

        radioactive_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(radioactive_btn);
        radioactive_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radioactive_btn.setForeground(new java.awt.Color(0, 0, 0));
        radioactive_btn.setText("Radioactive");

        asbestos_sludge_btn.setBackground(new java.awt.Color(204, 204, 255));
        hazardButtonGroup.add(asbestos_sludge_btn);
        asbestos_sludge_btn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        asbestos_sludge_btn.setForeground(new java.awt.Color(0, 0, 0));
        asbestos_sludge_btn.setText("Asbestos & Sludge");

        not_in_list_heading.setBackground(new java.awt.Color(204, 204, 255));
        not_in_list_heading.setForeground(new java.awt.Color(0, 0, 0));
        not_in_list_heading.setText("Not In Drop Down List: add below");

        points_display_ui.setBackground(new java.awt.Color(255, 255, 255));
        points_display_ui.setForeground(new java.awt.Color(0, 0, 0));
        points_display_ui_scroll.setViewportView(points_display_ui);

        remove_BTN.setBackground(new java.awt.Color(255, 51, 51));
        remove_BTN.setForeground(new java.awt.Color(0, 0, 0));
        remove_BTN.setText("Remove");
        remove_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_BTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(returnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(points_display_ui_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(remove_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(addBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(block_holding_output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(text_area_input_scroll)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(toxic_contaminated_btn)
                                        .addGap(59, 59, 59)
                                        .addComponent(corrosive_btn))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(not_in_list_heading, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(flammable_volatile_btn, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(biohazard_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(pesticides_btn, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(24, 24, 24)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(asbestos_sludge_btn)
                                            .addComponent(radioactive_btn)
                                            .addComponent(reactive_btn))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drop_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(heading, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(drop_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toxic_contaminated_btn)
                            .addComponent(corrosive_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(flammable_volatile_btn)
                            .addComponent(reactive_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(biohazard_btn)
                            .addComponent(radioactive_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pesticides_btn)
                            .addComponent(asbestos_sludge_btn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(not_in_list_heading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(text_area_input_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(block_holding_output, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(returnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addComponent(remove_BTN, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(points_display_ui_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(21, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void returnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBtnActionPerformed
        // TODO add your handling code here:
        UICListener.changePanel("ChemWaste");
        focus.addCurrentPoints(points);
        //let the user know what points were added(when done/exit)
        JOptionPane.showMessageDialog(this, "Name: " + focus.getUserName()  +  "\nTotal points to be added: " + points + "\nOverall points: " + focus.getCurrentPoints());
        resetPanel();
    }//GEN-LAST:event_returnBtnActionPerformed
    
    //drop down menu and when a item is selected from the list a radio button(choicBTN) associated is automatically selected.
    private void drop_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drop_menuActionPerformed
        // TODO add your handling code here:
        String selected = (String)drop_menu.getSelectedItem();
        //on conditions
        if(toxic_contaminated_list.contains(selected.toLowerCase())){
            
         
            //set toxic radio button to true
            toxic_contaminated_btn.setSelected(true);
            //display to user/call to read from specific file (toxic_non_recyc.txt)
            displayContent(nonRecycDisposalReader(toxic_non_recyc_txt));
        }else if(corrosive_list.contains(selected.toLowerCase())){
            
        
            //set corrosive radio button to true
            corrosive_btn.setSelected(true);
            //display to user/call to read from specific file (corrosive_non_recyc.txt)
            displayContent(nonRecycDisposalReader(corrosive_non_recyc_txt));
        }else if(flammable_volatile_list.contains(selected.toLowerCase())){
           
          
            //set flammable_volatile_list radio button to true
            flammable_volatile_btn.setSelected(true);
            //display to user/call to read from specific file (flammable_volatile_non_recyc.txt)
            displayContent(nonRecycDisposalReader(flammable_volatile_non_recyc_txt));
        }else if(biohazard_pharma_list.contains(selected.toLowerCase())){
         
        
            //set flammable_volatile_list radio button to true
            biohazard_btn.setSelected(true);
            //display to user/call to read from specific file (biohazard_pharma_non_recyc.txt)
            displayContent(nonRecycDisposalReader(biohazard_pharma_non_recyc_txt));
        }else if(reactive_list.contains(selected.toLowerCase())){
        
       
            //set reactive radio button to true
            reactive_btn.setSelected(true);
            //display to user/call to read from specific file (reactive_non_recyc.txt)
            displayContent(nonRecycDisposalReader(reactive_non_recyc_txt));
        }else if(pesticides_poisons_list.contains(selected.toLowerCase())){
        
      
            //set pesticides radio button to true
            pesticides_btn.setSelected(true);
            //display to user/call to read from specific file (pesticides_poisons_non_recyc.txt)
            displayContent(nonRecycDisposalReader(pesticides_poisons_non_recyc_txt));
        }else if(radioactive_list.contains(selected.toLowerCase())){
           
        
            //set radioactive radio button to true
            radioactive_btn.setSelected(true);
            //display to user/call to read from specific file (radioactive_non_recyc.txt)
            displayContent(nonRecycDisposalReader(radioactive_non_recyc_txt));
        }else if(asbestos_sludge_list.contains(selected.toLowerCase())){
            
            //set asbestos_sludge radio button to true
            asbestos_sludge_btn.setSelected(true);
            //display to user/call to read from specific file (asbestos_sludge_non_recyc.txt)
            displayContent(nonRecycDisposalReader(asbestos_sludge_non_recyc_txt));
            
        }
        
    }//GEN-LAST:event_drop_menuActionPerformed

    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
        // TODO add your handling code here:
        //displays what was just written in bottom left window IF....
        if(!getTextAreaValue().equals("")){//input text area is not empty
            //if no type selected to inform user and exit
            if(choiceBtn().equals("")){
                JOptionPane.showMessageDialog(this, "Must select Type using radio button");
                return;
            }
                
            //get choice radio button
            choiceBtn();
            //get value from text area bottom right and write to the file
            writeToNonRecyc(getTextAreaValue());
            //display written value to file
            displayInputContentFromFile();
            //show points
            showPoints();
            //clearing input text
            text_area_input.setText("");
               
        }else{
            //selected radion button
            choiceBtn();
            //update and show points
            showPoints();
        }
        //clear selected radio button
        hazardButtonGroup.clearSelection();
        
    }//GEN-LAST:event_addBTNActionPerformed

    private void remove_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_BTNActionPerformed
        // TODO add your handling code here:
        displayInputContentFromFile();
        String num = JOptionPane.showInputDialog(this, "Enter the number of the line of data to remove").trim();
        if(num != "" && !removeChoiceBtn().equalsIgnoreCase("")){
            removeChoiceBtn();
            deleteFile(num);
            displayInputContentFromFile();
            showPoints();
        }else{
            JOptionPane.showMessageDialog(this, "A number of line and radio button of type must be selected to remove line");
        }    
    }//GEN-LAST:event_remove_BTNActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBTN;
    private javax.swing.JRadioButton asbestos_sludge_btn;
    private javax.swing.JRadioButton biohazard_btn;
    private javax.swing.JPanel block_holding_output;
    private javax.swing.JRadioButton corrosive_btn;
    private javax.swing.JTextArea display_Dmenu_text;
    private javax.swing.JScrollPane display_Dmenu_text_scroll;
    private javax.swing.JTextArea display_input_text;
    private javax.swing.JScrollPane display_input_text_scroll;
    private javax.swing.JComboBox<String> drop_menu;
    private javax.swing.JRadioButton flammable_volatile_btn;
    private javax.swing.ButtonGroup hazardButtonGroup;
    private javax.swing.JLabel heading;
    private javax.swing.JLabel not_in_list_heading;
    private javax.swing.JRadioButton pesticides_btn;
    private javax.swing.JTextPane points_display_ui;
    private javax.swing.JScrollPane points_display_ui_scroll;
    private javax.swing.JRadioButton radioactive_btn;
    private javax.swing.JRadioButton reactive_btn;
    private javax.swing.JButton remove_BTN;
    private javax.swing.JButton returnBtn;
    private javax.swing.JLabel safety_heading;
    private javax.swing.JTextArea text_area_input;
    private javax.swing.JScrollPane text_area_input_scroll;
    private javax.swing.JRadioButton toxic_contaminated_btn;
    // End of variables declaration//GEN-END:variables
}
