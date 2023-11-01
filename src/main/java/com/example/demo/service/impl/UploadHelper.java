package com.example.demo.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadHelper {
	public boolean helper(MultipartFile file) throws IOException {
		final String UPLOAD_DIR= "C:\\Users\\pca43\\Desktop\\springboot\\demo\\src\\main\\resources\\static";
//		try {
//			InputStream is=file.getInputStream();
//			byte[] arr=new byte[is.available()];
//			is.read(arr);
//			OutputStream os=is.
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Files.copy(file.getInputStream(),Path.of(UPLOAD_DIR), StandardCopyOption.REPLACE_EXISTING);
		return true;
	}
}
