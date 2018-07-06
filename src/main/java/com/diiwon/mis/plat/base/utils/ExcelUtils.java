package com.diiwon.mis.plat.base.utils;

import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * 
 * @author sunshine
 *
 */
public class ExcelUtils {

	/**
	 * 将POI的HSSFWorkbook对象输出至客户端
	 * @param response HttpServletResponse对象
	 * @param fileName 输出的文件名(不含扩展名)
	 * @param workbook 待输出HSSFWorkbook对象
	 */
	public static void output(HttpServletResponse response, String fileName, HSSFWorkbook workbook) {
		try {
			fileName = URLEncoder.encode(fileName, "utf-8") + ".xls";
			ServletOutputStream sos = response.getOutputStream();							//获得输出流
			response.reset();																//清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fileName);	//设定输出文件头
			response.setContentType("application/msexcel");									//定义输出类型
			workbook.write(sos);
			sos.flush();
            sos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void output(HttpServletResponse response, HttpServletRequest request, String fileName, HSSFWorkbook workbook) {
		try {
			String agent = (String) request.getHeader("USER-AGENT");
			if (agent != null && agent.indexOf("FireFox") == -1) {
				fileName = new String(fileName.toString().getBytes("UTF-8"), "ISO-8859-1") + ".xls";
			}
			else {
				fileName = URLEncoder.encode(fileName, "utf-8") + ".xls";
			}
			ServletOutputStream sos = response.getOutputStream();							//获得输出流
			response.reset();																//清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ fileName);	//设定输出文件头
			response.setContentType("application/msexcel");									//定义输出类型
			workbook.write(sos);
			sos.flush();
            sos.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置单元格
	 * @param cellnum 索引位置(从0开始)
	 * @param value 单元格填充内容
	 * @param row 单元格所在行对象
	 * @param cell 单元格对象
	 * @param style	单元格样式
	 */
	public static void cell(int cellnum, String value, HSSFRow row, HSSFCell cell, HSSFCellStyle style) {
		cell = row.createCell(cellnum);
		cell.setCellValue(new HSSFRichTextString(value));
		cell.setCellStyle(style);
	}
	
	/**
	 * 设置单元格样式
	 * @param workbook HSSFWorkbook对象
	 * @param Alignment 设置水平对齐(CellStyle.ALIGN_LEFT | CellStyle.ALIGN_CENTER | CellStyle.ALIGN_RIGHT)
	 * @return
	 */
	public static HSSFCellStyle cellStyle(HSSFWorkbook workbook, short Alignment) {
		HSSFFont font = workbook.createFont();						//设置字体
		font.setFontHeightInPoints((short)10);						//设置字体大小
		font.setFontName("宋体");									//设置字体名字
		HSSFCellStyle style = workbook.createCellStyle();			//设置样式
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);			//设置底边框
		style.setBottomBorderColor(HSSFColor.BLACK.index);			//设置底边框颜色
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);				//设置左边框
		style.setLeftBorderColor(HSSFColor.BLACK.index);			//设置左边框颜色
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);			//设置右边框
		style.setRightBorderColor(HSSFColor.BLACK.index);			//设置右边框颜色
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);				//设置顶边框
		style.setTopBorderColor(HSSFColor.BLACK.index);				//设置顶边框颜色
		style.setFont(font);										//在样式用应用设置的字体
		style.setWrapText(false);									//设置自动换行
		style.setAlignment(Alignment);								//设置水平对齐的样式
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);	//设置垂直对齐的样式为居中对齐
		return style;
	}
}
