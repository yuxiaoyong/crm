/**
 * Creation Date:2017年5月5日-下午2:13:06
 *
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 *
 * @author 	郁晓勇
 * @version 1.0.0, 2017年5月5日-下午2:13:06
 * @since 2017年5月5日-下午2:13:06
 */
@SuppressWarnings("deprecation")
public final class ExcelUtils {


	static public interface FieldConverter{

		public Object convert(String field, Object value, Object object) throws Exception;
	}

	static public class DefaultFieldConverter implements FieldConverter{

		@SuppressWarnings("rawtypes")
		@Override
		public Object convert(String field, Object value, Object bean) throws Exception{
			if(value == null){
				return "";
			}
			return value;
		}

	}

	private static FieldConverter DEFAULT_CONVERTER = new DefaultFieldConverter();
	/**
	 * 将数据导出到excel中
	 *
	 * @author 	郁晓勇
	 * @version 1.0.0, 2017年5月5日-下午3:43:13
	 * @param dataset
	 * @param properties
	 * @param names
	 * @param os
	 * @throws Exception void
	 */
	public static void writeExcelData(Collection<?> dataset, String[] properties, String[] names, OutputStream os, FieldConverter converter) throws Exception{
		if(converter == null){
			converter = DEFAULT_CONVERTER;
		}

		Collection<Object[]> records = new ArrayList<Object[]>();

		for(Object bean: dataset){
			Object[] record = new Object[properties.length];

			for(int i = 0; i < properties.length; i++){

				String field = properties[i];
				Object result = null;
				if(bean instanceof Map){
					result = ((Map) bean).get(field);
				}else{
					result = PropertyUtils.getProperty(bean, field);
				}

				record[i] = converter.convert(field, result, bean);
			}
			records.add(record);
		}
		writeExcelData(records, names, os);
	}

	/**
	 * 将数据导出到excel中
	 *
	 * @author 	郁晓勇
	 * @version 1.0.0, 2017年5月5日-下午3:43:25
	 * @param records
	 * @param names
	 * @param os
	 * @throws Exception void
	 */
	public static void writeExcelData(Collection<Object[]> records, String[] names, OutputStream os) throws Exception{
		try(Workbook wb = new XSSFWorkbook()){
			Sheet sheet = wb.createSheet();
			int i = 0, j = 0;
			Row row = sheet.createRow(i++);
			for(String name: names){
				Cell cell = row.createCell(j++);
				setCellValue(cell, name);
			}

			for(Object[] record: records){
				row = sheet.createRow(i++);
				j = 0;
				for(Object field: record){
					Cell cell = row.createCell(j++);
					setCellValue(cell, field);
				}
			}
			wb.write(os);

		}finally{
		}
	}



	private static void setCellValue(Cell cell, Object value){
		if(value instanceof Date){
			cell.setCellValue((Date)value);
		}else{
			cell.setCellValue(value+"");
		}
	}

	/**
	 * 读取excel中的数据
	 *
	 * @author 	郁晓勇
	 * @version 1.0.0, 2017年5月5日-下午4:43:56
	 * @param is
	 * @return
	 * @throws Exception List<Map<String,Object>>
	 */
	public static List<Map<String, String>> readExcelData(InputStream is) throws Exception{
		List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
		try(Workbook wb = WorkbookFactory.create(is)){
			Sheet sheet = wb.getSheetAt(0);
			int rowNum = sheet.getLastRowNum();
			Row row = sheet.getRow(0);
			int colNum = row.getPhysicalNumberOfCells();

			String[] headers = new String[colNum];
			for(int i = 0; i < colNum; i++){
				headers[i] = getCellFormatValue(row.getCell((short) i)).trim();
			}

			for (int i = 1; i <= rowNum; i++) {
				Map<String, String> record = new HashMap<String, String>();
				row = sheet.getRow(i);
				int j = 0;
				while (j < colNum) {
					record.put(headers[j], getCellFormatValue(row.getCell((short) j)).trim());
					j++;
				}
				datas.add(record);
			}
		} finally{}

		return datas;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * @param cell
	 * @return
	 */
	private static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell == null) {
			return cellvalue;
		}
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
			case Cell.CELL_TYPE_FORMULA: {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				}
				else {
					cellvalue = new DecimalFormat("#").format(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellvalue = " ";
		}
		return cellvalue;

	}

	public static void main(String[] args) throws Exception{
		List<Map<String, String>> datas = new ArrayList<>();
		datas.add(ImmutableMap.<String, String>of("aaaa", "aaaaa"));
		datas.add(ImmutableMap.<String, String>of("aaaa", "aaaaa"));
		datas.add(ImmutableMap.<String, String>of("aaaa", "aaaaa"));
		ExcelUtils.writeExcelData(datas, new String[]{"aaaa"}, new String[]{"aaaa"}, new FileOutputStream("H:\\aaa.xlsx"), null);
	}
}

