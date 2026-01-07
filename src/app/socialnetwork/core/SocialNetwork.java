package app.socialnetwork.core;

/**
 * Implementation of the social network system. Manages user profiles, corporate
 * profiles, posts, and connections.
 */
public class SocialNetwork implements iSocialNetwork {

	@Override
	public void createUserProfile(String username, int age) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createCorporateProfile(String companyName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printWallOf(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connect(String username1, String username2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printFriendListOf(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printWallOfAFriend(String username, String friendUsername) {
		// TODO Auto-generated method stub

	}

	@Override
	public void follow(String username, String corporateName) {
		// TODO Auto-generated method stub

	}

	@Override
	public int distance(String username1, String username2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printPath(String username1, String username2) {
		// TODO Auto-generated method stub

	}

	@Override
	public int distanceExcludeCorporate(String username1, String username2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void printPathExcludeCorporate(String username1, String username2) {
		// TODO Auto-generated method stub

	}

}
