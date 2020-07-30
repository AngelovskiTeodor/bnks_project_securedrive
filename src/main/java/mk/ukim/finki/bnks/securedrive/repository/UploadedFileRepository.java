package mk.ukim.finki.bnks.securedrive.repository;

import mk.ukim.finki.bnks.securedrive.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {
}
