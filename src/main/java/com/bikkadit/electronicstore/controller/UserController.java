package com.bikkadit.electronicstore.controller;

import com.bikkadit.electronicstore.apiResponce.ApiResponse;
import com.bikkadit.electronicstore.apiResponce.PageableResponse;
import com.bikkadit.electronicstore.constant.AppConstant;
import com.bikkadit.electronicstore.payload.UserDto;
import com.bikkadit.electronicstore.service.FileService;

import com.bikkadit.electronicstore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    /**
     * @apiNote createUser
     * @since 1.0
     * @param userDto
     * @return UserDto
     */

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Initiating request for create a user");
        UserDto saveUser1 = this.userService.saveUser(userDto);
        logger.info("Complete request for create a user");
        return new ResponseEntity<>(saveUser1, HttpStatus.CREATED);
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Long userId) {
        logger.info("Initiating request for update a user for {userId}", userId);
        UserDto updateUser = this.userService.updateUser(userDto, userId);
        logger.info("Complete request for update a user for {userId}", userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        logger.info("Initiating request for get a user for {userId}", userId);
        UserDto getUser = this.userService.getUser(userId);
        logger.info("Complete request for get a user for {userId}", userId);
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<PageableResponse<UserDto>> getAllUser
            (@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) int pageNumber,
             @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) int pageSize,
             @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY_USER_ID, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {

        logger.info("Initiating request for get all user");
        PageableResponse<UserDto> getAllUser = this.userService.getAllUser(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Complete request for get all user");
        return new ResponseEntity<>(getAllUser, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        logger.info("Initiating request for get user by {email}", email);
        UserDto getUserByEmail = this.userService.getUserByEmail(email);
        logger.info("Complete request for get user by {email}", email);
        return new ResponseEntity<>(getUserByEmail, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        logger.info("Initiating request for delete user by userId", userId);
        this.userService.deleteUser(userId);
        logger.info("Complete request for delete user by userId", userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstant.USER_DELETE, true), HttpStatus.OK);
    }


    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keyword) {
        logger.info("Completion request for delete user by {keyword}", keyword);
        List<UserDto> searchUser = this.userService.searchUser(keyword);
        logger.info("Complete request for delete user by {keyword}", keyword);
        return new ResponseEntity<>(searchUser, HttpStatus.OK);
    }

    @PostMapping("/user/image/upload/{userId}")
    public ResponseEntity<UserDto> uploadPostImage(@RequestParam("image") MultipartFile image,
                                                   @PathVariable Long userId) throws IOException {
        logger.info("Initiating requst for uploading image by using {userId}", userId);
        String filename = this.fileService.uploadImage(path, image);
        UserDto userDto = this.userService.getUser(userId);
        userDto.setImageName(filename);
        UserDto updatePost = this.userService.updateUser(userDto, userId);
        logger.info("Complete step for uploading image by using postId", userId);
        return new ResponseEntity<UserDto>(updatePost, HttpStatus.OK);
    }

    // method to serve files

    @GetMapping(value = "/user/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
            throws IOException {

        logger.info("Initiating request for get image by using {imageName}", imageName);
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        //logger.info("Completion step for get image by using imageName");
        StreamUtils.copy(resource, response.getOutputStream());
        logger.info("Complete request for get image by using {imageName}", imageName);
    }

}
