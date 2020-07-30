package mk.ukim.finki.bnks.securedrive.repository;

import mk.ukim.finki.bnks.securedrive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
