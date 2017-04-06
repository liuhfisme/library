package com.mine.library.demo.core.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;



public class ApplicationPath {
	public static String getApplicationPath(@SuppressWarnings("rawtypes") Class cls){
		String path=null;
		URL url=getClassLocationURL(cls);
		if (url != null) {
		      path = url.getPath();
		      if ("jar".equalsIgnoreCase(url.getProtocol())) {
		        try {
		          path = new URL(path).getPath();
		        } catch (MalformedURLException e) {
		          e.printStackTrace();
		        }
		        int location = path.indexOf("!/");
		        if (location != -1) {
		          path = path.substring(0, location);
		        }
		      }
		      File file = new File(path);
		      try {
		        path = file.getCanonicalPath();
		      } catch (IOException e) {
		        e.printStackTrace();
		      }
		    }

		    return path;
		
	}
	private static URL getClassLocationURL(@SuppressWarnings("rawtypes") Class cls){
		if(cls==null){
			throw new IllegalArgumentException("Null Input:class");
		}
		URL result=null;
		String clsAsResource=cls.getName().replace('.', '/').concat(".class");
		ProtectionDomain pd=cls.getProtectionDomain();
		if(pd!=null){
			CodeSource cs=pd.getCodeSource();
			if(cs!=null){
				result=cs.getLocation();
			}
			if((result!=null)&&("file".equalsIgnoreCase(result.getProtocol()))){
				try{
					if((result.toExternalForm().endsWith("*.jar"))||(result.toExternalForm().endsWith(".zip"))){
						result=new URL("jar:".concat(result.toExternalForm()).concat("!/").concat(clsAsResource));
					}else if(new File(result.getFile()).isDirectory())
						result = new URL(result, clsAsResource);
				}catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		if(result==null){
			ClassLoader clsLoader=cls.getClassLoader();
			result=clsLoader!=null?clsLoader.getResource(clsAsResource):ClassLoader.getSystemResource(clsAsResource);
		}
		return result;
	}
	public static String getAppliactionPath() {
	    return getRootPath() + "WEB-INF" + File.separator + "classes";
	  }
	  public static String getRootPath() {
	    String path = getApplicationPath(ApplicationPath.class);
	    int index = path.indexOf("WEB-INF");
	    path = path.substring(0, index);
	    return path;
	  }

	  public static String getProjectRootPath() {
	    File rootPath = new File(getRootPath());
	    return rootPath.getParentFile().getAbsolutePath();
	  }
}
