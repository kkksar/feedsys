/**  
 * Project Name:feedsys  
 * File Name:MemcacheClient.java  
 * Package Name:com.ar.wb.feedsys.cache  
 * Date:2016年6月13日下午3:14:50  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.cache;


/**  
 * <pre>  
 * ClassName:MemcacheClient 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 下午3:14:50 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8
 * @see        
 */
public interface MemcacheClient {

    public void setServerPort(String serverPort);
    
    public boolean set(String key, Object value, int exptime);
    
    public Object get(String key);
    
    public boolean delete(String key);
}
