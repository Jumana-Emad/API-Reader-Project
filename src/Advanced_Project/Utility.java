package Advanced_Project;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jumana
 */
public class Utility {
    private String io,FieldName,Type,AllowedValue,Mandatory;
 private ArrayList<String> parent = new ArrayList<>();
 public void set_io(String io){
 this.io = io;
 }  
 public void set_Field_Name(String Fieldname){
// String Field_name = Fieldname.substring(1);
   this.FieldName=Fieldname;
 }   
 public void set_Type(String Type){
 this.Type = Type;
 }  
 public void set_AllowedValue(String AllowedValue){
 if (AllowedValue!="")this.AllowedValue= AllowedValue;
 else{ this.AllowedValue = "Null";}
 }  
 public void set_Mandatory(String Mandatory){
 this.Mandatory = Mandatory;
 }  
 public String get_io(){
 return io;
 }
 public ArrayList<String> get_FieldName(){
 ArrayList<String> list = new ArrayList<>(Arrays.asList(FieldName.split("/")));    
 ArrayList<String>newlist=Fieldname_edit(list); 
 return newlist;
 //return list;
 }
// public ArrayList<String> Fieldname_edit(ArrayList<String> x){
//  String child,field;
//  ArrayList<String> list = new ArrayList<>();
//  
//  child = x.get(x.size()-1);
//  if (x.size()>1){
//  field = x.get(x.size()-2);
//  list.add(field);}
//  list.add(child);
//  return list;
// }
 public ArrayList<String> Fieldname_edit(ArrayList<String> x){
  String child,field;
  ArrayList<String> children = new ArrayList<>();
  child = x.get(x.size()-1);
  if (x.size()>1){
  field = x.get(x.size()-2);
  if (this.parent.contains(field)){
  children.add(child);
  }
  this.parent.add(field);}
  else{this.parent.add(child);}
  return children;
 }
 public String get_Type(){
 return Type;
 }
 public String get_AllowedValue(){
 return AllowedValue;
 }
 public String get_Mandatory(){
 return Mandatory;
 }
 
 public void read_excel() throws FileNotFoundException, IOException{   
 String excelFilePath = ".\\Example.xlsx";
 FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
         
 XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
 XSSFSheet sheet = workbook.getSheet("Test");
 
 int rows= sheet.getLastRowNum();
 int colls=sheet.getRow(6).getLastCellNum();
  int apicount=0;   
  Utility e= new Utility();
 for(int r=0;r<=rows;r++){
    // if (apicount>1) break;
     XSSFRow row =sheet.getRow(r);
     
     for(int c=0;c<colls;c++){
          XSSFCell cell= row.getCell(c);
       
      while(cell!=null){
      
             switch(cell.getCellType()){
                 case STRING:
                     if (cell.getStringCellValue().contains("REST Operation")){
                    ++apicount;
                    if (apicount>1) System.out.println("API "+ apicount);
                 }
                     
                 if (cell.getStringCellValue().contains("/obj")){
                 e.set_Field_Name(cell.getStringCellValue());
                 System.out.println(e.get_FieldName());
                 }    
                 else{System.out.println(cell.getStringCellValue());}
                 break;
                 case NUMERIC: System.out.println(cell.getNumericCellValue());
                 break;
                 case BOOLEAN: System.out.println(cell.getBooleanCellValue());
                 break;
                 default:break;
             }
             break;
          }
          
              
          }
      if((row.getCell(0))!=null){
      System.out.println();
       
      }
      
      
      } 
 //System.out.println(apicount);
}  
 public void ExcelToObject()throws IOException, NullPointerException {
  try
          {
              FileInputStream file = new FileInputStream(new File(".\\Example.xlsx"));

              //Create Workbook instance holding reference to .xlsx file
              XSSFWorkbook workbook2 = new XSSFWorkbook(file);

              //Get first/desired sheet from the workbook
              XSSFSheet sheet2 = workbook2.getSheetAt(0);

      ArrayList<Utility> ObjectList = new ArrayList<>();
      ArrayList<Utility>ObjectList2=new ArrayList<>();
      ArrayList<Utility>ObjectList3=new ArrayList<>();
      ArrayList<Utility>ObjectList4=new ArrayList<>();
       ArrayList<Utility>picker=new ArrayList<>();
//      ObjectList= null;
//      ObjectList2= null;
//      ObjectList3= null;
//      ObjectList4= null;
    //I've Header and I'm ignoring header for that I've +6 in loop
    int count=0;
    int Lines = 0;
    int i=0;
              for(i=sheet2.getFirstRowNum();i<=sheet2.getLastRowNum();i++){
                  //try{
                  Utility e= new Utility();
                  XSSFRow ro=sheet2.getRow(i);
       
                  if((ro.getCell(0))!=null){
                  
                  
                  if( ro.getCell(0).getStringCellValue().contains("I/o")){
                      count++;
                      i++;
                  }
//                  if( (ro.getCell(0).getStringCellValue().contains("I")||ro.getCell(0).getStringCellValue().contains("i"))&&(ro.getCell(0).getStringCellValue().contains("O")||ro.getCell(0).getStringCellValue().contains("o"))){continue;}
//                  if(!( ro.getCell(0).getStringCellValue().contains("I")||ro.getCell(0).getStringCellValue().contains("O"))){continue;}
//                  
                 
                 for( ;i<=sheet2.getLastRowNum();i++){
                     
                 
                for(int j=ro.getFirstCellNum();j<=ro.getLastCellNum();j++){
                  XSSFCell ce = ro.getCell(j);
                  if(ce!=null){
                  //if( ro.getCell(0).getStringCellValue().contains("I")||ro.getCell(0).getStringCellValue().contains("O")){   
                      
                    if(j==0){  
                    e.set_io(ce.getStringCellValue());
                    }
                    if(j==1){
                        e.set_Field_Name(ce.getStringCellValue());
                        
                    }
                    if(j==2){
                        e.set_Type(ce.getStringCellValue());
                    } 
                    if(j==3){
                        e.set_AllowedValue(ce.getStringCellValue());
                    } 
                    if(j==4){
                        e.set_Mandatory(ce.getStringCellValue());
                    } 
                  //}
                                   
                  }
                  }
                 }
                 
                   switch(count){ 
                    case(1):ObjectList.add(e);
                            picker=ObjectList;
                            break;
                    case(2):ObjectList2.add(e);
                            picker=ObjectList2;
                            
                            break;
                    case(3):ObjectList3.add(e);
                            picker=ObjectList3;
                            
                            break;
                    case(4):ObjectList4.add(e);
                            picker=ObjectList4;
                            
                            break;
                
                   }
                   Lines++;
             // }
                  }
                  //catch (NullPointerException e){}
                      }
              for(Utility obj: ObjectList2){//prints first only to print all insert list instead o ObjectList
                  System.out.println("I/o:"+obj.get_io());
                  
                  
                  System.out.println("Field Name:"+obj.get_FieldName());
                  
                  System.out.println("Type:"+obj.get_Type());
                  if((obj.get_AllowedValue())!="Null"){ 
                  System.out.println("Allowed Value:"+obj.get_AllowedValue());}
                  System.out.println("Mandatory:"+obj.get_Mandatory());
              }
              
              //System.out.println(count);
              System.out.println("Lines Count = "+Lines);
              file.close();
          } 
          catch (IOException e) 
          {
          }
      
 
 
 }
 



}
