package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pcmarket.pcmarket.entity.template.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractEntity {
    @ManyToOne
    private Category category;

    public Category(String name, boolean isActive, Category category) {
        super(name, isActive);
        this.category = category;
    }

    public Category(String name, boolean isActive) {
        super(name, isActive);
    }
}
