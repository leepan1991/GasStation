package com.volunteer.manager.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @department 成都-产品运营-商务智能-java  
 * @description 
 * @author yaoh
 * @date 2014年11月18日 下午2:55:06 
 */

public class CSVReportView extends AbstractCsvView {

	private static final int BUFFER_SIZE = 64 * 1024;// 文件下载缓冲区

	private String filename;
	private File file;
//	private boolean autoDelete = false;

	/**
	 * 构造函数  ExcelReportView
	 * description 
	 */
	public CSVReportView() {
		super();
	}

	public CSVReportView(String filename, File file, boolean autoDelete) {
		super();
		this.filename = filename;
		this.file = file;
//		this.autoDelete = autoDelete;
	}

	public CSVReportView(String filename, File file) {
		super();
		this.filename = filename;
		this.file = file;
	}

	/**
	 * description 
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) {
		BufferedInputStream inputStream = null;
		OutputStream ouputStream = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(this.file);
			Properties props = System.getProperties();
			
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("MSIE") || null != agent  
	                   && -1 != agent.indexOf("Trident")) {// ie
				filename = java.net.URLEncoder.encode(filename, "UTF8");
			} else {
				String fileNameCodeType = props.get("sun.jnu.encoding").toString();
				this.filename = new String(filename.getBytes(fileNameCodeType), "ISO8859-1");
			}
			response.setHeader("Content-disposition", "attachment;filename=" + filename + ".csv");
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setContentLength(fileInputStream.available());
			inputStream = new BufferedInputStream(fileInputStream, BUFFER_SIZE);
			ouputStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int length = 0;
			while ((length = inputStream.read(buffer)) != -1) {
				ouputStream.write(buffer, 0, length);
				ouputStream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != ouputStream) {
					ouputStream.flush();
					ouputStream.close();
				}
			} catch (IOException ignore) {
				ignore.printStackTrace();
			} finally {
//				if (autoDelete) {
//					this.file.delete();
//				}
			}
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}