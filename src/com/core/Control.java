/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  
 */
package com.core;


import com.core.domain.ConfigDB;
import com.core.domain.User;
import com.core.persistence.JDBC.JDBCConfigDAO;
import com.core.persistence.JDBC.JDBCDAOFactory;
import com.core.persistence.JDBC.JDBCUserDAO;
import com.core.persistence.JDBC.JDBCUtilDAO;
import com.core.persistence.dao.DAOException;
import com.core.persistence.dao.DAOFactory;
import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author ballestax
 */
public class Control {

    private Aplication app;
    public static final Logger logger = Logger.getLogger(Control.class.getCanonicalName());

    public Control(Aplication app) {
        this.app = app;
    }

    public void initDatabase() {
        try {

            if (Aplication.INSTALL_DB) {
                //preguntas la contraseña y crea la database
                new JDBCDAOFactory().createDatabase();
            } else {
                //crea la database from properties pass
                new JDBCDAOFactory().createDatabaseFromProperties();
            }

            JDBCUserDAO userDAO = (JDBCUserDAO) DAOFactory.getInstance().getUserDAO();
            userDAO.init();

            JDBCConfigDAO configDAO = (JDBCConfigDAO) DAOFactory.getInstance().getConfigDAO();
            configDAO.init();


            JDBCUtilDAO utilDAO = (JDBCUtilDAO) DAOFactory.getInstance().getUtilDAO();
            utilDAO.init();

        } catch (Exception e) {
            logger.error("Error initializing database", e);
            GUIManager.showErrorMessage(null, e, "Error inicializando la database");
        }

    }

    public User verifyUser(String user, char[] pass) {
        try {
            JDBCUserDAO userDAO = (JDBCUserDAO) DAOFactory.getInstance().getUserDAO();
            //userDAO.init();
            return userDAO.checkPassword(user, String.valueOf(pass));
        } catch (DAOException ex) {
            logger.error("Error checking user: " + user, ex);
        }
        return null;
    }

    public boolean tableUserEmpty() {
        try {
            JDBCUserDAO userDAO = (JDBCUserDAO) DAOFactory.getInstance().getUserDAO();
            //userDAO.init();
            System.out.println(userDAO.checkTableEmpty());
            return userDAO.checkTableEmpty() == 0;
        } catch (DAOException ex) {
            logger.error("Error checking table users is empty: ", ex);
        }
        return false;
    }


    public void addConfig(ConfigDB config) {
        try {
            JDBCConfigDAO configDAO = (JDBCConfigDAO) DAOFactory.getInstance().getConfigDAO();
            configDAO.addConfigDB(config);
        } catch (Exception e) {
            logger.error("Error adding config.", e);
        }
    }

    public ConfigDB getConfig(String clave) {
        try {
            JDBCConfigDAO configDAO = (JDBCConfigDAO) DAOFactory.getInstance().getConfigDAO();
            return configDAO.getConfigDB(clave);
        } catch (Exception e) {
            logger.error("Error getting config.", e);
            return null;
        }
    }

    public void updateConfig(ConfigDB config) {
        try {
            JDBCConfigDAO configDAO = (JDBCConfigDAO) DAOFactory.getInstance().getConfigDAO();
            configDAO.updateConfigDB(config);
        } catch (Exception e) {
            logger.error("Error updating config.", e);
        }
    }

    public boolean existConfig(String code) {
        try {
            JDBCConfigDAO configDAO = (JDBCConfigDAO) DAOFactory.getInstance().getConfigDAO();
            return configDAO.existConfig(code) >= 1;
        } catch (Exception e) {
            logger.error("Error checking config.", e);
            return false;
        }
    }

    public HashMap getNames(long identification) {
        HashMap datos = new HashMap();
        try {
            String url = app.getRunDir() + File.separator + "config" + File.separator + "seman.db";
            String sql = "SELECT lastname1, lastname2, firstname1, firstname2 FROM names1 "
                    + "where identification=" + identification;

            ResultSet result = app.getSqlManager().consultarTabla(sql, url);
            while (result.next()) {
                datos.put(MyConstants.FIELD_LASTNAME1, result.getString(1));
                datos.put(MyConstants.FIELD_LASTNAME2, result.getString(2));
                datos.put(MyConstants.FIELD_FIRSTNAME1, result.getString(3));
                datos.put(MyConstants.FIELD_FIRSTNAME2, result.getString(4));
            }
            result.getStatement().getConnection().close();
        } catch (Exception ex) {
            logger.error("Error retrieve local names. " + ex.getMessage(), ex);
        }
        return datos;
    }


    public int existClave(String tabla, String columna, String clave) {
        try {
            JDBCUtilDAO utilDAO = (JDBCUtilDAO) JDBCDAOFactory.getInstance().getUtilDAO();
            return utilDAO.existClave(tabla, columna, clave);
        } catch (Exception e) {
            return -1;
        }
    }

    public int getMaxIDTabla(String tabla) {
        try {
            JDBCUtilDAO utilDAO = (JDBCUtilDAO) DAOFactory.getInstance().getUtilDAO();
            return utilDAO.getMaxID(tabla);
        } catch (Exception e) {
            logger.error("Error getting maxID for: " + tabla, e);
            return 0;
        }
    }
    
    
}
