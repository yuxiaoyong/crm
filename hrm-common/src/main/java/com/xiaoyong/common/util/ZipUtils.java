package com.xiaoyong.common.util;/**
 * Created by atlantisholic on 2018/7/17.
 */

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName ZipUtils
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/7/17 17:12
 * @Version 1.0.0
 **/
public final class ZipUtils {

    private ZipUtils(){}

    /**
     * ZIP数据条目
     */
    static public class ZipEntryData {
        private String fileName;
        private byte[] content;

        public ZipEntryData(){}

        public ZipEntryData(String fileName, byte[] content){
            this.fileName = fileName;
            this.content = content;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }
    }

    public static byte[] compress(List<ZipEntryData> entryDatas) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        addZipEntry(entryDatas, zos);
        zos.close();
        return baos.toByteArray();
    }

    public static void compress(OutputStream os, List<ZipEntryData> entryDatas) throws Exception{
        ZipOutputStream zos = new ZipOutputStream(os);
        addZipEntry(entryDatas, zos);
        zos.close();
    }

    private static void addZipEntry(List<ZipEntryData> entryDatas, ZipOutputStream zos) throws IOException {
        for(ZipEntryData entryData: entryDatas) {
            ZipEntry entry = new ZipEntry(entryData.getFileName());
            zos.putNextEntry(entry);
            zos.write(entryData.getContent());
            zos.closeEntry();
        }
    }



}
