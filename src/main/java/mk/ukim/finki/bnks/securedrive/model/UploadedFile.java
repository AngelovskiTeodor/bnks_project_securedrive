package mk.ukim.finki.bnks.securedrive.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="files")
@Data
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    @Getter
    String ownedByUser;

    @Setter
    @Getter
    String encodedFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnedByUser() {
        return ownedByUser;
    }

    public void setOwnedByUser(String ownedByUser) {
        this.ownedByUser = ownedByUser;
    }

    public String getEncodedFile() {
        return encodedFile;
    }

    public void setEncodedFile(String encodedFile) {
        this.encodedFile = encodedFile;
    }
}
