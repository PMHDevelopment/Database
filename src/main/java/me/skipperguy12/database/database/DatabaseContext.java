package me.skipperguy12.database.database;

import javax.annotation.Nonnull;

/**
 * Class that represents a Database
 * 
 * @author neiljohari
 * 
 */
public final class DatabaseContext {
    private String user = null, ip = null, port = null;
    char[] password = null;
    private String database = null;


    /**
     * Constructor for Database
     * 
     * @param user
     *            Username
     * @param password
     *            Password
     * @param ip
     *            Internet Protocal Address
     * @param port
     *            Port for ip
     * @param database
     *            Database name
     */

    public DatabaseContext(String user, char[] password, @Nonnull String ip, @Nonnull String port, @Nonnull String database) {
	this.user = user;
	this.password = password;
	this.ip = ip;
	this.port = port;
	this.database = database;
    }

    /**
     * Gets the Username
     * 
     * @return username
     */
    public String getUser() {
	return user;
    }

    /**
     * Gets the Password
     * 
     * @return password
     */
    public char[] getPassword() {
	return password;
    }

    /**
     * Gets the IP Address
     * 
     * @return ip address
     */
    public String getIP() {
	return ip;
    }

    /**
     * Gets the Port
     * 
     * @return port
     */
    public String getPort() {
	return port;
    }

    /**
     * Gets the Database
     * 
     * @return database
     */
    public String getDB() {
	return database;
    }
}
