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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Please enter the email!")
    @Column(nullable = false, unique = true)
    private String email;
    @NotNull(message = "Please enter the password!")
    @Column(nullable = false)
    private String password;
    @NotNull(message = "Please enter the phone number!")
    @Column(nullable = false)
    private String phoneNumber;
    @OneToOne(optional = false)
    private Address address;

    public User(String email, String password, String phoneNumber, Address address) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
