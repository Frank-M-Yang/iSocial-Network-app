package app.socialnetwork.core;

/**
 * Main test class for Social Network - Part 1 + Part 2
 * 
 * @author Ming Yang (Student ID: 0643277)
 */
public class Main {

	public static void main(String[] args) {

		try {
			System.out.println("=== SOCIAL NETWORK PROJECT - COMPLETE TESTS ===\n");

			SocialNetwork network = new SocialNetwork();

			// ============ PART 1 TESTS ============

			System.out.println("--- Part 1: Basic Functionality ---\n");

			// Test 1: Create profiles
			System.out.println("Test 1: Creating User Profiles");
			network.createUserProfile("Frank", 30);
			network.createUserProfile("Ming", 18);
			network.createUserProfile("Nancy", 17);
			network.createUserProfile("Alice", 20);
			System.out.println("Created 4 users: Frank(30), Ming(18), Nancy(17), Alice(20)\n");

			System.out.println("Test 2: Creating Corporate Profiles");
			network.createCorporateProfile("Huawei");
			network.createCorporateProfile("Samsung");
			network.createCorporateProfile("Suntory");
			System.out.println("Created 3 companies: Huawei, Samsung, Suntory\n");

			// Test 3: Post status updates
			System.out.println("Test 3: Posting Status Updates");

			network.postStatus("Frank", "Just finished my thesis proposal!", StatusUpdate.PUBLIC, 0, 1000);
			network.postStatus("Frank", "Meeting with Prof. Chan tomorrow", StatusUpdate.FRIENDS, 0, 1050);
			network.postStatus("Frank", "Worried about the deadline...", StatusUpdate.PRIVATE, 0, 1100);
			network.postStatus("Frank", "Celebrating with friends tonight", StatusUpdate.FRIENDS, 0, 1200);
			network.postStatus("Frank", "Starting to write research paper", StatusUpdate.FRIENDS, 0, 1300);
			network.postStatus("Frank", "Submitted paper to conference", StatusUpdate.PUBLIC, 0, 1350);
			network.postStatus("Frank", "Paper accepted! So excited!", StatusUpdate.PUBLIC, 0, 1400);
			network.postStatus("Frank", "Preparing presentation slides", StatusUpdate.FRIENDS, 0, 1450);

			network.postStatus("Ming", "Starting university next week!", StatusUpdate.PUBLIC, 0, 1020);
			network.postStatus("Ming", "Who wants to study together?", StatusUpdate.FRIENDS, 0, 1080);

			network.postStatus("Nancy", "Last year of high school!", StatusUpdate.PUBLIC, 0, 1010);
			network.postStatus("Nancy", "Preparing for university entrance exam", StatusUpdate.PUBLIC, 0, 1090);
			network.postStatus("Nancy", "Math homework is so difficult", StatusUpdate.FRIENDS, 0, 1130);
			network.postStatus("Nancy", "Visited VUB campus today - loved it!", StatusUpdate.PUBLIC, 0, 1170);
			network.postStatus("Nancy", "Want to study computer science", StatusUpdate.PUBLIC, 0, 1270);

			network.postStatus("Alice", "Coffee at the library anyone?", StatusUpdate.PUBLIC, 0, 1030);
			network.postStatus("Alice", "Found a great study spot", StatusUpdate.FRIENDS, 0, 1070);
			System.out.println("Posted status updates with various privacy levels\n");

			// Test 4: Post advertisements
			System.out.println("Test 4: Posting Advertisements");
			network.postAd("Huawei", "New Mate 60 Pro - Advanced Technology!", 0, true, 1025);
			network.postAd("Huawei", "Student discount: 20% off all phones", 0, true, 1075);

			network.postAd("Samsung", "Galaxy S24 Ultra now available!", 0, true, 1035);
			network.postAd("Samsung", "Trade in your old phone for credit", 0, false, 1085);

			network.postAd("Suntory", "Premium Whisky - Aged 18 years", 18, true, 1040);
			network.postAd("Suntory", "Yamazaki Single Malt - Limited Edition", 18, true, 1095);
			System.out.println("Posted advertisements (Note: Suntory ads are 18+)\n");

			// Test 5: Print walls without follows
			System.out.println("Test 5: Frank's Wall (no companies followed yet)");
			network.printWallOf("Frank");
			System.out.println();

			// Test 6: Print walls of Nancy
			System.out.println("Test 6: Nancy's Wall (she's 17)");
			network.printWallOf("Nancy");
			System.out.println();

			// ============ PART 2 TESTS ============

			System.out.println("\n--- Part 2: Connections and Paths ---\n");

			// Test 7: Create friend network
			System.out.println("Test 7: Building Friend Network");
			System.out.println("Social structure:");
			System.out.println("  Frank -- Alice -- Ming");
			System.out.println("    |               |");
			System.out.println("  Nancy ----------Ming");
			System.out.println();

			network.connect("Frank", "Alice");
			network.connect("Frank", "Nancy");
			network.connect("Alice", "Ming");
			network.connect("Nancy", "Ming");

			System.out.println("Connections created:");
			System.out.println("  - Frank and Alice are friends");
			System.out.println("  - Frank and Nancy are friends");
			System.out.println("  - Alice and Ming are friends");
			System.out.println("  - Nancy and Ming are friends\n");

			// Test 8: Print friend lists
			System.out.println("Test 8: Friend Lists");
			network.printFriendListOf("Frank");
			System.out.println();
			network.printFriendListOf("Alice");
			System.out.println();
			network.printFriendListOf("Ming");
			System.out.println();

			// Test 9: Follow companies
			System.out.println("Test 9: Following Companies");
			network.follow("Frank", "Huawei");
			network.follow("Frank", "Samsung");
			network.follow("Frank", "Suntory");

			network.follow("Ming", "Huawei");
			network.follow("Ming", "Suntory");

			network.follow("Nancy", "Samsung");
			network.follow("Nancy", "Suntory");

			network.follow("Alice", "Huawei");
			network.follow("Alice", "Samsung");

			System.out.println("Follow relationships:");
			System.out.println("  - Frank follows: Huawei, Samsung, Suntory");
			System.out.println("  - Ming follows: Huawei, Suntory");
			System.out.println("  - Nancy follows: Samsung, Suntory (but can't see 18+ ads)");
			System.out.println("  - Alice follows: Huawei, Samsung\n");

			// Test 10: Print walls with ads and age filtering
			System.out.println("Test 10: Frank's Wall (30 years old, sees all ads including Suntory)");
			network.printWallOf("Frank");
			System.out.println();

			System.out.println("Test 11: Nancy's Wall (17 years old, CANNOT see Suntory 18+ ads)");
			network.printWallOf("Nancy");
			System.out.println("Note: Nancy follows Suntory but can't see their ads due to age limit!\n");

			System.out.println("Test 12: Ming's Wall (18 years old, can see Suntory ads)");
			network.printWallOf("Ming");
			System.out.println();

			// Test 13: Privacy filtering - viewing friend's wall
			System.out.println("Test 13: Privacy Filtering");
			network.postStatus("Frank", "Public: VUB campus is beautiful today", StatusUpdate.PUBLIC, 0, 1150);
			network.postStatus("Frank", "Friends: Stressed about my thesis defense", StatusUpdate.FRIENDS, 0, 1151);
			network.postStatus("Frank", "Private: Should I continue to PhD?", StatusUpdate.PRIVATE, 0, 1152);

			System.out.println("\nAlice (Frank's friend) viewing Frank's wall:");
			network.printWallOfAFriend("Alice", "Frank");
			System.out.println("Note: Alice can see PUBLIC and FRIENDS posts, but not PRIVATE\n");

			// Test 14: Distance calculations
			System.out.println("Test 14: Distance Calculations");
			System.out.println("Distance Frank -> Alice: " + network.distance("Frank", "Alice"));
			System.out.println("Distance Frank -> Ming: " + network.distance("Frank", "Ming"));
			System.out.println("Distance Frank -> Nancy: " + network.distance("Frank", "Nancy"));
			System.out.println("Distance Alice -> Nancy: " + network.distance("Alice", "Nancy"));
			System.out.println();

			// Test 15: Print paths
			System.out.println("Test 15: Shortest Paths");
			network.printPath("Frank", "Ming");
			network.printPath("Alice", "Nancy");
			System.out.println();

			// Test 16: Test non-chronological timestamps
			System.out.println("Test 16: Non-Chronological Timestamps (Red-Black Tree sorting)");
			network.postStatus("Alice", "Post from the future", StatusUpdate.PUBLIC, 0, 2000);
			network.postStatus("Alice", "Post from the past", StatusUpdate.PUBLIC, 0, 500);
			network.postStatus("Alice", "Post from middle", StatusUpdate.PUBLIC, 0, 1500);
			System.out.println("Alice's wall (should be sorted automatically):");
			network.printWallOf("Alice");
			System.out.println();

			// Test 17: Edge cases
			System.out.println("Test 17: Edge Cases");
			System.out.println("Distance to self: " + network.distance("Frank", "Frank"));
			System.out.println("Distance to non-existent user: " + network.distance("Frank", "Kim"));
			network.printPath("Frank", "Kim");
			System.out.println();

			// Test 18: toString() methods
			System.out.println("Test 18: Testing toString() Methods");
			UserProfile testUser = new UserProfile("TestUser", 25);
			System.out.println(testUser.toString());

			CorporateProfile testCompany = new CorporateProfile("TestCorp");
			System.out.println(testCompany.toString());

			StatusUpdate testStatus = new StatusUpdate("TestUser", "Test status", StatusUpdate.PUBLIC, 0, 9999);
			System.out.println(testStatus.toString());

			Advertisement testAd = new Advertisement("TestCorp", "Test ad", 18, true, 9999);
			System.out.println(testAd.toString());
			System.out.println();

			System.out.println("=== ALL TESTS COMPLETED ===\n");

		} catch (Exception e) {
			System.err.println("\n=== ERROR OCCURRED ===");
			System.err.println("Error type: " + e.getClass().getName());
			System.err.println("Error message: " + e.getMessage());
			System.err.println("Stack trace:");
			e.printStackTrace();
		}
	}
}