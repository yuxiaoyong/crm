/**
 * Creation Date:2016年10月25日-下午5:32:55
 * 
 * Copyright 2010-2016 © 中格软件 Inc. All Rights Reserved
 */
package com.xiaoyong.common.util;



/**
 * Description Of The Class<br/>
 * QQ:603470086
 * 
 * @author 郁晓勇
 * @version 1.0.0, 2016年10月25日-下午5:32:55
 * @since 2016年10月25日-下午5:32:55
 */
public final class CoordinateUtils {

	// 地球半径 单位米
	private static final double EARTH_RADIUS = 6371004.0;

	// 最大精度(double 有效位 15位)
	private static final double MAX_PRECISION = 0.0000000000000001;

	// 180度
	private static final int RADIUS_180 = 180;

	private static final double DOUBLE_ZERO = 0D;
	private static final double DOUBLE_ONE = 1.0D;

	// 预先计算1 避免不必要的实时计算 浪费性能
	private static final double PRE_CAL1 = Math.PI / RADIUS_180;

	private CoordinateUtils() {
	}

	/**
	 * 计算两点距离(优化版本) 经纬度表示
	 * 
	 * @param lon1
	 *            点1的经度
	 * @param lat1
	 *            点1的纬度
	 * @param lon2
	 *            点2的经度
	 * @param lat2
	 *            点1的纬度
	 * @return 两点的弧度距离 单位:米
	 */
	public static double getDistance(double lon1, double lat1, 
									 double lon2, double lat2) {
		double ax = lat1 * PRE_CAL1;
		double bx = lat2 * PRE_CAL1;

		/**
		 * 由于double精度的问题 同样的坐标 计算出来的tempValueBeforeAcos
		 * 可能会大于1(如1.0000000000000002 正确值应该是1.0)
		 * 此时如果执行Math.acos(tempValueBeforeAcos) 会出现NaN情况 这边的判断 就是避免这种情况 但误差大于1时
		 * 就强制等于1 避免出现NaN问题
		 */
		double tempValueBeforeAcos = Math.sin(ax) * Math.sin(bx) + Math.cos(ax)
				* Math.cos(bx) * Math.cos((lon2 - lon1) * PRE_CAL1);
		return (tempValueBeforeAcos - DOUBLE_ONE) < MAX_PRECISION ? EARTH_RADIUS
				* Math.acos(tempValueBeforeAcos)
				: DOUBLE_ZERO;
	}

	public static void main(String[] args) {
		System.out.println(CoordinateUtils.getDistance(121.134622, 31.470546,
				121.141485, 31.462538));
//		double rad = DistanceUtils.distLawOfCosinesRAD(
//				DistanceUtils.toRadians(31.470546), 
//				DistanceUtils.toRadians(121.134622), 
//				DistanceUtils.toRadians(31.462538), 
//				DistanceUtils.toRadians(121.141485));
//		System.out.println(rad);
//		System.out.println(rad*DistanceUtils.EARTH_MEAN_RADIUS_KM*1000);
	}
}
