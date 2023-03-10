package br.com.dicasdeumdev.apitestes.services.impl;

import br.com.dicasdeumdev.apitestes.domain.User;
import br.com.dicasdeumdev.apitestes.domain.dto.UserDTO;
import br.com.dicasdeumdev.apitestes.repositories.UserRepository;
import br.com.dicasdeumdev.apitestes.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "vitor";
    public static final String EMAIL = "vitor@gmail.com";
    public static final String PASSWORD = "123";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto não encontrado!";
    private static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFinByIdThenReturnAnUserInstance() {
        //"mocka" o método findById da camaada de repository para que quando chamada receba o optionalUser
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        //faz a chamada do método findById da camada de serviço
        User response = service.findById(ID);

        //assegura que não passou objeto nulo
        assertNotNull(response);
        //assegura que ambos são iguais (argumentos)
        assertEquals(User.class, response.getClass());
        //assegura que ambos tem o mesmo ID
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        //"mocka" o método findById da camaada de repository para que quando chamada lance uma exception
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NÃO_ENCONTRADO));

        try {
            //chamando findById e assim lançando exception que será capturada pelo catch
            service.findById(ID);
        } catch (Exception ex){
            //confirma retorno de exception
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            //assegure que as mensagens das exceptions sejam iguais
            assertEquals(OBJETO_NÃO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        //"mocka" o método findAll da camaada de repository para que quando chamada receba uma lista de User
        when(repository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
    }

    @Test
    void whensFindAllThenReturnAnListOfUsers() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}