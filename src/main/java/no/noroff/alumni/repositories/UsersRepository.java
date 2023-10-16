package no.noroff.alumni.repositories;

import no.noroff.alumni.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
