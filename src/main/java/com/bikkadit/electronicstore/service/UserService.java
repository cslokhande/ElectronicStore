package com.bikkadit.electronicstore.service;

import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.payload.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long userId);

    void deleteUser(Long userId);

    UserDto getUser(Long userId);

    UserDto getUserByEmail(String email);

    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    List<UserDto> searchUser(String keyword);
}
