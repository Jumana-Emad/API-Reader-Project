
package Advanced_Project;
import java.util.*;

public class Parent {
     private String io,FieldName,Type,AllowedValue,Mandatory,parent;
     List<Child> parents = new ArrayList<>();

    public void setParent(String parent) {
        this.parent = parent;
        
    }

    public Parent(String io, String FieldName, String Type, String AllowedValue, String Mandatory) {
        this.io = io;
        this.FieldName = FieldName;
        this.Type = Type;
        this.AllowedValue = AllowedValue;
        this.Mandatory = Mandatory;
    }
 public Parent(){}
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
        this.io = io;
    }

    public void setFieldName(String FieldName) {
        this.FieldName = FieldName;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public void setAllowedValue(String AllowedValue) {
        this.AllowedValue = AllowedValue;
    }

    public void setMandatory(String Mandatory) {
        this.Mandatory = Mandatory;
    }

    @Override
    public String toString() {
        return "Class{" + "io=" + io + ", FieldName=" + FieldName + ", parent=" + parent + ", Type=" + Type + ", AllowedValue=" + AllowedValue + ", Mandatory=" + Mandatory +  '\n';
    }
    
   
    
    
}
