package com.chengxiang.mango.common.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class PoiUtil {
    /**
     * 创建文件到主机的默认temp文件夹下
     * 不好，不是平常的浏览器附件下载
     * @param workbook
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File createExcelFileToLocalTemp(Workbook workbook,String fileName) throws IOException {
        File file = File.createTempFile(fileName, ".xlsx");
        FileOutputStream out = new FileOutputStream(file.getAbsoluteFile());
        workbook.write(out);
        return file;
    }
}
