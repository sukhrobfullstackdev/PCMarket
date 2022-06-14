package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] mainContent;
    @OneToOne
    private Attachment attachment;

    public AttachmentContent(byte[] mainContent, Attachment attachment) {
        this.mainContent = mainContent;
        this.attachment = attachment;
    }
}
