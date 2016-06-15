/**  
 * Project Name:feedsys  
 * File Name:FeedContent.java  
 * Package Name:com.ar.wb.feedsys.model  
 * Date:2016年6月13日上午11:33:16  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.model;

import org.json.JSONException;
import org.json.JSONObject;

/**  
 * <pre>  
 * ClassName:FeedContent 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 上午11:33:16 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class FeedContent {

    private long id;
    private String content;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * Optimse this function later
     * toJson: translate to json formate. <br/>  
     *  
     * @author fishermen  
     * @return  
     * @throws JSONException 
     * @since JDK 1.8
     */
    public String toJson() {
        JSONObject tmp = new JSONObject();
        try {
            tmp.put("id", id);
            tmp.put("content", content);
            return tmp.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += id * 31;
        if(content != null){
            hash += content.hashCode() * 31;
        }
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null 
                || !(obj instanceof FeedContent)) {
            return false;
        }
        FeedContent cont = (FeedContent)obj;
        return this.id == cont.id 
                && this.content != null
                && this.content.equals(cont.content);
    }
    
    @Override
    public String toString() {
        JSONObject tmp = new JSONObject();
        JSONObject res = new JSONObject();
        try {
            tmp.put("id", id);
            tmp.put("content", content);
            res.put("FeedContent", tmp.toString());
            return res.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
