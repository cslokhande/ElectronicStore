package com.bikkadit.electronicstore.service.impl;

import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.exception.ResourceNotFoundException;
import com.bikkadit.electronicstore.helper.Helper;
import com.bikkadit.electronicstore.model.User;
import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.payload.UserDto;
import com.bikkadit.electronicstore.repository.UserRepository;
import com.bikkadit.electronicstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

   @Autowired
    private UserRepository userRepo;

   @Autowired
   private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        logger.info("Initiating dao call to save User");
        User user = this.modelMapper.map(userDto, User.class);
        user.setIsactive(AppConstant.YES);
        User save = this.userRepo.save(user);
        logger.info("Complete dao call to save User");
        return  this.modelMapper.map(save, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
        logger.info("Initiating dao call to update user by userId{}", userId);
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());
        User updateUser = userRepo.save(user);
        logger.info("Complete dao call to update user by userId{}", userId);
        return this.modelMapper.map(updateUser, UserDto.class);
    }

    @Override
    public void deleteUser(Long userId) {
        logger.info("Initiating dao call to delete user by userId{}", userId);
        User deleteUser = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        deleteUser.setIsactive(AppConstant.NO);
        userRepo.save(deleteUser);
        logger.info("Complete dao call to delete user by userId{}", userId);
    }

    @Override
    public UserDto getUser(Long userId) {
        logger.info("Initiating dao call to get all user");
        User user =  this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("Complete dao call to get all user");
        return this.UserToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        logger.info("Initiating dao call to get user by email{}", email);
        User userEmail = this.userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("Complete dao call to get user by email{}", email);
        return this.UserToDto(userEmail);
    }
    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        logger.info("Initiating dao call to get all user");

//        if (sortDir.equalsIgnoreCase("desc")) {
//           Sort sort = Sort.by(sortBy).descending();
//        } else {
//            Sort sort = Sort.by(sortBy).ascending();
//        }
//
// //     The above if_else statement can write as bellow by using
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageSize, pageNumber, sort);
        Page<User> userPage = this.userRepo.findAll(p);
        PageableResponse<UserDto> response = Helper.getPageableResponse(userPage,UserDto.class);
        logger.info("Initiating dao call to get all user");
        return response;


    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        logger.info("Initiating dao call to get user by using any keyword");
        List<User> byNameContaining = this.userRepo.findByNameContaining(keyword);
        List<UserDto> dtoList = byNameContaining.stream().map((user) -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        logger.info("Completion dao call to get user by using any keyword");
        return dtoList;
    }

    private User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto UserToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return  userDto;
    }
}
