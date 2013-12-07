package me.skipperguy12.database.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import me.skipperguy12.database.database.ConnectionManager;
import me.skipperguy12.database.database.DatabaseContext;
import me.skipperguy12.database.utils.config.ConfigUtils;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.mongodb.MongoClient;

public class Database extends JavaPlugin {
    private static Database instance;
    private static ConnectionManager connectionManager;

    // Since 2.10.0, uses MongoClient
    private MongoClient mongo = null;

    public void onEnable() {
	instance = this;
	File configuration = new File(this.getDataFolder(), "config.yml");
	if (!configuration.exists()) {
	    createConfig(configuration);
	}

	connectionManager = new ConnectionManager();
	DatabaseContext dbContext = ConfigUtils.configToDBContext(getConfig());

	connectionManager.setDatabaseContext(dbContext);
	connectionManager.connect();

    }

    public void createConfig(File f) {
	InputStream cfgStream = this.getResource("config.yml");
	if (!this.getDataFolder().exists()) {
	    this.getDataFolder().mkdirs();
	}
	try {
	    FileOutputStream fos = new FileOutputStream(f);
	    ReadableByteChannel rbc = Channels.newChannel(cfgStream);
	    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
	    fos.close();

	} catch (Exception e) {
	    Bukkit.getLogger().info("There was an error in creating the config. Using bukkit methods to do so.");
	    this.getConfig().options().copyDefaults(true);
	    this.saveConfig();
	}

    }

    /**
     * Gets the connection manager, static API entry point method
     * 
     * @return ConnectionManager connection manager
     */
    public static ConnectionManager getConnectionManager() {
	if (instance == null)
	    return null;
	Validate.notNull(connectionManager, "ConnectionManager");
	Validate.notNull(connectionManager.getDatabaseContext(), "DatabaseContextr");
	return connectionManager;
    }

    public void onDisable() {
	instance = null;
    }

    public static Database getInstance() {
	return instance;
    }
}
