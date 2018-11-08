/**
 * Creation Date:2017年4月25日-上午11:36:00
 * 
 * Copyright 2010-2017 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import org.apache.commons.io.IOUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.Iterator;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2017年4月25日-上午11:36:00
 * @since 2017年4月25日-上午11:36:00
 */
public final class ImageUtils {

	private ImageUtils() {}

    /**
     * 重调图片尺寸
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:46:27
     * @param input 图片输入流
     * @param output 图片输出流
     * @param width 宽度大于1的整数,单位：px，如果需要按宽度等比例缩放，请将height设置为-1
     * @param height 高度为大于1的整数，单位：px，如果需要按高度等比例缩放，请将width设置为-1
     * @throws Exception void
     */
    public static void resize(InputStream input, OutputStream output, int width, int height) throws Exception {
        resize(input, output, width, height, -1, -1);
    }

    /**
     * 重调图片尺寸
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:46:14
     * @param input 图片输入流
     * @param output 图片输出流
     * @param width 宽度大于1的整数,单位：px，如果需要按宽度等比例缩放，请将height设置为-1
     * @param height 高度为大于1的整数，单位：px，如果需要按高度等比例缩放，请将width设置为-1
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @throws Exception void
     */
    public static void resize(InputStream input, OutputStream output,
            int width, int height, int maxWidth, int maxHeight) throws Exception {

        if (width < 1 && height < 1 && maxWidth < 1 && maxHeight < 1) {
            try {
                IOUtils.copy(input, output);
            } catch (IOException e) {
                throw new Exception("resize error: ", e);
            }
        }
        try {
            BufferedImage img = ImageIO.read(input);
            boolean hasNotAlpha = !img.getColorModel().hasAlpha();
            double w = img.getWidth(null);
            double h = img.getHeight(null);
            int toWidth;
            int toHeight;
            double rate = w / h;

            if (width > 0 && height > 0) {
                rate = ((double) width) / ((double) height);
                toWidth = width;
                toHeight = height;
            } else if (width > 0) {
                toWidth = width;
                toHeight = (int) (toWidth / rate);
            } else if (height > 0) {
                toHeight = height;
                toWidth = (int) (toHeight * rate);
            } else {
                toWidth = ((Number) w).intValue();
                toHeight = ((Number) h).intValue();
            }

            if (maxWidth > 0 && toWidth > maxWidth) {
                toWidth = maxWidth;
                toHeight = (int) (toWidth / rate);
            }
            if (maxHeight > 0 && toHeight > maxHeight) {
                toHeight = maxHeight;
                toWidth = (int) (toHeight * rate);
            }

            BufferedImage tag = new BufferedImage(toWidth, toHeight, hasNotAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);

            // Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
            Graphics2D graphics2d = (Graphics2D)tag.getGraphics();
            graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//            graphics2d.drawImage(img.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH), 0, 0, null);
            graphics2d.drawImage(img, 0, 0, toWidth, toHeight, null);
            ImageIO.write(tag, hasNotAlpha ? "jpg" : "png", output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }

    }
    
    /**
     * 此方法将图片按给定的大小，先进行等比例缩放，然后再将图像进行居中裁剪
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年7月28日-下午12:56:19
     * @param input 图片输入流
     * @param output 图片输出流
     * @param width 图像裁剪后的宽度
     * @param height 图像裁剪后的高度
     * @throws Exception void
     */
    public static void resizeAndCrop(InputStream input, OutputStream output, int width, int height) throws Exception{
        try {
            BufferedImage img = ImageIO.read(input);
            boolean hasNotAlpha = !img.getColorModel().hasAlpha();
            double w = img.getWidth(null);
            double h = img.getHeight(null);
            double rate = Math.max(width/w, height/h);
            
            int toWidth = (int)Math.round(w*rate);
            int toHeight = (int)Math.round(h*rate);

            BufferedImage tag = new BufferedImage(toWidth, toHeight, hasNotAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);

            // Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
            tag.getGraphics().drawImage(img.getScaledInstance(toWidth, toHeight, Image.SCALE_SMOOTH), 0, 0, null);
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(tag, hasNotAlpha ? "jpg" : "png", baos);
            ImageUtils.crop(new ByteArrayInputStream(baos.toByteArray()), output, width, height, hasNotAlpha);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * 图像裁剪
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:45:52
     * @param input
     * @param output
     * @param x
     * @param y
     * @param w
     * @param h
     * @param isPNG
     * @throws Exception void
     */
    public static void crop(InputStream input, OutputStream output, int x,
            int y, int w, int h, boolean isPNG) throws Exception {
        try {
            BufferedImage srcImg = ImageIO.read(input);
            int tmpWidth = srcImg.getWidth();
            int tmpHeight = srcImg.getHeight();
            int xx = Math.min(tmpWidth - 1, x);
            int yy = Math.min(tmpHeight - 1, y);

            int ww = w;
            if (xx + w > tmpWidth) {
                ww = Math.max(1, tmpWidth - xx);
            }
            int hh = h;
            if (yy + h > tmpHeight) {
                hh = Math.max(1, tmpHeight - yy);
            }

            BufferedImage dest = srcImg.getSubimage(xx, yy, ww, hh);

            BufferedImage tag = new BufferedImage(w, h, isPNG ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(dest, 0, 0, null);
            ImageIO.write(tag, isPNG ? "png" : "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }
    
    /**
     * 对图像进行居中才将
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年7月28日-下午12:59:18
     * @param input
     * @param output
     * @param w
     * @param h
     * @param isPNG
     * @throws Exception void
     */
    public static void crop(InputStream input, OutputStream output, int w, int h, boolean isPNG) throws Exception {
        try {
            BufferedImage srcImg = ImageIO.read(input);
            int tmpWidth = srcImg.getWidth();
            int tmpHeight = srcImg.getHeight();
            int xx = 0, yy = 0;
            if(w < tmpWidth){
            	xx = Math.min(tmpWidth - 1, (tmpWidth-w)/2);
            }
            if(h < tmpHeight){
            	yy = Math.min(tmpHeight - 1, (tmpHeight-h)/2);
            }
            
            int ww = w;
            if (xx + w > tmpWidth) {
                ww = Math.max(1, tmpWidth - xx);
            }
            int hh = h;
            if (yy + h > tmpHeight) {
                hh = Math.max(1, tmpHeight - yy);
            }
            
            BufferedImage dest = srcImg.getSubimage(xx, yy, ww, hh);

            BufferedImage tag = new BufferedImage(w, h, isPNG ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(dest, 0, 0, null);
            ImageIO.write(tag, isPNG ? "png" : "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(output);
        }
    }

    /**
     * 设置图片压缩质量
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:44:59
     * @param input
     * @param output
     * @param quality
     * @throws Exception void
     */
    public static final void optimize(InputStream input, OutputStream output, float quality) throws Exception {

        BufferedImage image;
        ImageOutputStream ios = null;
        ImageWriter writer = null;
        try {
            image = ImageIO.read(input);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");

            if (!writers.hasNext())
                throw new IllegalStateException("No writers found");

            writer = (ImageWriter) writers.next();
            ios = ImageIO.createImageOutputStream(output);

            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);

            writer.write(null, new IIOImage(image, null, null), param);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        } finally {
            if (ios != null) {
                try {
                    ios.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new Exception(e);
                }
            }
            writer.dispose();
        }
    }

    /**
     * 图像圆角
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:42:37
     * @param inputStream
     * @param outputStream
     * @param radius
     * @throws Exception void
     */
    public static void makeRoundedCorner(InputStream inputStream,
            OutputStream outputStream, int radius) throws Exception {
        BufferedImage sourceImage = null;
        BufferedImage targetImage = null;
        try {
            sourceImage = ImageIO.read(inputStream);
            int w = sourceImage.getWidth();
            int h = sourceImage.getHeight();
            System.out.println(w);

            int cornerRadius = radius < 1 ? w / 4 : radius;

            targetImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = targetImage.createGraphics();

            g2.setComposite(AlphaComposite.Src);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));

            g2.setComposite(AlphaComposite.SrcAtop);
            g2.drawImage(sourceImage, 0, 0, null);
            g2.dispose();
            ImageIO.write(targetImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e);
        }
    }
	
    /**
     * 图像灰度化
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:42:20
     * @param inputStream
     * @param outputStream
     * @throws Exception void
     */
    public static void toGray(InputStream inputStream, OutputStream outputStream) throws Exception{
    	BufferedImage image = ImageIO.read(inputStream);
        
        int width = image.getWidth();
        int height = image.getHeight();
          
        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
            	int rgb = image.getRGB(i, j);
            	grayImage.setRGB(i, j, rgb);
            }
        }
        ImageIO.write(grayImage, "jpg", outputStream);
    }
    
    /**
     * 图像二值化
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午1:55:14
     * @param inputStream
     * @param outputStream
     * @throws Exception void
     */
    public static void toBinary(InputStream inputStream, OutputStream outputStream) throws Exception{
    	BufferedImage image = ImageIO.read(inputStream);
        
        int width = image.getWidth();
        int height = image.getHeight();
          
        BufferedImage binaryImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
            	int rgb = image.getRGB(i, j);
            	binaryImage.setRGB(i, j, rgb);
            }
        }
        ImageIO.write(binaryImage, "jpg", outputStream);
    }
    
    /**
     * 图像边缘检测
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:22:33
     * @param inputStream
     * @param outputStream
     * @throws Exception void
     */
    public static void toEdge(InputStream inputStream, OutputStream outputStream) throws Exception{
    	BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(toEdge(image), "jpg", outputStream);
    }
    
    /**
     * 图像模糊处理
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:23:09
     * @param inputStream
     * @param outputStream
     * @throws Exception void
     */
    public static void toBlur(InputStream inputStream, OutputStream outputStream) throws Exception{
    	BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(toBlur(image), "jpg", outputStream);
    }
    
    /**
     * 图像锐化处理
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:23:28
     * @param inputStream
     * @param outputStream
     * @throws Exception void
     */
    public static void toSharpen(InputStream inputStream, OutputStream outputStream) throws Exception{
    	BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(toSharpen(image), "jpg", outputStream);
    }
    
    /**
     * 图像边缘检测
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:05:16
     * @param originalPic
     * @return BufferedImage
     */
    public static BufferedImage toEdge(BufferedImage originalPic) {
		float[] data = { 
					0.0f, -1.0f, 0.0f, 
					-1.0f, 4.0f, -1.0f, 
					0.0f,-1.0f, 0.0f 
				};

		return applyImageFilter(originalPic, data);
	}
    
    /**
     * 图像模糊处理
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:19:08
     * @param originalPic
     * @return BufferedImage
     */
    public static BufferedImage toBlur(BufferedImage originalPic){
    	float[] data = {
    			0.111f, 0.111f, 0.111f, 
    	        0.111f, 0.111f, 0.111f, 
    	        0.111f, 0.111f, 0.111f,
        };
    	return applyImageFilter(originalPic, data);
    }
    
    
    /**
     * 图像锐化处理
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:19:47
     * @param originalPic
     * @return BufferedImage
     */
    public static BufferedImage toSharpen(BufferedImage originalPic){
    	float[] data = {
                -1.0f, -1.0f, -1.0f,
                -1.0f, 9.0f, -1.0f,
                -1.0f, -1.0f, -1.0f
        };
    	return applyImageFilter(originalPic, data);
    }
    
    
    /**
     * 图像滤镜
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:14:05
     * @param bufImage
     * @param data 滤镜参数
     * @return BufferedImage
     */
    private static BufferedImage applyImageFilter(BufferedImage bufImage, float[] data) {
        if (bufImage == null)
            return null; 
        Kernel kernel = new Kernel(3, 3, data);
        
        ConvolveOp imageOp = new ConvolveOp(kernel,ConvolveOp.EDGE_NO_OP, null);//创建卷积变换操作对象  
        BufferedImage filteredBufImage = new BufferedImage(bufImage.getWidth(),
        		bufImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);//过滤后的缓冲区图像  
        imageOp.filter(bufImage, filteredBufImage);//过滤图像，目标图像在filteredBufImage
        return filteredBufImage;
    }
    
    /**
     * 图像加水印处理
     * 
     * @author 	郁晓勇
     * @version 1.0.0, 2017年4月25日-下午2:07:36
     * @param source
     * @param mask
     * @param alpha
     * @return
     * @throws Exception BufferedImage
     */
	public static BufferedImage addWatermark(BufferedImage source, BufferedImage mask, Float alpha)throws Exception{
		int w1 = source.getWidth();
		int h1 = source.getHeight();
		int w2 = mask.getWidth();
		int h2 = mask.getHeight();
		BufferedImage dest = new BufferedImage(w1,h1,BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D)dest.getGraphics();
		graphics.drawImage(source,0,0,w1,h1,null);
		if(alpha != null){
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
		}
		graphics.drawImage(mask, w1-w2-14, h1-h2-14, w2, h2, null);
		graphics.setComposite(AlphaComposite.SrcOver);
		graphics.dispose();
		return dest;
	}
    
    /**
     * 获取图片类型
     *
     * @param file 文件
     * @return 图片类型
     */
    public static String getImageType(File file) {
        if (file == null) return null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            return getImageType(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        	IOUtils.closeQuietly(is);
        }
    }

    /**
     * 流获取图片类型
     *
     * @param is 图片输入流
     * @return 图片类型
     */
    public static String getImageType(InputStream is) {
        if (is == null) return null;
        try {
            byte[] bytes = new byte[8];
            return is.read(bytes, 0, 8) != -1 ? getImageType(bytes) : null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取图片类型
     *
     * @param bytes bitmap的前8字节
     * @return 图片类型
     */
    public static String getImageType(byte[] bytes) {
        if (isJPEG(bytes)) return "JPEG";
        if (isGIF(bytes)) return "GIF";
        if (isPNG(bytes)) return "PNG";
        if (isBMP(bytes)) return "BMP";
        return null;
    }

    private static boolean isJPEG(byte[] b) {
        return b.length >= 2
                && (b[0] == (byte) 0xFF) && (b[1] == (byte) 0xD8);
    }

    private static boolean isGIF(byte[] b) {
        return b.length >= 6
                && b[0] == 'G' && b[1] == 'I'
                && b[2] == 'F' && b[3] == '8'
                && (b[4] == '7' || b[4] == '9') && b[5] == 'a';
    }

    private static boolean isPNG(byte[] b) {
        return b.length >= 8
                && (b[0] == (byte) 137 && b[1] == (byte) 80
                && b[2] == (byte) 78 && b[3] == (byte) 71
                && b[4] == (byte) 13 && b[5] == (byte) 10
                && b[6] == (byte) 26 && b[7] == (byte) 10);
    }

    private static boolean isBMP(byte[] b) {
        return b.length >= 2
                && (b[0] == 0x42) && (b[1] == 0x4d);
    }
    
    
    public static void main(String[] args) throws FileNotFoundException, Exception {
		ImageUtils.resize(new FileInputStream("D:\\a.jpg"), new FileOutputStream("D:\\a1.jpg"), -1, 300);
//		ImageUtils.toSharpen(new FileInputStream("D:\\a.jpg"), new FileOutputStream("D:\\a2.jpg"));
	}
}

