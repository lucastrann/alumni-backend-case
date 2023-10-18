package no.noroff.alumni.expections;
import jakarta.persistence.EntityNotFoundException;

public class ReplyNotFoundException {extends EntityNotFoundException {

    /**
     * Constructs a new CharacterNotFoundException with the specified character ID.
     *
     * @param id The ID of the character that was not found.
     */
    public UserNotFoundException(String id) {
        super("Character does not exist with ID: " + id);
    }
}

