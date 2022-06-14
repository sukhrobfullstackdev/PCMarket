package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.template.AbstractEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Currency extends AbstractEntity {
    public Currency(String name, boolean isActive) {
        super(name, isActive);
    }
}
