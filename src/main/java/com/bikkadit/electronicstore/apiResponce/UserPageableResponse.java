package com.bikkadit.electronicstore.apiResponce;

import com.bikkadit.electronicstore.payload.UserDto;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageableResponse<V> {

    private List<UserDto> content;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPages;

    private boolean lastPage;


}
