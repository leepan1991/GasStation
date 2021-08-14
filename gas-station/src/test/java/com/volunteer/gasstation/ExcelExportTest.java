package com.volunteer.gasstation;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by huoyao on 2021/8/14.
 */
public class ExcelExportTest {
    Map<String, Object> value = new HashMap<>();

    @Before
    public void testBefore() {
        List<Map<String, Object>> colList = new ArrayList<>();
        //先处理表头
        Map<String, Object> map = new HashMap<>();
        map.put("index", "a");
        map.put("name", "1");
        map.put("code", "2");
        map.put("remark", "3");
        colList.add(map);

        map = new HashMap<>();
        map.put("index", "b");
        map.put("name", "4");
        map.put("code", "5");
        map.put("remark", "6");
        colList.add(map);

        value.put("list", colList);

    }

    @Test
    public void one() throws Exception {
        TemplateExportParams params = new TemplateExportParams(
                "D:/workspace/slzc/GasStation/gas-station/src/main/resources/excelTemplate/低温绝热气瓶充装记录.xlsx");
        params.setColForEach(true);
        Workbook book = ExcelExportUtil.exportExcel(params, value);
        FileOutputStream fos = new FileOutputStream("D:/ExcelExportTemplateColFeTest_one.xlsx");
        book.write(fos);
        fos.close();

    }

}
