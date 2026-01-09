package app.socialnetwork.core;

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
	 * Gets the age of the user
	 * 
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Posts a status update to this user's wall Time complexity: O(log n)
	 * guaranteed by Red-Black Tree
	 * 
	 * @param status the status update to post
	 */
	public void postStatus(StatusUpdate status) {
		wall.insert(status);
	}

	/**
	 * Checks if this is a user profile
	 * 
	 * @return true
	 */
	public boolean isUserProfile() {
		return true;
	}

	/**
	 * Checks if this is a corporate profile
	 * 
	 * @return false
	 */
	public boolean isCorporateProfile() {
		return false;
	}

	/**
	 * Returns string representation of the user profile Format: "User Profile:
	 * username, age"
	 * 
	 * @return string representation
	 */
	public String toString() {
		return "User Profile: " + name + ", " + age;
	}
}