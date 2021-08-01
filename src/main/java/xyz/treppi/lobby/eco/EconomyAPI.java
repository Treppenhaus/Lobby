package xyz.treppi.lobby.eco;

import xyz.treppi.lobby.LobbyPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EconomyAPI {
    private static final String host = "localhost";
    private static final String database = "economy";
    private static final String username = "root";
    private static final String password = "G4454-+32g";
    private static final String url = "jdbc:mysql://"+host+":3306/"+database;

    public static void createUser(String uuid) {
        if(!LobbyPlugin.ecoOn()) return;
        if(userExists(uuid)) return;
        try {

            int xp = 0;
            int coins = 0;

            String sql = "INSERT INTO "+database+" (uuid, coins, xp) VALUES (\""+uuid+"\", "+coins+", "+xp+")";
            Connection connection = createConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();

        }catch(Exception e) {e.printStackTrace();}
    }

    public static int getXP(String uuid) {
        return getValue(uuid, "xp");
    }

    public static int getCoins(String uuid) {
        return getValue(uuid, "coins");
    }

    public static void setCoins(String uuid, int amount) {
        if(!LobbyPlugin.ecoOn()) return;
        try {
            int xp = getXP(uuid);
            String sql = "UPDATE "+database+" SET coins = "+amount+" WHERE uuid = \""+uuid+"\";";
            Connection connection = createConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();

        }catch(Exception e) {e.printStackTrace();}
    }

    public static void setXP(String uuid, int amount) {
        if(!LobbyPlugin.ecoOn()) return;
        try {
            int coins = getXP(uuid);
            String sql = "UPDATE "+database+" SET xp = "+amount+" WHERE uuid = \""+uuid+"\";";
            Connection connection = createConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.close();

        }catch(Exception e) {e.printStackTrace();}
    }

    public static int getValue(String uuid, String column) {
        if(!LobbyPlugin.ecoOn()) return 0;
        int xp = -1;
        try {

            String sql = "SELECT * FROM "+database+";";
            Connection connection = createConnection();
            ResultSet result = executeQuery(sql, connection);

            while(result.next()) {
                String dbuuid = result.getString("uuid");
                if(dbuuid.equalsIgnoreCase(uuid)) xp = result.getInt(column);
            }
            connection.close();

        }catch(Exception e) { e.printStackTrace();}

        createUser(uuid);
        return xp;
    }

    public static boolean userExists(String uuid) {
        if(!LobbyPlugin.ecoOn()) return false;
        try {
            Connection connection = createConnection();
            String query = "SELECt * FROM "+database+" WHERE uuid = \""+uuid+"\";";
            ResultSet result = executeQuery(query, connection);

            while(result.next()) {
                connection.close();
                return true;
            }

        }catch(Exception e) {e.printStackTrace(); }
        return false;
    }

    public static ResultSet executeQuery(String query, Connection connection) {
        if(!LobbyPlugin.ecoOn()) return null;
        try {

            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();

        }catch(Exception e) {  e.printStackTrace();  }
        return null;
    }

    public static Connection createConnection() {
        if(!LobbyPlugin.ecoOn()) return null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);

        }catch(Exception e) { e.printStackTrace(); }
        return null;
    }
}
