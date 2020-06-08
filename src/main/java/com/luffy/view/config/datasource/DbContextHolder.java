package com.luffy.view.config.datasource;

public class DbContextHolder {
	  private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
	 
	  public static ThreadLocal<String> getLocal() {  
	      return contextHolder;  
	  }  
	  
      /** 
       * 读可能是多个库 
       */  
      public static void read() {  
    	 contextHolder.set(DataSourceType.read.getType());  
      }  
	  
	   /** 
	    * 写只有一个库 
	    */  
	  public static void write() {  
	     contextHolder.set(DataSourceType.write.getType());  
	  }

	  public static String getJdbcType() {
	      return contextHolder.get();  
	  }  
	  
	  public static void clearDbType(){
	      contextHolder.remove();
	  }


}
