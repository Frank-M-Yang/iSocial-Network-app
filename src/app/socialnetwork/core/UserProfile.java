package app.socialnetwork.core;

/**
 * User profile in the social network Contains user-specific information like
 * age and status updates
 * 
 * @author Ming Yang (Student ID: 0643277)
 */
public class UserProfile extends Profile {

	private int age;

	/**
	 * Creates a new user profile
	 * 
	 * @param username the username
	 * @param age      the age of the user
	 */
	public UserProfile(String username, int age) {
		super(username);
		this.age = age;
	}

	/**
	 * Gets the age of this user
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Posts a status update to this user's wall
	 * 
	 * @param status the status update to post
	 */
	public void postStatus(StatusUpdate status) {
		wall.insert(status);
	}

	/**
	 * Checks if this is a user profile
	 * 
	 * @return true (this is a user profile)
	 */
	@Override
	public boolean isUserProfile() {
		return true;
	}

	/**
	 * Checks if this is a corporate profile
	 * 
	 * @return false (this is not a corporate profile)
	 */
	@Override
	public boolean isCorporateProfile() {
		return false;
	}

	/**
	 * Returns a string representation of this user profile
	 * 
	 * @return string in format "User Profile: name, age"
	 */
	@Override
	public String toString() {
		return "User Profile: " + name + ", " + age;
	}
}