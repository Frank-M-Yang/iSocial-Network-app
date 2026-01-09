package app.socialnetwork.core;

public class StatusUpdate implements Comparable {

	private String content;
	private String author;
	private int privacy;
	private int ageLimit;
	private int timestamp;

	public static final int PUBLIC = 0;
	public static final int FRIENDS = 1;
	public static final int PRIVATE = 2;

	public StatusUpdate(String author, String content, int privacy, int ageLimit, int timestamp) {
		this.author = author;
		this.content = content;
		this.privacy = privacy;
		this.ageLimit = ageLimit;
		this.timestamp = timestamp;
	}

	public String getAuthor() {
		return author;
	}

	public int getPrivacy() {
		return privacy;
	}

	public int getAgeLimit() {
		return ageLimit;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public boolean isVisibleTo(int viewerAge, int relationshipLevel) {
		if (viewerAge < ageLimit)
			return false;
		if (privacy == PUBLIC)
			return true;
		if (privacy == FRIENDS)
			return relationshipLevel >= FRIENDS;
		if (privacy == PRIVATE)
			return relationshipLevel >= PRIVATE;
		return false;
	}

	public int compareTo(Object o) {
		StatusUpdate other = (StatusUpdate) o;
		return this.timestamp - other.timestamp;
	}

	public String toString() {
		String privacyStr = (privacy == 0) ? "public" : (privacy == 1) ? "friends" : "private";
		return "Status update: " + timestamp + ", " + author + ", " + privacyStr + ", " + ageLimit + ", " + content;
	}
}