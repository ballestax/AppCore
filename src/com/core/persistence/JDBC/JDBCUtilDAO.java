/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.persistence.JDBC;

import com.core.DBManager;
import com.core.domain.Person;
import com.core.persistence.SQLExtractor;
import com.core.persistence.SQLLoader;
import com.core.persistence.dao.DAOException;
import com.core.persistence.dao.UtilDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author ballestax
 */
public class JDBCUtilDAO implements UtilDAO {

    private DataSource dataSource;
    private SQLLoader sqlStatements;

    public static final String NAMED_PARAM_WHERE = "{where}";
    public static final String NAMED_PARAM_ORDER_BY = "{orderby}";
    public static final String DELETE_PERSON_WHERE_KEY = "DELETE_PERSON_WHERE";
    public static final String COUNT_TABLE_KEY = "CHECK_TABLE";
    public static final String TABLE_NAME = "types_vehicle";
    public static final String CREATE_TYPE_VEHICLES_TABLE_KEY = "CREATE_TYPE_VEHICLES_TABLE";
    public static final String CREATE_CANDIDATES_TABLE_KEY = "CREATE_CANDIDATES_TABLE";
    public static final String CREATE_NOVELTYS_TABLE_KEY = "CREATE_NOVELTYS_TABLE";
    public static final String CREATE_UPDATES_TABLE_KEY = "CREATE_UPDATES_TABLE";
    public static final String CREATE_RESPONSIBLES_TABLE_KEY = "CREATE_RESPONSIBLES_TABLE";
    public static final String GET_NOVELTY_KEY = "GET_NOVELTY";
    public static final String ADD_NOVELTY_KEY = "ADD_NOVELTY";
    public static final String DELETE_NOVELTY_KEY = "DELETE_NOVELTY";
    public static final String ADD_TYPE_VEHICLE = "ADD_TYPE_VEHICLE";
    public static final String ADD_CANDIDATE = "ADD_CANDIDATE";
    public static final String GET_TYPE_VEHICLE = "GET_TYPE_VEHICLE";
    public static final String DELETE_TYPE_VEHICLE = "DELETE_TYPE_VEHICLE";
    public static final String UPDATE_TYPE_VEHICLE = "UPDATE_TYPE_VEHICLE";
    public static final String GET_CANDIDATE_KEY = "GET_CANDIDATE";
    public static final String GET_COMMUNES_LIST_KEY = "GET_COMMUNES_LIST";
    public static final String GET_RANCHERY_LIST_KEY = "GET_RANCHERY_LIST";
    public static final String GET_UPDATE_KEY = "GET_UPDATE";
    public static final String ADD_UPDATE_KEY = "ADD_UPDATE";
    public static final String ADD_RESPONSIBLE_KEY = "ADD_RESPONSIBLE";
    public static final String GET_RESPONSIBLES_LIST_KEY = "GET_RESPONSIBLES_LIST";
    public static final String GET_COMUNITY_LIST_KEY = "GET_COMUNITY_LIST";
    
    private static final Logger logger = Logger.getLogger(JDBCUtilDAO.class.getCanonicalName());

    public static final String NAMED_PARAM_KEY = "{key}";
    public static final String GET_MAX_ID_KEY = "GET_MAX_ID";    
    public static final String EXIST_CLAVE_KEY = "EXIST_CLAVE";

    public JDBCUtilDAO(DataSource dataSource, SQLLoader sqlStatements) {
        this.dataSource = dataSource;
        this.sqlStatements = sqlStatements;
    }

    public void init() throws DAOException {


        String TABLE_NAME = "updates";
        createTable(TABLE_NAME, CREATE_UPDATES_TABLE_KEY);

    }

