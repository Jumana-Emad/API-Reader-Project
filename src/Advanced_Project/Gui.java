/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Advanced_Project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
public class Gui extends Application {
   @Override
   public void start(Stage stage) throws IOException {      
      //list View for educational qualification
      final ObservableList names = FXCollections.observableArrayList();
      final ObservableList data = FXCollections.observableArrayList();
      final ObservableList details = FXCollections.observableArrayList();

      String excelFilePath = ".\\Example.xlsx";
      storeData reader = new storeData();
        List<Child> listBooks = reader.readFromExcelFile(excelFilePath);
        reader.save_parent();
        for(String c : reader.apiList){
        details.add(c);}

        for (int i=0;i<reader.Parent.size();i++) {
            String parent = reader.Parent.get(i);
            names.add(parent);}

        for(ArrayList<String> j:reader.aList){
                data.add(j);
        }
     
        final ListView listView = new ListView();         
         ListView<String> listView2 = new ListView<String>();
        ListView<String> listView3 = new ListView<String>();
        ListView<String> listView4 = new ListView<String>(details);
      //Creating the layout
 
      GridPane gridpane = new GridPane();
     gridpane.setHgap(5);
     gridpane.setVgap(5);
     gridpane.add(listView4, 0, 1);
     Label lbl = new Label("APIs");
     Font font4 = Font.font("verdana", FontWeight.BOLD, 12);
     gridpane.setHalignment(lbl,HPos.CENTER);
     lbl.setFont(font4);
     gridpane.add(lbl, 0, 0);
     gridpane.add(listView2, 1, 1);
     Label label = new Label("List of objects");
     Font font = Font.font("verdana", FontWeight.BOLD, 12);
     label.setFont(font);
     gridpane.add(label, 1, 0);
     gridpane.setHalignment(label,HPos.CENTER);
     Label k = new Label("Fields");
     Font font2 = Font.font("verdana", FontWeight.BOLD, 12);
     k.setFont(font2);
     gridpane.add(k, 2, 0);
     gridpane.add(listView, 2, 1);
     gridpane.setHalignment(k,HPos.CENTER);
     Label labels = new Label("Properties");
     Font font3 = Font.font("verdana", FontWeight.BOLD, 12);
     labels.setFont(font3);
     gridpane.add(labels, 3, 0);
     gridpane.add(listView3, 3, 1);
     gridpane.setHalignment(labels,HPos.CENTER);
     
      VBox layout = new VBox(10);
      layout.setPadding(new Insets(5, 5, 5, 50));
      layout.getChildren().addAll(gridpane);
      //Functionalities for On click of APIs
      listView4.getSelectionModel().selectedItemProperty().addListener((listar,oldvalue,newvalue)->{
        listView2.getItems().clear();
        
        for (Child x:listBooks){
         if (x.getParent()!=null&&(x.getParent().contains("obj"))){
             
        for (int i=0; i<reader.apiList.size();i++){
            try {
                if ((x.getAPI()).equalsIgnoreCase(reader.apiList.get(i))){
                    if (x.getAPI().equals(newvalue)){
                        
                        if(x.getAPI().equalsIgnoreCase(listView4.getSelectionModel().selectedItemProperty().getValue())){
                            if(!(listView2.getItems().contains(x.getParent()))){
                        listView2.getItems().add(x.getParent());}}}
                }       } catch (IOException ex) {}
    }
         }
        }
      });
//Functionalities for On click of Objects
      listView2.getSelectionModel().selectedItemProperty().addListener((listar,oldvalue,newvalue)->{
        listView.getItems().clear();
        
        for (Child x:listBooks){
         if (x.getParent()!=null){
        
        for (int i=0; i<reader.Parent.size();i++){
        if ((x.getParent()).equalsIgnoreCase(reader.Parent.get(i))){
            if (x.getParent().equals(newvalue))
              if(x.getParent().equalsIgnoreCase(listView2.getSelectionModel().selectedItemProperty().getValue())){
            if(!(listView.getItems().contains(x.getFieldName()))){
           listView.getItems().add(x.getFieldName());       }}
    }
    }
         }
        }
      });
      //Functionalities for On click of fields
      listView.getSelectionModel().selectedItemProperty().addListener((var listar,var oldvalue,var newvalue)->{
      listView3.getItems().clear();
        for (Child x:listBooks){
         if (x.getFieldName()!=null){
        
        for (int i=0; i<reader.fields.size();i++){
            
        if ((x.getFieldName()).equalsIgnoreCase(reader.fields.get(i))||x.getFieldName().contains("obj")){
            if (x.getFieldName().equals(newvalue)){ 
                try {
                    if(x.getParent().equalsIgnoreCase(listView2.getSelectionModel().selectedItemProperty().getValue())&&x.getAPI().equalsIgnoreCase(listView4.getSelectionModel().selectedItemProperty().getValue()))
                    {
                        listView3.getItems().add(x.getIo());
                        listView3.getItems().add(x.getType());
                        listView3.getItems().add(x.getAllowedValue());
                        listView3.getItems().add(x.getMandatory());
                        break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
                }
}
            
       }
            
        
    }
         }
        }
      });
      
      layout.setStyle("-fx-background-color: BEIGE");
      //Setting the stage
      
      Scene scene = new Scene(layout);
      stage.setTitle("API Data Reading");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
   }
   public static void main(String args[]){
      launch(args);
   }
}
