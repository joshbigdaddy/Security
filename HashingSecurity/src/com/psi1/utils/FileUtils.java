package com.psi1.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.psi1.config.Configuration;

public class FileUtils {
	
	/**
     *  Method to read a file
     */
	public static String readFile(String directory) {
		File file = new File(directory);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		byte[] data = new byte[(int) file.length()];
		try {
			fis.read(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String str = null;
		try {
			str = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
     *  Method to read a file
     */
	public static String readFile(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		byte[] data = new byte[(int) file.length()];
		try {
			fis.read(data);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String str = null;
		try {
			str = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
     *  Method to hash the content with salt
     */
	public static String hashContent(String content, Configuration config) {
		byte[] convertme = null;
		try {
			content=content+"A>0@3%`sx4bvP35YuAe|";
			convertme = content.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(config.getAlgoritmo());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return byteArrayToHexString(md.digest(convertme));

	}

	public static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	
	/**
     *  Method to log a string
     */
	public static void logToFile(String message, Configuration config)

	{

		try {
			File dataFolder = new File(config.getGlobalConfig().getLogsDirectory());
			if (!dataFolder.exists()) {
				dataFolder.mkdirs();
			}
			Date now2 = new Date();
			SimpleDateFormat formato = new SimpleDateFormat(
					"yyyy-MM-dd");
			boolean res=true;
			File saveTo = new File(config.getGlobalConfig().getLogsDirectory(), formato.format(now2)+"-log.txt");
			if (!saveTo.exists()) {
				saveTo.createNewFile();
				res=false;
			}

			FileWriter fw = new FileWriter(saveTo, true);

			PrintWriter pw = new PrintWriter(fw);
			Date now = new Date();
			SimpleDateFormat format = new SimpleDateFormat(
					"dd-MM-yyyy HH:mm:ss");
			if(!res){
				pw.println("################################");
				pw.println("#                              #");
				pw.println("#          REVISION            #");
				pw.println("#                              #");
				pw.println("################################");
			}
			pw.println(format.format(now)+" : "+message);

			pw.flush();

			pw.close();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	/**
     *  Create the daily log, looking if all the directories/files/webs are correct
     */
	public static void proveAll(Configuration config)
	{
		Set<String> directories=config.getProperties().keySet();
		for(String s:directories){
			if(!s.equalsIgnoreCase("urls")){
				logToFile("#Checking directory: "+s,config);
				logToFile(DirectoryUtils.readDirectory(s, config),config);
				List<String> files=config.getProperties().get(s);
				for(String file:files){
					logToFile(DirectoryUtils.proveFile(s, file, config),config);
				}
			}
		}
		logToFile("#Checking webs",config);
		List<String> webs=config.getProperties().get("urls");
		for(String s:webs){
			
				logToFile(DirectoryUtils.proveWeb(s, config),config);
			
		}
	}

}
