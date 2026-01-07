package app.socialnetwork.core;

/**
 * Represents an advertisement posted by a corporate profile. Similar to
 * StatusUpdate but with additional 'paid' attribute.
 */
public class Advertisement implements Comparable {

	private String content;
	private String companyName;
	private int ageLimit;
	private boolean paid;
	private int timestamp;

	/**
	 * Creates a new advertisement
	 * 
	 * @param companyName the name of the company posting the ad
	 * @param content     the content of the advertisement
	 * @param ageLimit    the minimum age required to view this ad
	 * @param paid        whether this is a paid advertisement
	 * @param timestamp   the timestamp of the ad (higher = newer)
	 */
	public Advertisement(String companyName, String content, int ageLimit, boolean paid, int timestamp) {
		this.companyName = companyName;
		this.content = content;
		this.ageLimit = ageLimit;
		this.paid = paid;
		this.timestamp = timestamp;
	}

	/**
	 * Gets the content of the advertisement
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Gets the company name
	 * 
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * Gets the age limit
	 * 
	 * @return the age limit
	 */
	public int getAgeLimit() {
		return ageLimit;
	}

	/**
	 * Checks if this is a paid advertisement
	 * 
	 * @return true if paid, false otherwise
	 */
	public boolean isPaid() {
		return paid;
	}

	/**
	 * Gets the timestamp
	 * 
	 * @return the timestamp
	 */
	public int getTimestamp() {
		return timestamp;
	}

	/**
	 * Checks if this advertisement is visible to a viewer with given age
	 * 
	 * @param viewerAge the age of the viewer
	 * @return true if the ad is visible, false otherwise
	 */
	public boolean isVisibleTo(int viewerAge) {
		return viewerAge >= ageLimit;
	}

	/**
	 * Compares this advertisement with another based on timestamp Used for sorting
	 * ads chronologically
	 * 
	 * @param o the other object to compare with
	 * @return negative if this is older, positive if newer, 0 if same time
	 */
	public int compareTo(Object o) {
		if (!(o instanceof Advertisement)) {
			throw new IllegalArgumentException("Cannot compare Advertisement with " + o.getClass());
		}

		Advertisement other = (Advertisement) o;
		return this.timestamp - other.timestamp;
	}

	/**
	 * Returns a string representation of the advertisement Format: "Advertisement:
	 * timestamp, company, age limit, paid, content"
	 * 
	 * @return string representation
	 */
	public String toString() {
		return "Advertisement: " + timestamp + ", " + companyName + ", " + ageLimit + ", " + (paid ? "paid" : "unpaid")
				+ ", " + content;
	}
}