/**
 * Creation Date:2016年11月7日-上午11:02:01
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 	郁晓勇
 * @version 1.0.0, 2016年11月7日-上午11:02:01
 * @since 2016年11月7日-上午11:02:01
 */
public class QrCodeUtils {
	
	private QrCodeUtils() {}
	
	/**
	 * 根据二维码数据、宽度、高度生成二维码图片
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2017年2月23日-下午2:01:58
	 * @param value 二维码数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @return
	 * @throws Exception byte[]
	 */
	public static final byte[] generateQRCode(String value, int width, int height) throws Exception{
        String qrcodeFormat = "png";
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, baos);
        return baos.toByteArray();
	}
	
	/**
	 * 根据二维码数据、宽度、高度生成二维码图片
	 * 
	 * @author 	郁晓勇
	 * @version 1.0.0, 2017年2月23日-下午2:01:58
	 * @param value 二维码数据
	 * @param width 二维码宽度
	 * @param height 二维码高度
	 * @param margin 二维码边框
	 * @return
	 * @throws Exception byte[]
	 */
	public static final byte[] generateQRCode(String value, int width, int height, int margin) throws Exception{
        String qrcodeFormat = "png";
        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, margin);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, qrcodeFormat, baos);
        return baos.toByteArray();
	}
}

