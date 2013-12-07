package me.skipperguy12.database.database;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public final class ConnectionManager {

    private MongoClient mongo;

    private DatabaseContext database;

    private DB db;

    /**
     * Gets the MongoClient
     * 
     * @return MongoClient
     */
    public MongoClient getClient() {
	return mongo;
    }

    /**
     * Gets the Database Context
     * 
     * @return The database
     */
    public DatabaseContext getDatabaseContext() {
	return database;
    }

    /**
     * Sets the DatabaseContext
     * 
     * @param newContext
     *            The new context
     */
    public void setDatabaseContext(DatabaseContext newContext) {
	database = newContext;
    }

    /**
     * Connects to the Database
     */
    public void connect() {
	try {
	    mongo = new MongoClient(getDatabaseContext().getIP(), Integer.valueOf(getDatabaseContext().getPort()));
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	db = getClient().getDB(getDatabaseContext().getDB());
	boolean auth = db.authenticate(getDatabaseContext().getUser(), getDatabaseContext().getPassword());
    }

}
