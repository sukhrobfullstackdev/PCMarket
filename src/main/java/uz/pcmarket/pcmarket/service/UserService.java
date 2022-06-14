package uz.pcmarket.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Address;
import uz.pcmarket.pcmarket.entity.User;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.UserDto;
import uz.pcmarket.pcmarket.repository.AddressRepository;
import uz.pcmarket.pcmarket.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;
    final AddressRepository addressRepository;

    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<Page<User>> getUsers(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<User> getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (!exists) {
            Address savedAddress = addressRepository.save(new Address(userDto.getCity(), userDto.getDistrict(), userDto.getStreet(), userDto.getHomeNumber()));
            userRepository.save(new User(userDto.getEmail(), userDto.getPassword(), userDto.getPhoneNumber(), savedAddress));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The user is added!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The email is already exists!"));
        }
    }

    public ResponseEntity<Message> editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            boolean exists = userRepository.existsByEmailAndIdNot(userDto.getEmail(), id);
            if (!exists) {
                User user = optionalUser.get();
                Address address = user.getAddress();
                address.setCity(userDto.getCity());
                address.setDistrict(userDto.getDistrict());
                address.setStreet(userDto.getStreet());
                address.setHomeNumber(userDto.getHomeNumber());
                addressRepository.save(address);
                user.setEmail(userDto.getEmail());
                user.setPassword(userDto.getPassword());
                user.setPhoneNumber(userDto.getPhoneNumber());
                userRepository.save(user);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The user was edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The email is already in use!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
        }
    }

    public ResponseEntity<Message> deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The user was deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The user is not found!"));
        }
    }
}
