package uz.pcmarket.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDto {
    private String name;
    private boolean isActive = true;
    private Integer categoryParentId;
}
