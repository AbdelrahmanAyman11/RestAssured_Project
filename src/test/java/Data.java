import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Data {

    public String[][] getExcelData() throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        File inputStream = new File("C:\\Users\\Dell\\Desktop\\RestAssured.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int rowNumbers = sheet.getPhysicalNumberOfRows();
        int colNumbers = sheet.getRow(0).getLastCellNum();
        String[][] data = new String[rowNumbers-1][colNumbers];

        for(int i=1;i<rowNumbers;i++) {
            for(int j=0;j<colNumbers;j++) {
                XSSFRow row= sheet.getRow(i);
                data[i-1][j]=row.getCell(j).toString();
            }
        }

        return data;
    }
}