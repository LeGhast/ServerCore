package de.leghast.servercore.database;

import de.leghast.servercore.ServerCore;

import java.sql.*;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class Database {

    private ServerCore main;
    private DatabaseConfigManager databaseConfig;

    private Connection connection;

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public Database(ServerCore main, DatabaseConfigManager databaseConfig){
        this.main = main;
        this.databaseConfig = databaseConfig;
        URL = databaseConfig.getUrl();
        USERNAME = databaseConfig.getUsername();
        PASSWORD = databaseConfig.getPassword();
    }

    public void connect(){
        if(isConnected()){
            disconnect();
        }
        try {
            connection = DriverManager.getConnection(
                    URL,
                    USERNAME,
                    PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isConnected(){
        return connection != null;
    }

    public Connection getConnection(){
        return connection;
    }

    public void disconnect(){
        if(isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ResultSet executeQuery(PreparedStatement statement){
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            if(e instanceof CommunicationsException){
                connect();
                return executeQuery(statement);
            }
            throw new RuntimeException(e);
        }
    }

}