    private void createTable(String tableName, String JDBC_KEY) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            if (DBManager.tableExists(tableName, conn)) {
                return;
            }
            ps = sqlStatements.buildSQLStatement(conn, JDBC_KEY);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException | IOException e) {
            DBManager.rollbackConn(conn);
            throw new DAOException("Cannot create " + tableName + " table", e);
        } finally {
            DBManager.closeStatement(ps);
            DBManager.closeConnection(conn);
        }
    }
    
    
    public int countTableRows(String sql) throws DAOException {
        Connection conn = null;
        int count = 0;
        try {
            conn = dataSource.getConnection();
//            System.err.println(sql);
            count = DBManager.countTable(sql, conn, sqlStatements);
        } catch (SQLException e) {
            DBManager.rollbackConn(conn);
            throw new DAOException("Could not properly retrieve the current TaskID", e);
        } finally {
            DBManager.closeConnection(conn);
        }
        return count;
    }

    
    public ArrayList<Update> getUpdateWhere(String where, String order) throws DAOException {
        String retrieveList;
        try {
            SQLExtractor sqlExtractorWhere = new SQLExtractor(where, SQLExtractor.Type.WHERE);;
            Map<String, String> namedParams = new HashMap<String, String>();
            namedParams.put(NAMED_PARAM_WHERE, sqlExtractorWhere.extractWhere());
            SQLExtractor sqlExtractorOrder = new SQLExtractor(order, SQLExtractor.Type.ORDER_BY);;
            namedParams.put(NAMED_PARAM_ORDER_BY, sqlExtractorOrder.extractOrderBy());
            retrieveList = sqlStatements.getSQLString(GET_UPDATE_KEY, namedParams);

        } catch (SQLException e) {
            throw new DAOException("Could not properly retrieve the update", e);
        } catch (IOException e) {
            throw new DAOException("Could not properly retrieve the update", e);
        }
        Connection conn = null;
        PreparedStatement retrieve = null;
        ResultSet rs = null;
        Update update = null;
        ArrayList<Update> list = new ArrayList<>();
        try {
            conn = dataSource.getConnection();
            retrieve = conn.prepareStatement(retrieveList);
            rs = retrieve.executeQuery();
            while (rs.next()) {
                update = new Update();
                update.setId(rs.getInt(1));
                update.setRegisters(rs.getInt(2));
                update.setUpdates(rs.getInt(3));
                update.setStatus(rs.getString(4));
                update.setLastTimeUpdate(rs.getTimestamp(5));
                list.add(update);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not properly retrieve the update: " + e);
        } finally {
            DBManager.closeResultSet(rs);
            DBManager.closeStatement(retrieve);
            DBManager.closeConnection(conn);
        }
        return list;
    }

    public void addUpdate(Update update) throws DAOException {
        if (update == null) {
            throw new IllegalArgumentException("Null update");
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            Object[] parameters = {
                update.getLider(),
                update.getStatus(),
                update.getRegisters(),
                update.getUpdates()
            };
            ps = sqlStatements.buildSQLStatement(conn, ADD_UPDATE_KEY, parameters);
            ps.executeUpdate();
            conn.commit();
            logger.log(Level.INFO, "Added update:" + update.getId());
        } catch (SQLException | IOException e) {
            DBManager.rollbackConn(conn);
            throw new DAOException("Cannot add update", e);
        } finally {
            DBManager.closeStatement(ps);
            DBManager.closeConnection(conn);
        }
    }

    
    public int existClave(String table, String column, String code) throws DAOException {
        String retrieve;
        try {
            Map<String, String> namedParams = new HashMap<String, String>();
            namedParams.put(JDBCDAOFactory.NAMED_PARAM_TABLE, table);
            namedParams.put(JDBCDAOFactory.NAMED_PARAM_QUERY, column);
            namedParams.put(NAMED_PARAM_KEY, code);
            retrieve = sqlStatements.getSQLString(EXIST_CLAVE_KEY, namedParams);
        } catch (IOException e) {
            throw new DAOException("Could not properly retrieve the outdated count", e);
        }
//        System.err.println("retrieve = " + retrieve);
        Connection conn = null;
        PreparedStatement pSt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = dataSource.getConnection();
            pSt = conn.prepareStatement(retrieve);
//            Object[] parameters = {code};
//            pSt = sqlStatements.buildSQLStatement(conn, EXIST_CLAVE_KEY, parameters);
            rs = pSt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Could not properly retrieve the outdated count: " + e);
        } finally {
            DBManager.closeResultSet(rs);
            DBManager.closeStatement(pSt);
            DBManager.closeConnection(conn);
        }
        return count;
    }
    
    
    public int getMaxID(String table) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query;
        int maxID = 0;
        try {
            Map<String, String> namedParams = new HashMap<>();
            namedParams.put(JDBCDAOFactory.NAMED_PARAM_TABLE, table);
            query = sqlStatements.getSQLString(GET_MAX_ID_KEY, namedParams);
            conn = dataSource.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                maxID = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get max id for: " + table, e);
        } catch (IOException e) {
            throw new DAOException("Cannot get max id for: " + table, e);
        } finally {
            DBManager.closeStatement(ps);
            DBManager.closeConnection(conn);
        }
        return maxID;
    }
    
    
    
}
