
package Advanced_Project;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Child {
     private String io,FieldName,Type,AllowedValue,Mandatory,parent,API;
     ObservableList names = FXCollections.observableArrayList();
    public void setParent(String parent) {
        this.parent = parent;
    }

    public Child(String io, String FieldName, String Type, String AllowedValue, String Mandatory) {
        this.io = io;
        this.FieldName = FieldName;
        this.Type = Type;
        this.AllowedValue = AllowedValue;
        this.Mandatory = Mandatory;
    }
 public Child(){}
    public String getIo() {
        return io;
    }

    public String getFieldName() {
        return FieldName;
    }

    public String getType() {
        return Type;
    }

    public String getAllowedValue() {
        return AllowedValue;
    }

    public String getMandatory() {
        return Mandatory;
    }

    public void setIo(String io) {
        this.io ="I/O: "+ io;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setAllowedValue(String AllowedValue) {
        if (AllowedValue==null){this.AllowedValue="Allowed Values: All is allowed";}
        else{this.AllowedValue = "Allowed Values: "+AllowedValue;}
    }

    public String getParent() {
        return parent;
    }

    public void setMandatory(String Mandatory) {
        this.Mandatory = "Mandatory: "+Mandatory;
    }

    public String getAPI() throws IOException {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    @Override
    public String toString() {
        return "Class:" + "io=" + io + ", FieldName=" + FieldName + ", parent=" + parent + ", Type=" + Type + ", AllowedValue=" + AllowedValue + ", Mandatory=" + Mandatory +  '\n';
    }
    public ObservableList getNames() {
            return names;
        }
   
    public void save_parent() throws IOException{
      String excelFilePath = ".\\Example.xlsx";
      storeData reader = new storeData();
       List<Child> listBooks = reader.readFromExcelFile(excelFilePath);         
        for (Child x:listBooks){
         if (x.getParent()!=null){
        
            for (int i=0; i<reader.Parent.size();i++){
            if ((x.getParent()).equalsIgnoreCase(reader.Parent.get(i))){
            names.set(i, x.getFieldName());       
    }
    }
    }
}
    }   
}
