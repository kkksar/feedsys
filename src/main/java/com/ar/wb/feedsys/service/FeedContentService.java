/**  
 * Project Name:feedsys  
 * File Name:FeedContentService.java  
 * Package Name:com.ar.wb.feedsys.service  
 * Date:2016年6月13日上午11:58:12  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.service;

import com.ar.wb.feedsys.model.FeedContent;

/**  
 * <pre>  
 * ClassName:FeedContentService 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 上午11:58:12 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public interface FeedContentService {

    
    public boolean addContent(FeedContent content);
    
    public boolean deleteContent(long id);
    
    public FeedContent getContent(long id);
}
