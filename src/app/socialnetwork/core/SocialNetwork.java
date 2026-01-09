package app.socialnetwork.core;

import app.socialnetwork.datastructure.Graph;
import app.socialnetwork.datastructure.Queue;
import app.socialnetwork.datastructure.RedBlackTree;
import app.socialnetwork.datastructure.SplayTreeDictionary;
import app.socialnetwork.datastructure.Stack;
import app.socialnetwork.datastructure.Vector;

/**
 * Social Network implementation:
 * 
 * Data Structures Used: - User/Company storage: SplayTreeDictionary (O(log n)
 * amortized) - Friendship relations: Undirected Graph (user <-> user) - Follow
 * relations: Directed Graph (user -> company) - Timeline/Wall: RedBlackTree
 * (O(log n) guaranteed)
 * 
 * Key Design Principles: - All relationships stored in graphs, not in Profile
 * objects - No duplicate names
 * 
 * @author Ming Yang (Student ID: 0643277)
 */
public class SocialNetwork implements iSocialNetwork {

	private SplayTreeDictionary profiles;
	private Graph friendshipGraph;
	private Graph followGraph;

	/**
	 * Creates a new social network with empty data structures
	 */
	public SocialNetwork() {
		profiles = new SplayTreeDictionary();
		friendshipGraph = new Graph();
		followGraph = new Graph();
	}

	/**
	 * Finds a profile by name using Splay Tree Time complexity: O(log n) amortized
	 * 
	 * @param name the username or company name
	 * @return the Profile object, or null if not found
	 */
	private Profile findProfile(String name) {
		return (Profile) profiles.find(name);
	}

	/**
	 * Finds a user profile by username
	 * 
	 * @param username the username
	 * @return the UserProfile, or null if not found or not a user
	 */
	private UserProfile findUser(String username) {
		Profile profile = findProfile(username);
		if (profile != null && profile.isUserProfile()) {
			return (UserProfile) profile;
		}
		return null;
	}

	/**
	 * Finds a corporate profile by company name
	 * 
	 * @param companyName the company name
	 * @return the CorporateProfile, or null if not found or not a company
	 */
	private CorporateProfile findCompany(String companyName) {
		Profile profile = findProfile(companyName);
		if (profile != null && profile.isCorporateProfile()) {
			return (CorporateProfile) profile;
		}
		return null;
	}

	// ============ PART 1 IMPLEMENTATION ============

	/**
	 * Creates a new user profile Adds user to both profile dictionary and graphs
	 * 
	 * @param username the username (must be unique)
	 * @param age      the age of the user
	 */
	@Override
	public void createUserProfile(String username, int age) {
		if (findProfile(username) != null) {
			System.out.println("Profile " + username + " already exists.");
			return;
		}

		UserProfile newUser = new UserProfile(username, age);
		profiles.add(username, newUser);
		friendshipGraph.addNode(username);
		followGraph.addNode(username);
	}

	/**
	 * Creates a new corporate profile Adds company to profile dictionary and follow
	 * graph
	 * 
	 * @param companyName the company name (must be unique)
	 */
	@Override
	public void createCorporateProfile(String companyName) {
		if (findProfile(companyName) != null) {
			System.out.println("Profile " + companyName + " already exists.");
			return;
		}

		CorporateProfile newCompany = new CorporateProfile(companyName);
		profiles.add(companyName, newCompany);
		followGraph.addNode(companyName);
	}

	/**
	 * Prints the wall of a user with 4:1 status-to-ad ratio Ads come from companies
	 * the user follows (via followGraph)
	 * 
	 * @param username the username
	 */
	@Override
	public void printWallOf(String username) {
		UserProfile user = findUser(username);
		if (user == null) {
			System.out.println("User " + username + " not found.");
			return;
		}

		// Collect status updates
		Vector statusUpdates = new Vector(100);
		StatusCollector statusCollector = new StatusCollector(statusUpdates);
		user.getWall().traverseInOrder(statusCollector);

		// Get followed companies
		Vector followedCompanyNames = followGraph.getNeighbors(username);

		// Collect and filter ads
		Vector allAds = new Vector(50);
		int userAge = user.getAge();

		for (int i = 0; i < followedCompanyNames.size(); i++) {
			String companyName = (String) followedCompanyNames.get(i);
			CorporateProfile company = findCompany(companyName);

			if (company != null) {
				Vector companyAds = new Vector(20);
				StatusCollector adCollector = new StatusCollector(companyAds);
				company.getWall().traverseInOrder(adCollector);

				for (int j = 0; j < companyAds.size(); j++) {
					Advertisement ad = (Advertisement) companyAds.get(j);
					if (ad.isVisibleTo(userAge)) {
						allAds.addLast(ad);
					}
				}
			}
		}

		// Sort ads by timestamp
		RedBlackTree sortedAdsTree = new RedBlackTree();
		for (int i = 0; i < allAds.size(); i++) {
			sortedAdsTree.insert((Comparable) allAds.get(i));
		}

		Vector sortedAds = new Vector(50);
		StatusCollector sortedAdCollector = new StatusCollector(sortedAds);
		sortedAdsTree.traverseInOrder(sortedAdCollector);

		// Print 4:1 ratio (show all ads)
		int statusIndex = 0;
		int adIndex = 0;

		while (statusIndex < statusUpdates.size() || adIndex < sortedAds.size()) {
			// Print up to 4 status updates
			int count = 0;
			while (count < 4 && statusIndex < statusUpdates.size()) {
				System.out.println(statusUpdates.get(statusIndex));
				statusIndex++;
				count++;
			}

			// Print 1 ad (if available)
			if (adIndex < sortedAds.size()) {
				System.out.println(sortedAds.get(adIndex));
				adIndex++;
			}
		}
	}

