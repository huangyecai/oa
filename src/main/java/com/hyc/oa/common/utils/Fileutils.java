package com.hyc.oa.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Fileutils {
	
	public static void write(String filePath,String txt){
		File file=new File(filePath);
		if (!file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		BufferedWriter out =null;
		try {
			FileWriter fw = new FileWriter(file);
		    out = new BufferedWriter(fw);		  
		    out.write(txt);		    
		    out.flush();
		    fw.close();
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (out!=null)
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
	}
}
