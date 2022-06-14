package uz.pcmarket.pcmarket.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.Attachment;
import uz.pcmarket.pcmarket.entity.AttachmentContent;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private double brand;
    private String color;
    @OneToOne
    private AttachmentContent attachmentContent;
    @Column(nullable = false)
    private String description;

    public AbstractProduct(double price, double brand, String color, AttachmentContent attachmentContent, String description) {
        this.price = price;
        this.brand = brand;
        this.color = color;
        this.attachmentContent = attachmentContent;
        this.description = description;
    }
}
