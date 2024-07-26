package com.example.blogAngular.entity;
//thư viện JPA cung cấp chú thích và các lớp cần thiết để ánh xạ lớp Java thành bảng CSDL
import jakarta.persistence.*;
// Thư viện Lombok tự động sinh ra phương thức getter, setter, toString(), equals(), và hashCode() cho lớp này.
import lombok.Data;

import java.util.Date;
//thư viện cung cấp List
import java.util.List;

//@Entity đánh dấu lớp Post là 1 thực thể JPA,tức là trở thành 1 bảng CSDL
@Entity
//của LomBok
@Data
public class Post {
    //id là khoá chính
    @Id
    // auto increment khoá chính
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 5000)
    private String content;

    private String postedBy;

    private String img;

    private Date date;

    private int likeCount;

    private int viewCount;

    private List<String> tags;
}
