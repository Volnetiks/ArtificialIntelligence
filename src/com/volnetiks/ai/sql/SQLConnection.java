package com.volnetiks.ai.sql;

import com.volnetiks.ai.graphics.InterfaceRender;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

/* Date: 16/01/2019 For Artificial Intelligence By Volnetiks */
public class SQLConnection {

    public String urlbase, host, database, user, pass;
    private Connection connection;

    public SQLConnection(String urlbase, String host, String database, String user, String pass) {
        this.urlbase = urlbase;
        this.host = host;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }

    public void connection() {
        if(!isConnected()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(urlbase + host + "/" + database, user, pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnect() {
        if(isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected() {
        return connection != null;
    }

    public String getAllUsername() {
        System.out.println("called");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = preparedStatement.executeQuery();
            String user = "";
            int v = 1;
            while(rs.next()) {
                user += v + " - Username: " + rs.getString("username") + "\n";
                v++;
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No Username Found";
    }

    public void accesPermissionIp() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println("IP Address:- " + inetAddress.getHostAddress());
            System.out.println("Host Name:- " + inetAddress.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
