package br.com.thiagopereira.nationalpetregistry.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thiagopereira.nationalpetregistry.models.User;
import br.com.thiagopereira.nationalpetregistry.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

  @Autowired
  private final UserRepository userRepository;

  public UserService(
    UserRepository userRepository
  ) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(UUID id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new EntityNotFoundException("User not found"));
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User update(UUID id, User updatedUser) {
    User user = this.findById(id);
    user.setName(updatedUser.getName());
    user.setAddress(updatedUser.getAddress());
    user.setDateOfBirth(updatedUser.getDateOfBirth());
    user.setGender(updatedUser.getGender());
    user.setIdentificationDocument(updatedUser.getIdentificationDocument());
    user.setPhone(updatedUser.getPhone());
    return userRepository.save(user);
  }

  public void delete(UUID id) {
    User user = this.findById(id);
    userRepository.delete(user);
  }

}
