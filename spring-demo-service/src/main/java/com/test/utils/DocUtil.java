package com.test.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;  
  
public class DocUtil {  
      
    public static void main(String[] args) {  
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();  
        for (int i = 0; i < 10; i++) {  
            Map<String,Object> map = new HashMap<String,Object>();  
            map.put("rowid", "内容"+i);  
            map.put("rowname", "内容"+i);  
            map.put("rowphone", "内容"+i);  
            list.add(map);  
        }  
        createWord(list);  
    }  
      
    public static File createWord(List<Map<String,Object>> list){ 
    	Configuration configuration = null; 
    	configuration = new Configuration();  
        configuration.setDefaultEncoding("UTF-8");
        Map<String,Object> dataMap=new HashMap<String,Object>(); 
        dataMap.put("outperson", "XTADMIN");  
        Format format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dataMap.put("outtime", format.format(new Date()));  
        dataMap.put("list", list);
        configuration.setClassForTemplateLoading(DocUtil.class, "/");  //FTL文件所存在的位置  
        Template t=null;  
        try {  
            t = configuration.getTemplate("wordModel.ftl"); //文件名  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        File outFile = new File("xtadmin"+Math.random()*10000+".doc");  
        try {  
            Writer w = new OutputStreamWriter(new FileOutputStream(outFile),"utf-8");
            t.process(dataMap,w);
            w.close();
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return outFile;
    }  
  
}  
