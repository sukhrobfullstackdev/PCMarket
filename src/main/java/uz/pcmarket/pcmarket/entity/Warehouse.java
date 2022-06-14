package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.template.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warehouse extends AbstractEntity {
    @OneToOne
    private Address address;

    public Warehouse(String name, boolean isActive, Address address) {
        super(name, isActive);
        this.address = address;
    }
}
