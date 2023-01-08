package com.springboot.blog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    //not null, not empty
    //2글자 이상
    @NotEmpty
    @Size(min = 2, message = "2글자 이상 입력하세요.")
    private String title;

    //not null, not empty
    //10글자 이상
    @NotEmpty
    @Size(min = 10, message = "10글자 이상 입력하세요.")
    private String description;

    //not null, not empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}
