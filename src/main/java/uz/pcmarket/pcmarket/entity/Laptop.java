package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.template.AbstractProduct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop extends AbstractProduct {
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
}
