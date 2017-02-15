package com.psi1.utils;

import java.io.File;
import java.util.List;

import com.psi1.config.Configuration;

public class DirectoryUtils {
	public static String readDirectory(String directory,Configuration config){
		File folder= new File(directory);
		File[] files=folder.listFiles();
		int longitud=files.length;
		List<String> lista=config.getProperties().get(directory);
		if(longitud>lista.size()){
			return "ERROR --- Directory '"+directory+"' has more files than specified on the config file";
		} else if(longitud<lista.size()){
			return "ERROR --- Directory '"+directory+"' has less files than specified on the config file";
		} else {
			return "OK --- Directory '"+directory+"' has the correct number of files";
		}
	}
	public static String proveFile(String directory,String arch,Configuration config){
		File file= new File(directory+"\\"+arch);
		String hash=FileUtils.hashContent(FileUtils.readFile(file), config);
		
		return "OK --- Directory '"+directory+"' has the correct number of files";
	}
	

}
