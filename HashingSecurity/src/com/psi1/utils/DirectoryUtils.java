package com.psi1.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.psi1.config.Configuration;

public class DirectoryUtils {
	
	/**
     *  Read all files from a directory to see if it has the correct amount of files
     */
	public static String readDirectory(String directory, Configuration config) {
		File folder = new File(directory);
		File[] files = folder.listFiles();
		int longitud = files.length;
		List<String> lista = config.getProperties().get(directory);
		if (longitud > lista.size()) {
			return "ERROR --- Directory '" + directory
					+ "' has more files than specified on the config file";
		} else if (longitud < lista.size()) {
			return "ERROR --- Directory '" + directory
					+ "' has less files than specified on the config file";
		} else {
			return "OK --- Directory '" + directory
					+ "' has the correct number of files";
		}
	}

	/**
     *  Read the file to see if its hash is correct
     */
	public static String proveFile(String directory, String arch,
			Configuration config) {
		String dir = directory + "\\" + arch;
		File file = new File(dir);
		if (file.exists()) {
			String hash = FileUtils.hashContent(FileUtils.readFile(file),
					config);
			if (config.getHashes().containsKey(dir)) {
				if (config.getHashes().get(dir).equals(hash)) {
					return "OK --- File '" + dir + "' hash correct";
				} else {
					return "ERROR --- File '" + dir + "' hash incorrect";
				}
			} else {
				config.getHashes().put(dir, hash);
				return "OK --- File '" + dir + "' hash not found --- CREATING";
			}
		} else {
			return "ERROR --- File '" + dir + "' does not exist";
		}

	}
	
	/**
     *  Read the web to see if its hash is correct
     */
	public static String proveWeb(String web,
			Configuration config) {
		
		String content=getContentFromHtmlPage(web);
		if (content!="") {
			String hash = FileUtils.hashContent(content,
					config);
			if (config.getHashes().containsKey(web)) {
				if (config.getHashes().get(web).equals(hash)) {
					return "OK --- Web '" + web + "' hash correct";
				} else {
					return "ERROR --- Web '" + web + "' hash incorrect";
				}
			} else {
				config.getHashes().put(web, hash);
				return "OK --- Web '" + web + "' hash not found --- CREATING";
			}
		} else {
			return "ERROR --- Web '" + web + "' content not found";
		}

	}
	
	/**
     *  Method to get the content from a web
     */
	public static String getContentFromHtmlPage(String page) {
		 URL url = null;
		try {
			url = new URL(page);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	        BufferedReader in = null;
			try {
				in = new BufferedReader(
				new InputStreamReader(url.openStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        String result="";
	        String inputLine;
	        try {
				while ((inputLine = in.readLine()) != null)
					result+=inputLine;
			} catch (IOException e) {
				e.printStackTrace();
			}
	        try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    

	    return result;
	}

}
