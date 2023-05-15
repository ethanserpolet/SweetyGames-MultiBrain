package fun.slowfeew.multibrain.Utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    enum Manager {
        Osaka2(new DatabaseCredentials("localhost", "root", "Taylor@13", "Sweety", 3306));
        private DatabaseAccess databaseAccess;

        public static Connection getOsaka2Connection() throws SQLException {
            return Manager.Osaka2.getDatabaseAccess().getConnection();
        }
        Manager(DatabaseCredentials credentials) {
            this.databaseAccess = new DatabaseAccess(credentials);
        }

        public DatabaseAccess getDatabaseAccess() {
            return databaseAccess;
        }
    }

    public static Connection getOsaka2Connection() throws SQLException {
        return Manager.Osaka2.getDatabaseAccess().getConnection();
    }
    public static void initAllDatabaseConnections() {
        for(Manager manager : Manager.values()) {
            manager.getDatabaseAccess().initPool();
        }
    }

    public static void closeAllDatabaseConnections() {
        for(Manager manager : Manager.values()){
            manager.getDatabaseAccess().closePool();
        }
    }

    static class DatabaseCredentials {
        private String host;
        private String user;
        private String pass;
        private String dbName;
        private int port;

        public DatabaseCredentials(String host, String user, String pass, String dbName, int port) {
            this.host = host;
            this.user = user;
            this.pass = pass;
            this.dbName = dbName;
            this.port = port;
        }

        public String toURL() {
            return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        }

        public String getUser() {
            return user;
        }

        public String getPass() {
            return pass;
        }
    }

    static class DatabaseAccess {
        private DatabaseCredentials credentials;
        private HikariDataSource hikariDataSource;

        public DatabaseAccess(DatabaseCredentials credentials) {
            this.credentials = credentials;
        }

        private void setupHikariCP() {
            HikariConfig hikariConfig = new HikariConfig();

            hikariConfig.setMaximumPoolSize(15);
            hikariConfig.setJdbcUrl(credentials.toURL());
            hikariConfig.setUsername(credentials.getUser());
            hikariConfig.setPassword(credentials.getPass());
            hikariConfig.setMaxLifetime(600000L);
            hikariConfig.setIdleTimeout(300000L);
            hikariConfig.setLeakDetectionThreshold(300000L);
            hikariConfig.setConnectionTimeout(10000L);
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

            this.hikariDataSource = new HikariDataSource(hikariConfig);
        }

        public void initPool() {
            setupHikariCP();
        }

        public void closePool() {
            this.hikariDataSource.close();
        }

        public Connection getConnection() throws SQLException {
            if(this.hikariDataSource == null){
                System.out.println("Not connected");
                setupHikariCP();
            }

            return this.hikariDataSource.getConnection();
        }
    }
}
