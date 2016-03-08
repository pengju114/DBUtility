/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pj.db.util;

import java.lang.reflect.Method;
import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.util.Properties;

/**
 *
 * @author pengju 2011-7-11 21:41
 */
public class ConnectionFactory {

    public static ConnectionManager getConnMgr() {
        Properties prop = new Properties();
        try {
            prop.load(ConnectionFactory.class.getResourceAsStream("dbutility.properties"));
            //System.out.println(prop);
            if(prop.containsKey("database.cfg.userName")){
                return getConnMgr(
                        prop.getProperty("database.cfg.serverName", "localhost"),
                        prop.getProperty("database.cfg.databaseName"),
                        prop.getProperty("database.cfg.portNumber", "1433"),
                        prop.getProperty("database.cfg.userName","sa"),
                        prop.getProperty("database.cfg.password")
                        );
            }else if(prop.containsKey("database.cfg.class")){
                return getConnMgr(prop.getProperty("database.cfg.class"),prop.getProperty("database.cfg.method"));
            }else if(prop.containsKey("database.cfg.dataSource")){
                return getConnMgr(prop.getProperty("database.cfg.dataSource"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConnectionManager getConnMgr(String className, String method) {
        return new ClassConn(className, method);
    }

    public static ConnectionManager getConnMgr(String dataSource) {
        return new DsConn(dataSource);
    }

    public static ConnectionManager getConnMgr(String serverName, String databaseName, String portNumber, String userName, String password) {
        return new SmpConn(serverName, databaseName, portNumber, userName, password);
    }
}

class ClassConn implements ConnectionManager {

    private Method method;

    public ClassConn(String className, String md) {
        try {
            Class c = Class.forName(className);
            if (c != null) {
                method = c.getMethod(md, new Class[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        if (method != null) {
            try {
                return (Connection) method.invoke(null, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

class DsConn implements ConnectionManager {

    private DataSource ds;

    public DsConn(String dataSource) {
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        if (ds != null) {
            try {
                return ds.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}

class SmpConn implements ConnectionManager {

    private DataSource ds;

    public SmpConn(String serverName, String databaseName, String portNumber, String userName, String password) {
        SQLServerDataSource d = new SQLServerDataSource();
        d.setServerName(serverName);
        d.setPortNumber(Integer.valueOf(portNumber));
        d.setDatabaseName(databaseName);
        d.setUser(userName);
        d.setPassword(password);
        ds = d;
    }

    public Connection getConnection() {
        if (ds != null) {
            try {
                return ds.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}