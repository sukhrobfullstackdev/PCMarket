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
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Printer extends AbstractProduct {
    private boolean colored = false;
    private boolean twoSidedPrinting = false;
    private String deviceFeatures;
    private String maximumFormat;
    private String blackPrintSpeed;
    private String colorPrintSpeed;
    private String printTechnology;
    private String connectivity;
    @ManyToOne
    private Category category;
}
