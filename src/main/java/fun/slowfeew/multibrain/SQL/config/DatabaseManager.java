package fun.slowfeew.multibrain.SQL.config;


public enum DatabaseManager {

    Osaka2(new DatabaseCredentials("localhost", "root", "Taylor@13", "Sweety", 3306));
    private DatabaseAccess databaseAccess;


    DatabaseManager(DatabaseCredentials credentials){
        this.databaseAccess = new DatabaseAccess(credentials);
    }

    public DatabaseAccess getDatabaseAccess() {
        return databaseAccess;
    }

    public static void initAllDatabaseConnections() {
        for(DatabaseManager databaseManager: values()){
            databaseManager.databaseAccess.initPool();
        }
    }

    public static void closeAllDatabaseConnections() {
        for(DatabaseManager databaseManager: values()){
            databaseManager.databaseAccess.closePool();
        }

    }
}
