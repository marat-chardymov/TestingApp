package com.testapp.controllers.listeners;

import com.testapp.exceptions.AppConnectionException;
import com.testapp.model.util.MyConnectionPool;
import com.testapp.model.util.PropertiesLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.Properties;

public class PoolInitServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyConnectionPool pool = MyConnectionPool.getInstance();
        Properties props = null;
        try {
            props = PropertiesLoader.readProperties("datasource.properties");
            pool.initPool(props);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AppConnectionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
