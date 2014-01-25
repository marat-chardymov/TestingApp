package com.testapp.controllers.listeners;

import com.testapp.exceptions.AppConnectionException;
import com.testapp.model.util.MyConnectionPool;
import com.testapp.model.util.PropertiesLoader;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

public class PoolInitServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyConnectionPool pool = MyConnectionPool.getInstance();
        Properties props = null;
        Logger log = Logger.getLogger(PoolInitServletContextListener.class);
        try {
            props = PropertiesLoader.readProperties("datasource.properties");
            pool.initPool(props);
        } catch (IOException e) {
            log.log(Level.FATAL, "can't read init properties for connection pool", e);
        } catch (AppConnectionException e) {
            log.log(Level.FATAL, "can't initialize connection pool", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
