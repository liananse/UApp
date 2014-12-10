package com.liananse.uapp.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import net.tsz.afinal.bitmap.core.BitmapDisplayConfig;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.View.MeasureSpec;

public class UImageUtil {

	public static Bitmap convertViewToBitmap(View view) {
		Bitmap bitmap = null;
		if (view != null) {
			view.measure(
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
			view.buildDrawingCache();
			bitmap = view.getDrawingCache();
		}
		return bitmap;
	}

	/**
	 * 保存图片
	 * 
	 * @author liananse
	 * @param bitmap
	 * @param path
	 * @return 2013-8-22
	 */
	public static boolean saveBtimapToFile(Bitmap bitmap, String path) {
		boolean result = false;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			bis = new BufferedInputStream(new ByteArrayInputStream(
					baos.toByteArray()));
			int b = -1;
			while ((b = bis.read()) != -1) {
				bos.write(b);
			}
			result = true;
		} catch (Exception e) {
			result = false;
			try {
				bos.close();
				bis.close();
			} catch (Exception e1) {
				result = false;
			}
		} finally {
			try {
				bos.close();
				bis.close();
			} catch (Exception e) {
				result = false;
			}
		}
		return result;
	}

	public static byte[] bmpToByteArray(final Bitmap bmp,
			final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}

		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static BitmapDisplayConfig createDefaultDisplayConfig() {
		BitmapDisplayConfig config = new BitmapDisplayConfig();
		config.setAnimation(null);
		config.setAnimationType(BitmapDisplayConfig.AnimationType.fadeIn);
		return config;
	}

	public static byte[] getBitmapBytes(String path) {
		byte[] arrayOfByte = null;
		try {

			Bitmap bm = BitmapFactory.decodeFile(path);
			arrayOfByte = getBitmapBytes(bm);
		} catch (Exception e) {
		}

		return arrayOfByte;

	}

	// 把图片压缩到32KB以内，微信限制
	public static byte[] getBitmapBytes(Bitmap bitmap) {

		byte[] arrayOfByte = null;
		try {
			Bitmap localBitmap = Bitmap.createScaledBitmap(bitmap,
					bitmap.getWidth() / 2, bitmap.getHeight() / 2, true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			int options = 100;
			// 如果大于80kb则再次压缩,最多压缩三次
			while (baos.toByteArray().length / 1024 > 31 && options != 10) {
				// 清空baos
				baos.reset();
				// 这里压缩options%，把压缩后的数据存放到baos中
				localBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
				options -= 2;
			}

			arrayOfByte = baos.toByteArray();
			baos.close();

			localBitmap.recycle();
		} catch (Exception e) {
		}

		return arrayOfByte;
	}

	// 压缩bitmap图片大小(size 为文件大小 KB)
	public static Bitmap compressBitmapBytes(Bitmap bitmap, int size) {

		Bitmap localBitmap = null;
		try {
			localBitmap = Bitmap.createScaledBitmap(bitmap,
					bitmap.getWidth() / 2, bitmap.getHeight() / 2, true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

			int options = 100;
			// 循环压缩到指定大小以内
			while (baos.toByteArray().length / 1024 > size && options != 10) {
				// 清空baos
				baos.reset();
				// 这里压缩options%，把压缩后的数据存放到baos中
				localBitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
				options -= 5;// 每次质量缩减5%
			}

			// arrayOfByte = baos.toByteArray();
			baos.close();

			// localBitmap.recycle();
		} catch (Exception e) {
		}

		return localBitmap;
	}

	public static byte[] getBitmapBytes(Bitmap bitmap, int width, int height,
			boolean needRecycle) {
		Bitmap localBitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.RGB_565);
		Canvas localCanvas = new Canvas(localBitmap);
		int i;
		int j;
		if (bitmap.getHeight() > bitmap.getWidth()) {
			i = bitmap.getWidth();
			j = bitmap.getWidth();
		} else {
			i = bitmap.getHeight();
			j = bitmap.getHeight();
		}
		while (true) {
			localCanvas.drawBitmap(bitmap, new Rect(0, 0, i, j), new Rect(0, 0,
					width, height), null);
			if (needRecycle)
				bitmap.recycle();
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					localByteArrayOutputStream);
			localBitmap.recycle();
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			try {
				localByteArrayOutputStream.close();
				return arrayOfByte;
			} catch (Exception e) {
				e.printStackTrace();
			}
			i = bitmap.getHeight();
			j = bitmap.getHeight();
		}
	}

	// 需要对图片进行处理，否则微信会在log中输出thumbData检查错误
	public static byte[] getBitmapBytes(Bitmap bitmap, boolean needRecycle) {
		return getBitmapBytes(bitmap, 80, 80, needRecycle);
	}

	public static String getShareTempPath() {
		String path = null;

		String dir = UDataStorage
				.getExternalStoragePublicDirectory(UDataStorage.Dir_Pictures)
				+ "/fashion/temp/";
		path = dir + System.currentTimeMillis() + ".jpg";

		UDataStorage.ensureDir(dir);

		return path;
	}

}
