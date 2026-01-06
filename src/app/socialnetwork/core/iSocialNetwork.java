package app.socialnetwork.core;

public interface iSocialNetwork {

	/*************************************
	 * PART 1
	 *******************************************/
	/*
	 * Create new user profile with given parameters: username, age.
	 *
	 * @param username
	 *
	 * @param age
	 *
	 */
	public void createUserProfile(String username, int age);

	/*
	 * Create new corporate profile with given parameters: companyName.
	 *
	 * @param companyName
	 *
	 */
	public void createCorporateProfile(String companyName);

	/*
	 * Print the wall of the user.
	 *
	 * @param username
	 *
	 */
	public void printWallOf(String username);

	/*
	 * Create a new status update of a user.
	 *
	 * @param username of the author of the status update
	 *
	 * @param status content
	 *
	 * @param privacy setting of the status
	 *
	 * @param ageLimit
	 *
	 * @param timestamp
	 *
	 */
	public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp);

	/*
	 * Create new ad message.
	 *
	 * @param companyName of the author of the ad
	 *
	 * @param ad content
	 *
	 * @param ageLimit
	 *
	 * @param paid
	 *
	 * @param timestamp
	 *
	 */
	public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp);

	/*************************************
	 * PART 2
	 *******************************************/
	/*
	 * Connect two user.
	 *
	 * @param username1
	 *
	 * @param username2
	 *
	 */
	public void connect(String username1, String username2);

	/*
	 * Print all friends of the user.
	 *
	 * @param username
	 *
	 */
	public void printFriendListOf(String username);

	/*
	 * Print contents of the wall of a friend. Note: make sure people are connected
	 * before printing the wall of the friend
	 *
	 * @param username
	 *
	 * @param friendUsername
	 *
	 */
	public void printWallOfAFriend(String username, String friendUsername);

	/*
	 * Connect user with a company, i.e. user follows this company's content.
	 *
	 * @param username
	 *
	 * @param corporateName
	 *
	 */
	public void follow(String username, String corporateName);

	/*
	 * Return distance between two users, i.e. the number of steps required to go
	 * from one user to another.
	 *
	 * @param username1
	 *
	 * @param username2
	 *
	 * @return distance
	 *
	 */
	public int distance(String username1, String username2);

	/*
	 * Print path between two users, i.e. usernames of users on the path between
	 * them.
	 *
	 * @param username1
	 *
	 * @param username2
	 *
	 */
	public void printPath(String username1, String username2);

	/*
	 * Return distance between two users, i.e. the number of steps required to go
	 * from one user to another, BUT excluding corporate profiles from the path.
	 *
	 * @param username1
	 *
	 * @param username2
	 *
	 * @return distance
	 *
	 */
	public int distanceExcludeCorporate(String username1, String username2);

	/*
	 * Print path between two users, i.e. usernames of users on the path between
	 * them, BUT excluding corporate profiles from the path.
	 *
	 * @param username1
	 *
	 * @param username2
	 *
	 */
	public void printPathExcludeCorporate(String username1, String username2);

}
