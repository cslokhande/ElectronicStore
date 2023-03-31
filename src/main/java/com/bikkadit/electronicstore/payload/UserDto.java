package com.bikkadit.electronicstore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseEntityDto {

    private Long userId;

    @NotEmpty
    @Size(min=4, message="name minimum size is 4 character")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
   // @Pattern(regexp = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{8,}$",message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit  must be 8 char ")
    private String password;

    @NotEmpty
    private String about;

    @NotEmpty
    @Size(min=4, message="Gender minimum size is 4 character")
    private String gender;

    private String imageName;
}
