package no.noroff.alumni.expections;

import jakarta.persistence.EntityNotFoundException;

public class PostNotFoundException extends EntityNotFoundException {

    /**
     * Constructs a new CharacterNotFoundException with the specified character ID.
     *
     * @param id The ID of the character that was not found.
     */
    public PostNotFoundException(int id) {
        super("Character does not exist with ID: " + id);
    }
}