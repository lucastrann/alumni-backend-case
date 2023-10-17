package no.noroff.alumni.expections;

import jakarta.persistence.EntityNotFoundException;

public class TopicNotFoundException extends EntityNotFoundException {
    public TopicNotFoundException(int id) {
        super("Topic does not exist with ID: " + id);
    }
}