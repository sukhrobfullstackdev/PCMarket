package uz.pcmarket.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pcmarket.pcmarket.entity.Category;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ComputerDto {
    private double price;
    private double brand;
    private String color;
    private Integer attachmentId;
    private String description;
    private String powerSupply;
    private String motherboard;
    private String RAM;
    private String CPU;
    private String SSD;
    private String videoMap;
    private String HDD;
    private Integer categoryId;
}
