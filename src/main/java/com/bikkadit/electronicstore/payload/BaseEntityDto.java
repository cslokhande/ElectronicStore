package com.bikkadit.electronicstore.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntityDto {

    @Column(name="Create_Date", updatable = false)
    @CreationTimestamp
    private LocalDate createDate;

    @Column(name="Update_Date", insertable = false)
    @UpdateTimestamp
    private LocalDate updateDate;

    @Column(name = "IsActive_Switch")
    private String isactive;
}
