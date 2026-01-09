package app.socialnetwork.core;

/**
 * Corporate profile in the social network Represents companies that can post
 * advertisements
 * 
 * @author Ming Yang (Student ID: 0643277)
 */
public class CorporateProfile extends Profile {

	/**
	 * Creates a new corporate profile
	 * 
	 * @param companyName the name of the company
	 */
	public CorporateProfile(String companyName) {
		super(companyName);
	}

	/**
	 * Posts an advertisement to this company's wall
	 * 
	 * @param ad the advertisement to post
	 */
	public void postAdvertisement(Advertisement ad) {
		wall.insert(ad);
	}

	/**
	 * Checks if this is a user profile
	 * 
	 * @return false (this is not a user profile)
	 */
	@Override
	public boolean isUserProfile() {
		return false;
	}

	/**
	 * Checks if this is a corporate profile
	 * 
	 * @return true (this is a corporate profile)
	 */
	@Override
	public boolean isCorporateProfile() {
		return true;
	}

	/**
	 * Returns a string representation of this corporate profile
	 * 
	 * @return string in format "Corporate Profile: name"
	 */
	@Override
	public String toString() {
		return "Corporate Profile: " + name;
	}
}