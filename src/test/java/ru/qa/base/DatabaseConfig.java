package ru.qa.base;

public class DatabaseConfig extends BaseConfig {
    public static final String DB_URL = "host1271";
    protected static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static String getConnectionString() {
        return "jdbc:mysql://" + DB_URL + "/" + DB_USER + "/";
    }

    private static boolean validateCredentials(String user, String password) {
        if (user == null || user.isEmpty() || password == null || password.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    protected String getConfigType() {
        return "Database";
    }
}
