package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.BaseTest;
import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.model.User;
import com.bikkadit.electronicstore.payload.UserDto;
import com.bikkadit.electronicstore.repository.UserRepository;
import com.bikkadit.electronicstore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserServiceImplTest extends BaseTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    User user;

    User user1;

    List<User> users;

    UserDto userDto;

    @BeforeEach
    public void init() {
        userDto = UserDto.builder().name("Mohit").email("mohit@gmail.com").gender("male").password("Mohit@123")
                .imageName("abcdef.png").about("for test").build();

        user = User.builder().name("Chandu").email("chandu96@gmail.com").gender("male").password("Chandu@123")
                .imageName("abcdef.png").about("for test").build();

        user1 = User.builder().name("Ramnath").email("ram@gmail.com").gender("male").password("Ram@123")
                .imageName("pqrs.png").about("for test").build();

        users = new ArrayList<>();
        users.add(user);
        users.add(user1);
    }

    @Test
    void saveUser() {
//        Arrange
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//        Act
        UserDto user1 = userService.saveUser(userDto);
//        Assert
        Assertions.assertNotNull(user1);
        Assertions.assertEquals(userDto.getName(), user1.getName());
    }


    @Test
    void updateUser() {
        Long id = 10L;
        //Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        //Act
        UserDto updateUser = userService.updateUser(userDto, id);
        //Assert
        Assertions.assertNotNull(updateUser);
        Assertions.assertEquals(userDto.getName(), updateUser.getName());
        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(userDto, 111L));
    }

// //   If we are hard delete user then we can use code as bellow

//    @Test
//    void deleteUser() {
//        Long id = 10l;
//        //Arrange
//        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        //Act
//        userService.deleteUser(id);
//        //Assert
//        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
//        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(111l));
//    }


// //   if we use soft delete then we use code as bellow
    @Test
    void deleteUser() {
        Long id = 10l;
        //Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        //Act
        userService.deleteUser(id);
        //Assert
        Assertions.assertEquals("No", user.getIsactive());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> userService.deleteUser(111l));
    }

    @Test
    void getUser() {
        Long id = 10l;
//        Arrange
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
//        Act
        UserDto userDto1 = userService.getUser(id);
//        Assert
        Assertions.assertNotNull(userDto1);
    }

    @Test
    void getUserByEmail() {
        String mail = "cs@gmail.com";
//        Arrange
        Mockito.when(userRepository.findByEmail(mail)).thenReturn(Optional.of(user));
//        Act
        UserDto user3 = userService.getUserByEmail(mail);
//        Assert
        Assertions.assertNotNull(user3);
    }

    @Test
    void getAllUser() {
        //Arrange
        Page<User> page=new PageImpl<>(users);
        Mockito.when(userRepository.findAll((Pageable) Mockito.any())).thenReturn(page);
        //Act
        PageableResponse<UserDto> allUser = userService.getAllUser(1, 5, AppConstant.SORT_BY_USER_ID, AppConstant.SORT_DIR);
        //Assert
        Assertions.assertEquals(2, allUser.getContent().size());
    }

    @Test
    void searchUser() {
        String key= "a";
        //Arrange
        Mockito.when(userRepository.findByNameContaining(key)).thenReturn(users);
        //Act
        List<UserDto> userDtos = userService.searchUser(key);
        //Assert
        Assertions.assertNotNull(userDtos);
        Assertions.assertEquals(2,userDtos.size());
    }

}