package com.psi1.testing;

import java.util.List;
import java.util.Map;

import com.psi1.config.Configuration;
import com.psi1.utils.FileUtils;

public class Testing {

	public static void main(String[] args) {
		GlobalConfiguration globalConfig=new Configuration("C:\\Users\\ADRIAN\\Desktop\\config.properties","C:\\Users\\ADRIAN\\Desktop\\logsHashingSecurity");
		Configuration config=new Configuration(globalConfig);
		Map<String,List<String>> map=config.getProperties();
		System.out.println(map.keySet());
		System.out.println(map.values());
		for(String s:map.keySet()){
			if(s.contains("\\")){
				List<String> lista=map.get(s);
				for(String r:lista){
				String str=FileUtils.readFile(s+"\\"+r);
				str=FileUtils.hashContent(str,config);
				System.out.println(s+"\\"+r+" --- Contenido ---> "+str);
				}
			}	
		}
		
	}

}
