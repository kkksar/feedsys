/**  
 * Project Name:feedsys  
 * File Name:FeedContentServlet.java  
 * Package Name:com.ar.wb.feedsys.servlet  
 * Date:2016年6月13日下午2:06:19  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ar.wb.feedsys.model.FeedContent;
import com.ar.wb.feedsys.service.FeedContentService;

/**  
 * <pre>  
 * ClassName:FeedContentServlet 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 下午2:06:19 <br/>  
 * @author   fishermen  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class FeedContentServlet extends HttpServlet{
    	
    private static final long serialVersionUID = 6509029984757202640L;

    private ApplicationContext ctx = null;
            
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FeedContentService fcontentService = null;
        
        PrintWriter writer = resp.getWriter();
        
        try {
            ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            fcontentService = (FeedContentService)ctx.getBean("contentService");
        } catch (BeansException e) {  
            e.printStackTrace();  
            writer.println("init error!");
        }

        String idStr = req.getParameter("id");
        if(idStr != null) {
            long id = Long.parseLong(idStr);
            FeedContent fc = fcontentService.getContent(id);
            if(fc != null) {
                writer.println(fc.toJson());
            }else {
                writer.println("not found content for id: " + id);
            }
            return;
        }
        
        writer.println("Need parameter with name of id");
    }
}
