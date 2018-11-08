package com.xiaoyong.hrm.system.web;/**
 * Created by atlantisholic on 2018/8/26.
 */

import com.xiaoyong.common.util.ImageUtils;
import com.xiaoyong.hrm.system.domain.SysAttachment;
import com.xiaoyong.hrm.system.service.SysAttachmentService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;

/**
 * @ClassName SysAttachmentController
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/26 14:01
 * @Version 1.0.0
 **/
@Controller
@RequestMapping("/api/attachment")
public class SysAttachmentController {

    @Autowired
    SysAttachmentService sysAttachmentService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception{
        SysAttachment attachment = new SysAttachment();
        attachment.setContent(file.getBytes());
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setFileSize(file.getSize());
        return ResponseEntity.ok(sysAttachmentService.save(attachment));
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<?> download(String fileId, String resize, HttpServletRequest request) throws Exception{
        SysAttachment attachment = sysAttachmentService.findByFileId(fileId);
        String fileName = attachment.getFileName();
        if (request.getHeader("User-Agent").indexOf("MSIE") != -1) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else if (request.getHeader("User-Agent").indexOf("Mozilla/5.0") != -1) {
            fileName = new String(fileName.replaceAll(" ", "_").getBytes("UTF-8"), "ISO-8859-1");
        } else if (request.getHeader("User-Agent").indexOf("Mozilla/4.0") != -1) {
            fileName = URLEncoder.encode(fileName.replaceAll(" ", "_"), "UTF-8");
        } else {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        }

        String range = request.getHeader("Range");
        if(StringUtils.isNotBlank(range)){
            String[] startAndEnd = range.split("=")[1].split("-");
            long rangeStart = NumberUtils.toInt(startAndEnd[0]);
            long rangeEnd = 0;
            if(startAndEnd.length == 2){
                rangeEnd = NumberUtils.toInt(startAndEnd[1]);
            }else{
                rangeEnd = attachment.getFileSize()-1;
            }
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .header("Content-Type", attachment.getFileType())
                    .header("Accept-Ranges", "bytes")
                    .header("Content-Range", "bytes "+rangeStart+"-"+rangeEnd+"/"+attachment.getFileSize())
                    .header("Content-Disposition", "attachment;filename=\"" + fileName + "\"")
                    .lastModified(attachment.getCreateTime().getTime())
                    .body(ArrayUtils.subarray(attachment.getContent(), (int)rangeStart, (int)rangeEnd+1));
        }else{
            ScaleMode scaleMode = ScaleMode.parse(resize);

            if(scaleMode != null){

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                if(StringUtils.equals(scaleMode.getMode(), ScaleMode.SCALE_MODE_CROP)){
                    ImageUtils.resizeAndCrop(new ByteArrayInputStream(attachment.getContent()), baos, scaleMode.getWidth(), scaleMode.getHeight());
                }else{
                    ImageUtils.resize(new ByteArrayInputStream(attachment.getContent()), baos, scaleMode.getWidth(), scaleMode.getHeight());
                }

                return ResponseEntity.ok()
                        .header("Content-Type", attachment.getFileType())
                        .header("Content-Disposition", "attachment;filename=\"" + fileName + "\"")
                        .body(baos.toByteArray());
            }else{
                return ResponseEntity.ok()
                        .header("Content-Type", attachment.getFileType())
                        .header("Content-Disposition", "attachment;filename=\"" + fileName + "\"")
                        .body(attachment.getContent());
            }

        }
    }

    static class ScaleMode{

        public static final String SCALE_MODE_RESIZE = "S";
        public static final String SCALE_MODE_CROP = "C";

        private int width;
        private int height;
        private String mode;

        public ScaleMode(int width, int height, String mode) {
            this.width = width;
            this.height = height;
            this.mode = mode;
        }

        public int getWidth() {
            return width;
        }
        public void setWidth(int width) {
            this.width = width;
        }
        public int getHeight() {
            return height;
        }
        public void setHeight(int height) {
            this.height = height;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public static ScaleMode parse(String pattern){
            if(StringUtils.isBlank(pattern)){
                return null;
            }
            String[] widthAndHeightAndMode = StringUtils.split(pattern, "X_");
            if( widthAndHeightAndMode.length != 3
                    || !NumberUtils.isDigits(widthAndHeightAndMode[0])
                    || !NumberUtils.isDigits(widthAndHeightAndMode[1])
                    || (!StringUtils.equals(widthAndHeightAndMode[2], ScaleMode.SCALE_MODE_RESIZE)
                    && !StringUtils.equals(widthAndHeightAndMode[2], ScaleMode.SCALE_MODE_CROP))){
                return null;
            }
            return new ScaleMode(NumberUtils.toInt(widthAndHeightAndMode[0]), NumberUtils.toInt(widthAndHeightAndMode[1]), widthAndHeightAndMode[2]);
        }

    }

}
