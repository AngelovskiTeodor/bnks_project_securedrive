package mk.ukim.finki.bnks.securedrive.service;

import mk.ukim.finki.bnks.securedrive.model.UploadedFile;

import java.util.List;
import java.util.Optional;

public interface UploadedFileService {
    List<UploadedFile> findAll();
    Optional<UploadedFile> findById(Long id);
    void saveUploadedFile(UploadedFile uploadedFile);
}
