package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.template.AbstractProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Computer extends AbstractProduct {
    @Column(nullable = false)
    private String powerSupply;
    @Column(nullable = false)
    private String motherboard;
    @Column(nullable = false)
    private String RAM;
    @Column(nullable = false)
    private String CPU;
    @Column(nullable = false)
    private String SSD;
    @Column(nullable = false)
    private String videoMap;
    @Column(nullable = false)
    private String HDD;
    @ManyToOne
    private Category category;

    public Computer(double price, double brand, String color, AttachmentContent attachmentContent, String description, String powerSupply, String motherboard, String RAM, String CPU, String SSD, String videoMap, String HDD, Category category) {
        super(price, brand, color, attachmentContent, description);
        this.powerSupply = powerSupply;
        this.motherboard = motherboard;
        this.RAM = RAM;
        this.CPU = CPU;
        this.SSD = SSD;
        this.videoMap = videoMap;
        this.HDD = HDD;
        this.category = category;
    }
}
