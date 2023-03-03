package Advanced_Project;
//package net.codejava.excel;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class storeData {
    String s1="";
    String s="";
    String api="";
    ArrayList<String> fields = new ArrayList<>();
    ArrayList<String> Parent = new ArrayList<>();
    ArrayList<ArrayList<String> > aList = new ArrayList<>();
    ArrayList<String>  apiList = new ArrayList<>();
//    ArrayList<String> parent_obj1 = new ArrayList<>();
//    ArrayList<String> parent_obj2 = new ArrayList<>();
//    ArrayList<String> parent_obj3 = new ArrayList<>();
//    ArrayList<String> parent_obj4 = new ArrayList<>();
//    ArrayList<String> parent_obj5 = new ArrayList<>();
    
    List<Child> rows_as_obj;
    private Object getCellValue(Cell cell) {
    switch (cell.getCellType()) {
    case STRING:
        
        return cell.getStringCellValue();
 
    case BOOLEAN:
        return cell.getBooleanCellValue();
 
    case NUMERIC:
        return cell.getNumericCellValue();
    }
 
    return null;
}
     
   public List<Child> readFromExcelFile(String excelFilePath) throws IOException {
    rows_as_obj = new ArrayList<>();
    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
    
    Workbook workbook = new XSSFWorkbook(inputStream);
    Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> iterator = firstSheet.iterator();
    while (iterator.hasNext()) {
        
        Row nextRow = iterator.next();
        //if (nextRow.getCell(0)== null){continue;}
        Iterator<Cell> cellIterator = nextRow.cellIterator();
        Child aBook = new Child();
        aBook.setAPI(api);
        while (cellIterator.hasNext()) {
            Cell nextCell = cellIterator.next();
            int columnIndex = nextCell.getColumnIndex();
              
            switch (columnIndex) {
            case 0:
                if(nextCell.getStringCellValue().contains("REST Operation Mapping")){
                api=nextCell.getStringCellValue();
                apiList.add(api);
                aBook.setAPI(api);}
                aBook.setIo((String) getCellValue(nextCell));
                break;
            case 1:
               
                s= (String)getCellValue(nextCell);
                if(s!=null){
                String[] arrOfStr = s.split("/");
                int length =arrOfStr.length;
             
             if(length==1){aBook.setParent((String) arrOfStr[length-1]);}
             if (length>1){
              aBook.setFieldName((String) arrOfStr[length-1]);   
              aBook.setParent((String) arrOfStr[length-2]);
             }
                
                }
               

                break;
                
            case 2:
                aBook.setType((String) getCellValue(nextCell));
                if((aBook.getType())!=null){
               if ((aBook.getType()).equalsIgnoreCase("string")){
                  //if((aBook.getType()).equalsIgnoreCase("string"))  
                  fields.add(aBook.getFieldName());
                }
               else{ 
                   if(!((aBook.getType()).equalsIgnoreCase("Type"))){
                   Parent.add(aBook.getType());
                   ArrayList<String> t = new ArrayList<>();
                   aList.add(t);}
               
               }
                }
                break;
                case 3:
                aBook.setAllowedValue((String) getCellValue(nextCell));
                break;
                case 4:
                aBook.setMandatory((String) getCellValue(nextCell));
                break;
            }
 
 
        }
        
        rows_as_obj.add(aBook);
    }
 
    workbook.close();
    inputStream.close();
 
    return rows_as_obj;
}
 

public void save_parent(){
for (Child x:rows_as_obj){
    if (x.getParent()!=null){
        
    for (int i=0; i<Parent.size();i++){
        if ((x.getParent()).equalsIgnoreCase(Parent.get(i))){
            aList.get(i).add(x.getFieldName());
            
    }
    }

}
}
}
}