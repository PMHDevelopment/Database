package me.skipperguy12.database.utils.config;

import me.skipperguy12.database.database.DatabaseContext;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtils {
    /**
     * Creates a DatabaseContext from a configuration file
     * 
     * @param config
     * @return
     */
    public static DatabaseContext configToDBContext(FileConfiguration config) {
	String user = config.getString("database.user");
	String ip = config.getString("database.ip");
	String port = config.getString("database.port");
	String password = config.getString("database.password");
	String database = config.getString("database.database");
	return new DatabaseContext(user, password.toCharArray(), ip, port, database);
    }
}
