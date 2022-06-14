package uz.pcmarket.pcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotNull(message = "Please enter the city!")
    private String city;
    @NotNull(message = "Please enter the district!")
    @Column(nullable = false)
    private String district;
    @NotNull(message = "Please enter the street!")
    @Column(nullable = false)
    private String street;
    @NotNull(message = "Please enter the home number!")
    @Column(nullable = false)
    private String homeNumber;

    public Address(String city, String district, String street, String homeNumber) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
