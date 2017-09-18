package com.volunteer.common;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Administrator on 2017/4/27.
 */
public class FileUploadUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
	public static int DEFAULT_SIZE = 1920;// 图片默认缩放比例
	public static String FILE_SAVE_PATH = SystemConf.getBySystem("FILE_SAVE_PATH_");// 存储图片的根目录
	public static String IMAGE_SUFFIX = ".jpg";

	public static void main(String[] args) {
		System.out.println(SystemConf.getBySystem("FILE_SAVE_PATH_"));
	}

	public static String uploadImage(CommonsMultipartFile file, int toWidth,
			int toHeight) {
		if (null == file) {
			return "";
		}
		UUID uuid = UUID.randomUUID();
		File savePath = new File(FILE_SAVE_PATH);
		if (!savePath.exists() || !savePath.isDirectory()) {// 如果不存在该目录，则创建该目录
			savePath.mkdir();
		}
		String fileId = uuid.toString().replace("-","");
		String path = FILE_SAVE_PATH + fileId + IMAGE_SUFFIX;
		if (!file.isEmpty()) {
			if (file.getFileItem() instanceof DiskFileItem) {
				FileItem fileItem = file.getFileItem();
				try {
					ImgUtil.resizeImg(fileItem.getInputStream(), path, toWidth,
							toHeight, true);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				logger.error("FileUploadUtil typeError:" + file.getFileItem());
				return "";
			}
		}
		return fileId;
	}

	public static String uploadImage(CommonsMultipartFile file) {
		return uploadImage(file, DEFAULT_SIZE, DEFAULT_SIZE);
	}

	public static boolean deleteImage(String fileId) {
		boolean flag = true;
		String path = FILE_SAVE_PATH + fileId + IMAGE_SUFFIX;
		try {
			File file = new File(path);
			file.delete();
		} catch (Exception e) {
			logger.error("FileUploadUtil deleteImage error fileId:" + fileId, e);
			flag = false;
		}
		return flag;
	}

	public static File getImage(String fileId) {
		return new File(getFileSavePath(fileId));
	}

	public static String getFileSavePath(String fileId){
		return FILE_SAVE_PATH + fileId + IMAGE_SUFFIX;
	}
}