	/**
	 * Posts a status update to a user's wall
	 * 
	 * @param username  the username of the author
	 * @param status    the content of the status
	 * @param privacy   the privacy setting (0=public, 1=friends, 2=private)
	 * @param ageLimit  the minimum age to view
	 * @param timestamp the timestamp
	 */
	@Override
	public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp) {
		UserProfile user = findUser(username);
		if (user == null) {
			System.out.println("User " + username + " not found.");
			return;
		}

		StatusUpdate newStatus = new StatusUpdate(username, status, privacy, ageLimit, timestamp);
		user.postStatus(newStatus);
	}

	/**
	 * Posts an advertisement to a company's wall
	 * 
	 * @param companyName the name of the company
	 * @param ad          the content of the advertisement
	 * @param ageLimit    the minimum age to view
	 * @param paid        whether this is a paid ad
	 * @param timestamp   the timestamp
	 */
	@Override
	public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp) {
		CorporateProfile company = findCompany(companyName);
		if (company == null) {
			System.out.println("Company " + companyName + " not found.");
			return;
		}

		Advertisement newAd = new Advertisement(companyName, ad, ageLimit, paid, timestamp);
		company.postAdvertisement(newAd);
	}

	// ============ PART 2 IMPLEMENTATION ============

	/**
	 * Connects two users as friends (bidirectional) Adds edges in both directions
	 * in the friendship graph
	 * 
	 * @param username1 the first user
	 * @param username2 the second user
	 */
	@Override
	public void connect(String username1, String username2) {
		UserProfile user1 = findUser(username1);
		UserProfile user2 = findUser(username2);

		if (user1 == null) {
			System.out.println("User " + username1 + " not found.");
			return;
		}
		if (user2 == null) {
			System.out.println("User " + username2 + " not found.");
			return;
		}

		// Add bidirectional edges (undirected graph)
		friendshipGraph.addEdge(username1, username2);
		friendshipGraph.addEdge(username2, username1);
	}

	/**
	 * Prints the friend list of a user Gets friends from the friendship graph
	 * 
	 * @param username the username
	 */
	@Override
	public void printFriendListOf(String username) {
		UserProfile user = findUser(username);
		if (user == null) {
			System.out.println("User " + username + " not found.");
			return;
		}

		Vector friends = friendshipGraph.getNeighbors(username);
		System.out.println("Friends of " + username + ":");
		for (int i = 0; i < friends.size(); i++) {
			System.out.println("  " + friends.get(i));
		}
	}

	/**
	 * Prints the wall of a friend with privacy filtering Checks friendship via
	 * friendship graph
	 * 
	 * @param username       the viewer's username
	 * @param friendUsername the friend's username
	 */
	@Override
	public void printWallOfAFriend(String username, String friendUsername) {
		UserProfile user = findUser(username);
		UserProfile friend = findUser(friendUsername);

		if (user == null) {
			System.out.println("User " + username + " not found.");
			return;
		}
		if (friend == null) {
			System.out.println("User " + friendUsername + " not found.");
			return;
		}

		// Check friendship via graph
		if (!friendshipGraph.hasEdge(username, friendUsername)) {
			System.out.println(username + " and " + friendUsername + " are not friends.");
			return;
		}

		System.out.println("Wall of " + friendUsername + " (visible to " + username + "):");

		// Collect all status updates from friend's wall
		Vector allStatuses = new Vector(100);
		StatusCollector collector = new StatusCollector(allStatuses);
		friend.getWall().traverseInOrder(collector);

		// Manually filter by privacy
		int viewerAge = user.getAge();
		for (int i = 0; i < allStatuses.size(); i++) {
			StatusUpdate status = (StatusUpdate) allStatuses.get(i);
			if (status.isVisibleTo(viewerAge, 1)) { // 1 = friends level
				System.out.println(status);
			}
		}
	}

	/**
	 * Makes a user follow a company Adds directed edge: user -> company in follow
	 * graph
	 * 
	 * @param username      the username
	 * @param corporateName the company name
	 */
	@Override
	public void follow(String username, String corporateName) {
		UserProfile user = findUser(username);
		CorporateProfile company = findCompany(corporateName);

		if (user == null) {
			System.out.println("User " + username + " not found.");
			return;
		}
		if (company == null) {
			System.out.println("Company " + corporateName + " not found.");
			return;
		}

		// Add directed edge: user -> company
		followGraph.addEdge(username, corporateName);
	}

	// ============ BFS ALGORITHMS ============

	/**
	 * Calculates shortest distance between two users using BFS Uses friendship
	 * graph refer to exercise 12.3.1
	 * 
	 * @param username1 the first user
	 * @param username2 the second user
	 * @return the distance, or -1 if not connected or user not found
	 */
	@Override
	public int distance(String username1, String username2) {
		UserProfile start = findUser(username1);
		UserProfile end = findUser(username2);

		if (start == null || end == null) {
			return -1;
		}
		if (username1.equals(username2)) {
			return 0;
		}

		// BFS using friendship graph
		Queue queue = new Queue();
		SplayTreeDictionary visited = new SplayTreeDictionary();
		SplayTreeDictionary distances = new SplayTreeDictionary();

		queue.push(username1);
		visited.add(username1, true);
		distances.add(username1, 0);

		while (!queue.empty()) {
			String current = (String) queue.pop();
			int currentDist = ((Integer) distances.find(current)).intValue();

			Vector neighbors = friendshipGraph.getNeighbors(current);
			for (int i = 0; i < neighbors.size(); i++) {
				String neighbor = (String) neighbors.get(i);

				if (visited.find(neighbor) == null) {
					visited.add(neighbor, true);
					distances.add(neighbor, currentDist + 1);

					if (neighbor.equals(username2)) {
						return currentDist + 1;
					}

					queue.push(neighbor);
				}
			}
		}

		return -1; // Not connected
	}

	/**
	 * Prints the shortest path between two users using BFS Uses friendship graph
	 * 
	 * @param username1 the first user
	 * @param username2 the second user
	 */
	@Override
	public void printPath(String username1, String username2) {
		UserProfile start = findUser(username1);
		UserProfile end = findUser(username2);

		if (start == null || end == null) {
			System.out.println("User not found.");
			return;
		}

		if (username1.equals(username2)) {
			System.out.println(username1);
			return;
		}

		// BFS with parent tracking
		Queue queue = new Queue();
		SplayTreeDictionary visited = new SplayTreeDictionary();
		SplayTreeDictionary parent = new SplayTreeDictionary();

		queue.push(username1);
		visited.add(username1, true);
		parent.add(username1, null);

		boolean found = false;

		while (!queue.empty() && !found) {
			String current = (String) queue.pop();

			Vector neighbors = friendshipGraph.getNeighbors(current);
			for (int i = 0; i < neighbors.size(); i++) {
				String neighbor = (String) neighbors.get(i);

				if (visited.find(neighbor) == null) {
					visited.add(neighbor, true);
					parent.add(neighbor, current);

					if (neighbor.equals(username2)) {
						found = true;
						break;
					}

					queue.push(neighbor);
				}
			}
		}

		if (!found) {
			System.out.println("No path found.");
			return;
		}

		// Reconstruct path
		Stack path = new Stack();
		String current = username2;
		while (current != null) {
			path.push(current);
			current = (String) parent.find(current);
		}

		// Print path
		System.out.print("Path: ");
		while (!path.empty()) {
			System.out.print(path.pop());
			if (!path.empty()) {
				System.out.print(" -> ");
			}
		}
		System.out.println();
	}

	/**
	 * Same as distance() since friendship graph only contains users
	 * 
	 * @param username1 the first user
	 * @param username2 the second user
	 * @return the distance
	 */
	@Override
	public int distanceExcludeCorporate(String username1, String username2) {
		return distance(username1, username2);
	}

	/**
	 * Same as printPath() since friendship graph only contains users
	 * 
	 * @param username1 the first user
	 * @param username2 the second user
	 */
	@Override
	public void printPathExcludeCorporate(String username1, String username2) {
		printPath(username1, username2);
	}
}
