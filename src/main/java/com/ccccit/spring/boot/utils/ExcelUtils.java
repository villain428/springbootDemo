package com.ccccit.spring.boot.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelUtils {
	
	private static XSSFWorkbook workbook;
	public static void init(String filePath) {
		//1、获取文件输入流
        InputStream inputStream;
		try {
			inputStream = new FileInputStream(filePath);
			//2、获取Excel工作簿对象
	        workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public static int getRowCount(int sheetIndex) {
		//3、得到Excel工作表对象
        XSSFSheet sheetAt = workbook.getSheetAt(sheetIndex);
        return sheetAt.getPhysicalNumberOfRows();
	}
	
	//读取的方法
    public static String getCellValue(int sheetIndex, int rowIndex, int cellIndex){
            
        //3、得到Excel工作表对象
        XSSFSheet sheetAt = workbook.getSheetAt(sheetIndex);
        XSSFCell cell = sheetAt.getRow(rowIndex).getCell(cellIndex);
        return cell == null ? null : cell.getStringCellValue();
    }
    
    public static void destory() {
    	if(workbook != null) {
    		try {
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    
    public static void main(String[] args) {
    	double a = 123.465;
    	BigDecimal bd = new BigDecimal(a);
    	DateFormat df = new SimpleDateFormat();
    	System.out.println(String.format("%07d", 1));
	}
}
