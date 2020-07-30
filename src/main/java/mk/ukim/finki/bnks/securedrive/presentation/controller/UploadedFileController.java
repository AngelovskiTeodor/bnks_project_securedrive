package mk.ukim.finki.bnks.securedrive.presentation.controller;

import mk.ukim.finki.bnks.securedrive.Utils.Utils;
import mk.ukim.finki.bnks.securedrive.model.UploadedFile;
import mk.ukim.finki.bnks.securedrive.model.User;
import mk.ukim.finki.bnks.securedrive.service.UploadedFileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/files")
public class UploadedFileController {
    private final UploadedFileService uploadedFileService;

    public UploadedFileController(UploadedFileService uploadedFileService) {
        this.uploadedFileService = uploadedFileService;
    }

    @GetMapping
    public String getMyFiles (Model model) {
        List<UploadedFile> files = uploadedFileService.findAll();
        model.addAttribute("files", files);
        return "list";
    }

    @GetMapping
    @RequestMapping("/{id}")
    public String getFile (@PathVariable Long id, Model model) throws Exception {
        UploadedFile file = uploadedFileService.findById(id).orElseThrow(() -> new Exception("File does not exist: "+id));
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String passwordBase64 = Base64.getEncoder().encodeToString(currentUser.getPassword().getBytes());
        file.setEncodedFile(Utils.decrypt(file.getEncodedFile(),passwordBase64));
        model.addAttribute("file", file);
        return "list";
    }

    @PostMapping
    @RequestMapping("/upload")
    public String uploadFile (@RequestParam MultipartFile file) throws IOException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, ShortBufferException, InvalidKeyException {
        UploadedFile uploadedFile = new UploadedFile();
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        uploadedFile.setOwnedByUser(currentUser.getUsername());
        String encodedFile = Base64.getEncoder().encodeToString(file.getBytes());
        encodedFile = Utils.encrypt(encodedFile, Base64.getEncoder().encodeToString(currentUser.getPassword().getBytes()));
        uploadedFile.setEncodedFile(encodedFile);
        uploadedFileService.saveUploadedFile(uploadedFile);
        return "list";
    }
}