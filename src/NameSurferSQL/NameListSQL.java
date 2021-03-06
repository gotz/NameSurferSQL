/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package NameSurferSQL;

import java.sql.*;

/**
 *
 * @author nathan
 */
public class NameListSQL {

    private Statement stmt;
    private Connection conn = null;
    private PreparedStatement query = null;
    private ResultSet rset = null;

    public NameListSQL() throws Exception {
        initializeDB();
  Class.forName("org.sqlite.JDBC");

    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:names.db");
        return conn;
    }

    private void initializeDB() throws Exception {
        conn = getConnection();
        stmt = conn.createStatement();

    }

    public int search(String name) throws Exception {
        initializeDB();

        query = conn.prepareStatement("SELECT id FROM names WHERE name LIKE ? LIMIT 1");
        query.setString(1, name);
        rset = query.executeQuery();
        int ret = rset.getInt(1);
        conn.close();

        return ret;
    }

    public String getName(int id) throws Exception {
        initializeDB();
        String qString = "SELECT name FROM names where id=?";
        query = conn.prepareStatement(qString);
        query.setInt(1, id);
        rset = query.executeQuery();
        String ret = rset.getString(1);
        conn.close();

        return ret;
    }

    public int getRank(int id, int decade) throws Exception {
        initializeDB();

        PreparedStatement sQ = conn.prepareStatement("SELECT * FROM names WHERE id = ?");
        sQ.setInt(1, id);
        rset = sQ.executeQuery();
        int ret = rset.getInt("f"+decade);
        conn.close();

        return ret;
    }

}
