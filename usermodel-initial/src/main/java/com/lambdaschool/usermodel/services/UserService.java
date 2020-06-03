package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.views.UserNameCountEmails;

import java.util.List;

/**
 * The Service that works with User Model.
 * <p>
 * Note: Emails are added through the add user process
 * Roles are added through the add user process
 * No way to delete an assigned role
 */
public interface UserService
{
    /**
     * Returns a list of all the Users
     *
     * @return List of Users. If no users, empty list.
     */
    List<User> findAll();

    /**
     * A list of all users whose username contains the given substring
     *
     * @param username The substring (String) of the username of the Users you seek
     * @return List of users whose username contains the given substring
     */
    List<User> findByNameContaining(String username);

    /**
     * Returns the user with the given primary key.
     *
     * @param id The primary key (long) of the user you seek.
     * @return The given User or throws an exception if not found.
     */
    User findUserById(long id);

    /**
     * Returns the user with the given name
     *
     * @param name The full name (String) of the User you seek.
     * @return The User with the given name or throws an exception if not found.
     */
    User findByName(String name);

    /**
     * Deletes the user record, its role and user combinations, and its useremail items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the user you seek.
     */
    void delete(long id);

    /**
     * Given a complete user object, saves that user object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param user the user object to be saved
     * @return the saved user object including any automatically generated fields
     */
    User save(User user);

    /**
     * Updates the provided fields in the user record referenced by the primary key.
     * <p>
     * Regarding Role and Useremail items, this process only allows replace those list completely. Deleting and editing those lists
     * is done through a separate endpoint.
     *
     * @param user just the user fields to be updated.
     * @param id   The primary key (long) of the user to update
     * @return the complete user object that got updated
     */
    User update(
            User user,
            long id);

    /**
     * Deletes a given user role combination
     *
     * @param userid The user id of the user part of this user role combination
     * @param roleid The role id of the role part of this user role combination
     */
    void deleteUserRole(
            long userid,
            long roleid);

    /**
     * Adds a given user role combination
     *
     * @param userid The user id of the user part of this user role combination
     * @param roleid The role id of the role part of this user role combination
     */
    void addUserRole(
            long userid,
            long roleid);

    /**
     * List containing usernames and the number of emails they have - not counting the primary email
     *
     * @return List (element type is the interface UserNameCountEmails) containing usernames and the number of emails they have - not counting the primary email
     */
    List<UserNameCountEmails> getCountUserEmails();
}