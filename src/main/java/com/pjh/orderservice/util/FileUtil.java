package com.pjh.orderservice.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
	public static void copyFile(byte[] file, String filePath, String fileName) throws Exception {
		createDirs(filePath);
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

	//生成缩略图
	public static void thumbnailImg(MultipartFile file, String filePath, String fileName) throws Exception {
		//得到上传时的原文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件格式
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        createDirs(filePath);
        
		Thumbnails.of(file.getInputStream()).scale(0.3f)
	        .outputQuality(0.1f)
	        .outputFormat(ext)
	        .toFile(filePath+fileName);
    }
	
	public static void createDirs(String path) throws Exception {
		File targetFile = new File(path);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
	}
	
	public static void delFile(String filename) throws Exception {
		File file = new File(filename) ;
		if (file.isFile()) {

			file.delete();//清理文件

		} else {

			File list[] = file.listFiles();

			if (list != null) {

				for (File f : list) {

					deleteFile(f);

				}

				file.delete();//清理目录
			}
		}
     }

	public static void deleteFile(File file) {
		if (file.isFile()) {

			file.delete();//清理文件

		} else {

			File list[] = file.listFiles();

			if (list != null) {

				for (File f : list) {

					deleteFile(f);

				}

				file.delete();//清理目录
			}
		}
	}
	
	/**
	 * 下载
	 * @param response response
	 * @param fileName 文件名
	 * @return 返回结果 成功或者文件不存在
	 */
	public static String downloadFile(HttpServletResponse response, String filePath, String fileName) {
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		try {
			response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;		
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(filePath + fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (FileNotFoundException e1) {
			//e1.getMessage()+"系统找不到指定的文件";
			return "系统找不到指定的文件";
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}


}
