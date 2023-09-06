package com.alessandropaiano.gamingeshop.service;

import com.alessandropaiano.gamingeshop.dto.UserDTO;
import com.alessandropaiano.gamingeshop.model.User;
import com.alessandropaiano.gamingeshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

   public UserDTO register(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User updatedUser) {
        return userRepository.save(updatedUser);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
