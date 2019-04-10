package com.testuse;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PicIOTest {
	public byte[] getPictureByteArray(String filePath) {
		File pic = new File(filePath);
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;

		byte[] buffer =null;
		try {
			fis = new FileInputStream(pic);
//			baos = new ByteArrayOutputStream();
			buffer = new byte[fis.available()];
			// 用available是否就不需要ByteArrayOutputStream
//			byte[] buffer = new byte[fis.available()];
//			int i;			
//			while (( i = fis.read(buffer)) != -1) {
//				baos.write(buffer, 0, i);
//			}
			
			fis.read(buffer);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
//			if(baos != null) {
//				try {
//					baos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return buffer;
//		return baos.toByteArray();
	}
	
	public byte[] getPictureByteArray(InputStream in) {
		BufferedInputStream bIn = new BufferedInputStream(in);
		ByteArrayOutputStream baos = null;
		byte[] buffer = new byte[4 * 1024];
		int len;
		try {
			baos = new ByteArrayOutputStream();
			while((len = bIn.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bIn != null) {
				try {
					bIn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		return baos.toByteArray();
	}
	
	public void byteArrToFile(byte[] picByteArr) {
		FileOutputStream fos = null;
		
		try {
			fos =new FileOutputStream("P:/pic/1.png");
			fos.write(picByteArr);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void byteArrToFile(byte[] picByteArr, String filePath) {
		FileOutputStream fos = null;
		if(picByteArr == null) {
			System.out.print("沒有圖片" + " ");
			return;
		}
		
		try {
			fos =new FileOutputStream(filePath);
			fos.write(picByteArr);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
