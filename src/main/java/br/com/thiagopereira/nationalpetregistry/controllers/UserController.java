package br.com.thiagopereira.nationalpetregistry.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.thiagopereira.nationalpetregistry.models.User;
import br.com.thiagopereira.nationalpetregistry.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/users")
@Tag(name = "Users")
public class UserController {

  @Autowired
  private final UserService userService;

  public UserController(
    UserService userService
  ) {
    this.userService = userService;
  }

  @GetMapping
  @Operation(summary = "Find all users", description = "Find all users")
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  @Operation(summary = "Find user by id", description = "Find user by id")
  public ResponseEntity<User> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping
  @Operation(summary = "Store user", description = "Store user")
  public ResponseEntity<User> save(@RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update user", description = "Update user")
  public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody User user) {
    return ResponseEntity.ok(userService.update(id, user));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete user", description = "Delete user")
  public void delete(@PathVariable UUID id) {
    userService.delete(id);
  }

}
