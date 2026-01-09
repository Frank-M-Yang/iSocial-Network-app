package app.socialnetwork.core;

/**
 * Corporate Profile - Pure data container Follower relationships are managed in
 * SocialNetwork's followGraph
 * 
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
	 * Posts an advertisement to this company's wall Time complexity: O(log n)
	 * guaranteed by Red-Black Tree
	 * 
	 * @param ad the advertisement to post
	 */
	public void postAdvertisement(Advertisement ad) {
		wall.insert(ad);
	}

	/**
	 * Checks if this is a user profile
	 * 
	 * @return false
	 */
	public boolean isUserProfile() {
		return false;
	}

	/**
	 * Checks if this is a corporate profile
	 * 
	 * @return true
	 */
	public boolean isCorporateProfile() {
		return true;
	}

	/**
	 * Returns string representation of the corporate profile Format: "Corporate
	 * Profile: company name"
	 * 
	 * @return string representation
	 */
	public String toString() {
		return "Corporate Profile: " + name;
	}
}