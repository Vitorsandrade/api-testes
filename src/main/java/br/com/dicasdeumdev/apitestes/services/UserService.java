package br.com.dicasdeumdev.apitestes.services;


import br.com.dicasdeumdev.apitestes.domain.User;
import br.com.dicasdeumdev.apitestes.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create (UserDTO obj);

    User update (UserDTO userDTO);

    void delete (Integer id);
}