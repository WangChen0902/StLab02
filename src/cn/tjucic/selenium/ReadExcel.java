package cn.tjucic.selenium;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// 读取excel的类
public class ReadExcel {
	public List<Object[]> read() throws Exception {
		String filePath = System.getProperty("user.dir") + "\\软件测试名单.xlsx";
		System.out.println(filePath);
		File file = new File(filePath);
		System.out.println(file);
		List<Object[]> result = new ArrayList<Object[]>();
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		for(int rowIndex=2; rowIndex<=sheet.getPhysicalNumberOfRows(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if(row != null) {
				Object[] infoList = new Info[1];
				Info info = new Info();
				info.id = String.valueOf((long)row.getCell(1).getNumericCellValue());
				info.name = row.getCell(2).getStringCellValue();
				info.github = row.getCell(3).getStringCellValue();
				info.password = info.id.substring(4);
				infoList[0] = info;
				result.add(infoList);
			}
		}
		System.out.println("read");
		return result;
	}
}
