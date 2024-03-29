package br.com.dicasdeumdev.apitestes.services.impl;

import br.com.dicasdeumdev.apitestes.domain.User;
import br.com.dicasdeumdev.apitestes.domain.dto.UserDTO;
import br.com.dicasdeumdev.apitestes.repositories.UserRepository;
import br.com.dicasdeumdev.apitestes.services.UserService;
import br.com.dicasdeumdev.apitestes.services.exceptions.DataIntegratyViolationException;
import br.com.dicasdeumdev.apitestes.services.exceptions.ObjectNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return repository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    private void findByEmail(UserDTO dto) {
        Optional<User> user = repository.findByEmail(dto.getEmail());
        if (user.isPresent() && !user.get().getId().equals(dto.getEmail())) {
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
