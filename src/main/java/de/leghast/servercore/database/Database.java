package de.leghast.servercore.database;

import com.google.common.graph.Network;
import de.leghast.servercore.ServerCore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
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

}
