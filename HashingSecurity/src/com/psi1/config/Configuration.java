package com.psi1.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Configuration {
	private String dir;
	private Map<String,List<String>> properties;
	private Map<String,String> hashes;
	private String algoritmo;
	private String tiempo;
	private GlobalConfiguration globalConfig;
	
	public Configuration( GlobalConfiguration global) {
		super();
		this.dir = global.getConfigurationFile();
		this.globalConfig=global;
		this.hashes=loadHashes();
		loadConfig(dir);
	}
	public Map<String,String> loadHashes(){
		String logDir=globalConfig.getLogsDirectory();
		File hashLog=new File(logDir+"\\logHash.txt");
		if(hashLog.exists()){
			BufferedReader r;
			try {
				r = new BufferedReader(new FileReader(hashLog));
				String line;
            	while((line = r.readLine()) != null) {
            		String linea=decode(line);
                	String[] palabras=linea.split(";");
                	hashes.put(palabras[0], palabras[1]);
            	}
            	r.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			hashes=new HashMap<String,String>();
		}
		return hashes;
	}
	public void saveHashes(){
		String logDir=globalConfig.getLogsDirectory();
		File hashLog=new File(logDir+"\\logHash.txt");
		if(!hashLog.exists()){
			hashLog.mkdirs();
			try {
				hashLog.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw = null;
		try {
			fw = new FileWriter(hashLog, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PrintWriter pw = new PrintWriter(fw);
		for(String s:hashes.keySet()){
		pw.println(encode(s+";"+hashes.get(s)));
		}

		pw.flush();

		pw.close();
		
	}
	public String encode(String s){
		try {
			return new String(Base64.encode(s.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public String decode(String s){
		try {
			return new String(Base64.decode(s));
		} catch (Base64DecodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public void loadConfig(String dir){
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		Properties propiedades= new Properties();
		
		try {
			File file=new File(dir);
			if(file.exists()){
			propiedades.load(new FileInputStream(dir));
			this.algoritmo=propiedades.getProperty("algoritmo");
			this.tiempo=propiedades.getProperty("tiempo");
			String directories=propiedades.getProperty("directories");
			String[] direc=directories.split(";");
			int length=1;
			for (String s:direc){
				List<String> lista=new ArrayList<String>();
				String files=propiedades.getProperty(""+length);
				for(String r:files.split(";")){
					lista.add(r);
				}
				map.put(s,lista);
				length++;
			}
			String urls=propiedades.getProperty("urls");
			List<String> lista=new ArrayList<String>();
			for (String s:urls.split(";")){
				lista.add(s);
			}
			map.put("urls", lista);
			}else{
				System.out.println("ERROR - Config file not found");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.properties=map;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Map<String, List<String>> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, List<String>> properties) {
		this.properties = properties;
	}

	public String getAlgoritmo() {
		return algoritmo;
	}

	public void setAlgoritmo(String algoritmo) {
		this.algoritmo = algoritmo;
	}

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
}
