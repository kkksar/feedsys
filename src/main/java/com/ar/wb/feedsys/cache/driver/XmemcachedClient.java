/**  
 * Project Name:feedsys  
 * File Name:XmemcachedClient.java  
 * Package Name:com.ar.wb.feedsys.cache.driver  
 * Date:2016年6月13日下午3:19:08  
 * Copyright (c) 2016, @weibo All Rights Reserved.  
 *  
*/  
  
package com.ar.wb.feedsys.cache.driver;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.ar.wb.feedsys.cache.MemcacheClient;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;

/**  
 * <pre>  
 * ClassName:XmemcachedClient 
 * 
 * description here!
 * <pre/>   
 * Date:     2016年6月13日 下午3:19:08 <br/>  
 * @author   anrans
 * @version    
 * @since    JDK 1.8
 * @see        
 */
public class XmemcachedClient implements MemcacheClient{

    private final Logger log = Logger.getLogger(getClass());
    
    private String serverPort;
    
    private MemcachedClient client;
    
    public String getServerPort() {
        return serverPort;
    }

    @Override
    public void setServerPort(String serverPort){
        System.out.println("will create client connect to: " + serverPort);
        try {
            String binary = System.getProperty("binary");
            if (binary == null) {
                client = new XMemcachedClient(AddrUtil.getAddresses(serverPort));
            } else {
                MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(serverPort));
                builder.setCommandFactory(new BinaryCommandFactory());//use binary protocol 
                client = builder.build();
            }
            client.getTranscoder().setCompressionThreshold(512);
            client.setOpTimeout(5000l);
            log.info("INIT memcache client " + client.getClass().getCanonicalName());
        } catch (IOException e) {
            log.fatal("FAIL to init memcached client: " + e.getMessage());
        }
    }
    
    public boolean set(String key, Object value, int exptime) {
        try {
            return client.set(key, exptime, value);
        } catch (Exception e) {
            String err = e.getMessage();
            if (err != null && err.indexOf("stopped") == -1)
                log.warn("set " + e.getMessage());
        }
        return false;
    }
    
    @Override
    public Object get(String key) {
        try {
            return client.get(key);
        } catch (Exception e) {
            String err = e.getMessage();
            if (err != null && err.indexOf("stopped") == -1)
                log.warn("get " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public boolean delete(String key) {
        try {
            return client.delete(key);
        } catch (Exception e) {
            String err = e.getMessage();
            if (err != null && err.indexOf("stopped") == -1)
                log.warn("delete " + e.getMessage());
            return false;
        }
    }
}
