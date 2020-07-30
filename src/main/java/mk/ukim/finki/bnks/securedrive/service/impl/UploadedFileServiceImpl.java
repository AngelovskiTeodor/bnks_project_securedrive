package mk.ukim.finki.bnks.securedrive.service.impl;

import mk.ukim.finki.bnks.securedrive.model.UploadedFile;
import mk.ukim.finki.bnks.securedrive.repository.UploadedFileRepository;
import mk.ukim.finki.bnks.securedrive.service.UploadedFileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UploadedFileServiceImpl implements UploadedFileService {
    private final UploadedFileRepository uploadedFileRepository;

    public UploadedFileServiceImpl(UploadedFileRepository uploadedFileRepository) {
        this.uploadedFileRepository = uploadedFileRepository;
    }


    @Override
    public List<UploadedFile> findAll() {
        return this.uploadedFileRepository.findAll();
    }

    @Override
    public Optional<UploadedFile> findById(Long id) {
        return uploadedFileRepository.findById(id);
    }

    @Override
    public void saveUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFileRepository.save(uploadedFile);
    }
}
