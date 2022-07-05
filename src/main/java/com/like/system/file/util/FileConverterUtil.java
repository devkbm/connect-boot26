package com.like.system.file.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileConverterUtil {

	private static final int BUFFER_SIZE = 8192;
	
	public static String getBase64String(File file) throws FileNotFoundException, IOException {
		
		byte[] byteArray = toByteArray(file);		
		
		return Base64.getEncoder().encodeToString(byteArray);		
	}
		
	public static File convertAndCopy(MultipartFile sourceFile, String destinationPath, String destinationFileName) throws FileNotFoundException, IOException {
		
		if(sourceFile == null || sourceFile.isEmpty()) throw new FileNotFoundException();
		
		File file = new File(destinationPath, destinationFileName);				
								
		FileConverterUtil.streamToFile(sourceFile.getInputStream(), file);	
		
		return file;
	}
	
		
	public static void streamToFile(InputStream is, File file) throws FileNotFoundException, IOException  {
		try (ReadableByteChannel	cin = Channels.newChannel(is);	
			 FileOutputStream 		fos = new FileOutputStream(file);
			 FileChannel 			cout = fos.getChannel();) {			
						
			 cout.transferFrom(cin, 0, is.available());						 				
		}		
	}	
	
	public static void fileToStream(File file, OutputStream os) throws FileNotFoundException, IOException {
			
		try (
			FileInputStream 	fis = new FileInputStream(file);
			FileChannel 		inChannel = fis.getChannel();
			WritableByteChannel outChannel = Channels.newChannel(os);) {										
			
			inChannel.transferTo(0, fis.available(), outChannel);			
		}		
	}	
	
	private static byte[] toByteArray(File file) throws FileNotFoundException, IOException {
		
		byte[] buffer;
		byte[] byteArray;
		int bytesRead 	= -1;		
				
		try (InputStream is = new FileInputStream(file);
			 BufferedInputStream bis = new BufferedInputStream(is);
			 ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
							
			buffer = new byte[BUFFER_SIZE];		
			
			while ((bytesRead = is.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			
			byteArray = bos.toByteArray();					
		} 
		
		return byteArray;
	}
}
