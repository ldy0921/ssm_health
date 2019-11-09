package comjxufe.test;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;

public class POITest {

    @Test
    public void testReider() throws Exception {
        //加载指定文件,创建Excel对象
        XSSFWorkbook sheets = new XSSFWorkbook("C:\\Users\\Administrator\\Desktop\\user.xlsx");

        //读取一个Excel文件中第一个标签页
        XSSFSheet sheet = sheets.getSheetAt(0);

        //遍历Sheet标签页,获得每一行数据
        for (Row row : sheet) {
            //遍历每一列
            for (Cell cell : row) {

                System.out.println(cell.getStringCellValue());

            }
        }



    }

    @Test
    public void testWrite() throws Exception {
        String path = "C:\\Users\\Administrator\\Desktop\\user.xlsx";

        // 创建
        XSSFWorkbook excel = new XSSFWorkbook();

        // 创建一个Excel工作簿
        XSSFSheet sheet = excel.createSheet("用户信息");

        XSSFRow head = sheet.createRow(0);
        head.createCell(0).setCellValue("姓名");
        head.createCell(1).setCellValue("地址");

        // 创建行,填充单元格
        XSSFRow row_1 = sheet.createRow(1);
        row_1.createCell(0).setCellValue("张三");
        row_1.createCell(1).setCellValue("北京");


        //创建文件
        FileOutputStream fos = FileUtils.openOutputStream(new File(path));
        excel.write(fos);
        fos.flush();
        excel.close();

    }
}
