package edu.unimagdalena.user.service;

import edu.unimagdalena.user.entity.User;
import edu.unimagdalena.user.entity.UserDTO;
import edu.unimagdalena.user.entity.UserMapper;
import edu.unimagdalena.user.exception.ResourceNotAbleToDeleteException;
import edu.unimagdalena.user.exception.ResourceNotFoundException;
import edu.unimagdalena.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.DtoToEntity(userDTO);
        userRepository.save(user);
        return UserMapper.INSTANCE.entityToDTO(user);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        userToUpdate.setUsername(userDTO.username());
        userToUpdate.setId(userDTO.id());
        userToUpdate.setEmail(userDTO.email());
        userToUpdate.setUsername(userDTO.username());
        return UserMapper.INSTANCE.entityToDTO(userRepository.save(userToUpdate));
    }

    @Override
    public void delete(Long id) {
        User userToDelete = userRepository.findById(id)
                .orElseThrow(ResourceNotAbleToDeleteException::new);
        userRepository.delete(userToDelete);
    }

    @Override
    public UserDTO getById(Long id) {
        return UserMapper.INSTANCE.entityToDTO(
                userRepository.findById(id).
                        orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapper.INSTANCE::entityToDTO)
                .toList();
    }
}
