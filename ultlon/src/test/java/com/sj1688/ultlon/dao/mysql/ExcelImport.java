package com.sj1688.ultlon.dao.mysql;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.aspectj.weaver.reflect.IReflectionWorld;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by barton on 16-2-18.
 */
public class ExcelImport {

    /**
     * 读取excel标题
     *
     * @param filePath
     * @return
     */
    public static String[] readExcelTitle(String filePath) {
        XSSFWorkbook workbook = null;

        try {
            InputStream is = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow row = sheet.getRow(0);

        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();

        String[] title = new String[colNum];

        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }

        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @param filePath
     * @return
     */
    public static List<Map<Integer, String>> readExcelContent(String filePath) {
    	List<Map<Integer, String>> contentList = new ArrayList<Map<Integer, String>>();
        StringBuffer sb = new StringBuffer("");

        XSSFWorkbook workbook = null;

        try {
            InputStream is = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sheetNum = workbook.getNumberOfSheets();//获得sheet的个数
        
        //XSSFSheet sheet = workbook.getSheetAt(0);
        
		for(int i=0;i<sheetNum;i++){
			XSSFSheet sheet = workbook.getSheetAt(i);
			if(null!=sheet){
				contentList.add( getSheetContent(sb, sheet));
			}
		}
        return contentList;
    }

	private static Map<Integer, String> getSheetContent(StringBuffer sb, XSSFSheet sheet) {
		Map<Integer, String> content = new HashMap<>();
		// 得到总行数
        int rowNum = sheet.getLastRowNum();

        XSSFRow row = sheet.getRow(0);

        int colNum = row.getPhysicalNumberOfCells();

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;

            while (j < colNum) {
            	if(row!=null&&null!=row.getCell(j)){
	                sb.append(getCellFormatValue(row.getCell(j)).trim());
	
	                // 用4个空格分割开
	                sb.append("    ");
            	}

                j++;
            }

            // 将每一行的内容都put到map中
            content.put(i, sb.toString());

            // 重新初始化StringBuffer
            sb.setLength(0);
        }
        return content;
	}

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell
     * @return
     */
    private static String getStringCellValue(XSSFCell cell) {
        String strCell;

        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }

        if (StringUtils.isBlank(strCell)) {
            return "";
        }

        if (cell == null) {
            return "";
        }

        return strCell;
    }

    /**
     * 根据XSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(XSSFCell cell) {
        String cellValue;

        if (cell != null) {

            // 判断当前Cell的Type
            switch (cell.getCellType()) {

                // 如果当前Cell的Type为NUMERIC
                case HSSFCell.CELL_TYPE_NUMERIC:

                case HSSFCell.CELL_TYPE_FORMULA: {

                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值
                        cellValue = format(cell.getNumericCellValue());
                    }
                    break;
                }

                // 如果当前Cell的Type为STRING
                case HSSFCell.CELL_TYPE_STRING:

                    // 取得当前的Cell字符串
                    cellValue = cell.getRichStringCellValue().getString();
                    break;

                // 默认的Cell值
                default:
                    cellValue = " ";
            }
        } else {
            cellValue = " ";
        }
        return cellValue;

    }

    /**
     * double 转换成String
     * @param cellValue
     * @return
     */
    private static String format(double cellValue){
        NumberFormat numberFormat = new DecimalFormat("#.######");
        return numberFormat.format(cellValue);
    }
}
