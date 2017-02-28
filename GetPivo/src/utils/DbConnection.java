package utils;

/**
 * Created by Johnny on 2/27/2017.
 */

import java.sql.*;

/**
 * Created by johnny on 1/28/17.
 */
public class DbConnection {

    private Connection dbh;
    private static DbConnection dbConnection;

    private DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/getPivo";
            String username = "root";
            String password = "";

            this.dbh = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbConnection getDbConnection() {
        if (dbConnection == null){
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public void setAutoCommit(boolean on_off) throws SQLException {
        this.dbh.setAutoCommit(on_off);
    }

    public void commitTransaction() throws SQLException {
        this.dbh.commit();
    }

    public int iudQeury(String sql) throws SQLException {
        Statement statement = this.dbh.createStatement();
        return statement.executeUpdate(sql);
    }

    public ResultSet selectQuery(String sql) throws SQLException {
        Statement statement = this.dbh.createStatement();
        return statement.executeQuery(sql);
    }

    public String vardump(ResultSet resultSet) {
        String ret = "";
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnsNumber = resultSetMetaData.getColumnCount();
            while (resultSet.next()){
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    ret += columnValue + " " + resultSetMetaData.getColumnName(i);
                }
                ret += "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
