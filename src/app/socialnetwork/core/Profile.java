package app.socialnetwork.core;

import app.socialnetwork.datastructure.RedBlackTree;

/**
 * Abstract base class for all profile types in the social network. Provides
 * common functionality for both user and corporate profiles. Uses Red-Black
 * Tree for O(log n) guaranteed wall operations.
 */
public abstract class Profile {

	protected String name;
	protected RedBlackTree wall;

	/**
	 * Creates a new profile with the given name
	 * 
	 * @param name the name of the profile (username or company name)
	 */
	public Profile(String name) {
		this.name = name;
		this.wall = new RedBlackTree();
	}

	/**
	 * Gets the name of this profile
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the wall (Red-Black Tree of posts) for this profile
	 * 
	 * @return the wall
	 */
	public RedBlackTree getWall() {
		return wall;
	}

	/**
	 * Checks if this is a user profile
	 * 
	 * @return true if this is a UserProfile, false otherwise
	 */
	public abstract boolean isUserProfile();

	/**
	 * Checks if this is a corporate profile
	 * 
	 * @return true if this is a CorporateProfile, false otherwise
	 */
	public abstract boolean isCorporateProfile();

	/**
	 * Returns a string representation of the profile
	 * 
	 * @return string representation
	 */
	public abstract String toString();
}
