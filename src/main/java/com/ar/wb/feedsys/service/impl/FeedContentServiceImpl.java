/**  
 * Project Name:feedsys  
 * File Name:FeedContentServiceImpl.java  
 * Package Name:com.ar.wb.feedsys.service.impl  
 * Date:2016年6月13日下午1:50:59  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.service.impl;

import com.ar.wb.feedsys.cache.MemcacheClient;
import com.ar.wb.feedsys.constant.FeedConstants;
import com.ar.wb.feedsys.dao.FeedContentDao;
import com.ar.wb.feedsys.model.FeedContent;
import com.ar.wb.feedsys.service.FeedContentService;

import net.rubyeye.xmemcached.MemcachedClient;

/**  
 * <pre>  
 * ClassName:FeedContentServiceImpl 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 下午1:50:59 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class FeedContentServiceImpl implements FeedContentService{

    private MemcacheClient memcache;

    private FeedContentDao fcontentDao;

    @Override
    public boolean addContent(FeedContent content) {
        boolean rs = fcontentDao.insertContent(content);
        if (rs) {
            memcache.set(getContentCacheKey(content.getId()), content.getContent(), FeedConstants.EXPIRE_CONTENT);
        }
        return rs;
    }

    @Override
    public boolean deleteContent(long id) {
        memcache.delete(getContentCacheKey(id));
        return fcontentDao.deleteContent(id);
    }

    @Override
    public FeedContent getContent(long id) {
        String content = (String)memcache.get(getContentCacheKey(id));
        if(content != null) {
            FeedContent fc = new FeedContent();
            fc.setId(id);
            fc.setContent(content);
            return fc;
        }
        
        FeedContent fc = fcontentDao.getContent(id);
        if(fc != null) {
            //cache the missing content
            memcache.set(getContentCacheKey(id), fc.getContent(), FeedConstants.EXPIRE_CONTENT);
        }
        return fc;
    }

    public FeedContentDao getFcontentDao() {
        return fcontentDao;
    }

    public void setFcontentDao(FeedContentDao fcontentDao) {
        this.fcontentDao = fcontentDao;
    }
    
    private String getContentCacheKey(long contentid) {
        return new StringBuilder(16)
                .append(contentid)
                .append(FeedConstants.SUFFIX_CONTENT)
                .toString();
    }

    public MemcacheClient getMemcache() {
        return memcache;
    }

    public void setMemcache(MemcacheClient memcache) {
        this.memcache = memcache;
    }
}
