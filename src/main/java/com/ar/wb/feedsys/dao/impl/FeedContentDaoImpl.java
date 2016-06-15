/**  
 * Project Name:feedsys  
 * File Name:FeedContentDaoImpl.java  
 * Package Name:com.ar.wb.feedsys.dao.impl  
 * Date:2016年6月13日上午11:43:18  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.ar.wb.feedsys.dao.FeedContentDao;
import com.ar.wb.feedsys.model.FeedContent;

/**  
 * <pre>  
 * ClassName:FeedContentDaoImpl 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 上午11:43:18 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class FeedContentDaoImpl implements FeedContentDao{

    private static final String INSERT_CONTENT = "insert into weibo.f_content (id, content) values (?, ?)";
    
    private static final String SELECT_CONTENT = "select id, content from weibo.f_content where id=?";
    
    private static final String DELETE_CONTENT = "delete from weibo.f_content where id=?";
    
    private JdbcTemplate contentJt; 
    
    @Override
    public boolean insertContent(FeedContent content) {
        return contentJt.update(INSERT_CONTENT, content.getId(), content.getContent()) > 0;
    }
    
    public FeedContent getContent(long id) {
        final FeedContent fc = new FeedContent();
        contentJt.query(SELECT_CONTENT, new Object[]{id}, new RowCallbackHandler(){
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                fc.setId(rs.getLong("id"));
                fc.setContent(rs.getString("content"));
            }
        });
        if (fc.getId() > 0) {
            return fc;
        }
        return null;
    }
    
    public boolean deleteContent(long id) {
        return contentJt.update(DELETE_CONTENT, id) > 0;
    }

    public JdbcTemplate getContentJt() {
        return contentJt;
    }

    public void setContentJt(JdbcTemplate contentJt) {
        this.contentJt = contentJt;
    }
}
