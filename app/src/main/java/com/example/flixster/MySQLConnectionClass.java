package com.example.flixster;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class MySQLConnectionClass {

    private Connection connection;
    protected static String db = "flixster_schema";
    protected static String ip = "10.0.2.2";
    protected static String port = "3306";
    protected static String username = "root";
    protected static String password = "";

    public Connection conn() {
        connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + ip + ":" + port + "/" + db;
            connection = DriverManager.getConnection(connectionString, username, password);

        } catch (Exception e){
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return connection;
    }

}
