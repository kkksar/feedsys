/**  
 * Project Name:feedsys  
 * File Name:FeedContentDao.java  
 * Package Name:com.ar.wb.feedsys.dao  
 * Date:2016年6月13日上午11:40:32  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.dao;

import com.ar.wb.feedsys.model.FeedContent;

/**  
 * <pre>  
 * ClassName:FeedContentDao 
 * 
 * feed content should not be updated!
 * <pre/>   
 * Date:     2016年6月13日 上午11:40:32 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public interface FeedContentDao {

    public boolean insertContent(FeedContent content);
    
    public FeedContent getContent(long id);
    
    public boolean deleteContent(long id);
    
}
