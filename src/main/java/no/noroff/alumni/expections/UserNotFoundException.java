package no.noroff.alumni.expections;

import jakarta.persistence.EntityNotFoundException;

/**
 * Custom exception to indicate that a character with a specific ID does not exist.
 */
public class UserNotFoundException extends EntityNotFoundException {

    /**
     * Constructs a new CharacterNotFoundException with the specified character ID.
     *
     * @param id The ID of the character that was not found.
     */
    public UserNotFoundException(String id) {
        super("Character does not exist with ID: " + id);
    }
}