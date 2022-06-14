package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.User;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.UserDto;
import uz.pcmarket.pcmarket.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getUsers(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.editUser(id, userDto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
