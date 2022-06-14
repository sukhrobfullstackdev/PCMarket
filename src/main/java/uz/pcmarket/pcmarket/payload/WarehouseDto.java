package uz.pcmarket.pcmarket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class WarehouseDto {
    private String name;
    private boolean isActive;
    private String city;
    private String district;
    private String street;
    private String homeNumber;
}
