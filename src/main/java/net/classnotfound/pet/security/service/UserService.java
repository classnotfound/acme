
package net.classnotfound.pet.security.service;

import net.classnotfound.pet.security.domain.User;

public interface UserService {

	/**
	 * Disable the the user with corresponding userId
	 * @param userId
	 */
	void disableUser (String userId);

	
	User findUser(String userId);
}
