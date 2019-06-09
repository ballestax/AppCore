package com.core.persistence.dao;

import com.core.domain.User;




/**
 * This is an interface for a proxy results object which is used by a Client to
 * access User objects on the Desktop.
 * 
 * @author Raymes Khoury
 * 
 */
public interface RemoteUserResultsInterface extends RemoteResultsInterface<User, UserRetrieveException> {
	
}
