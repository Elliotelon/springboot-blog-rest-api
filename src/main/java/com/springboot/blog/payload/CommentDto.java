package com.springboot.blog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    //not null, not empty
    @NotEmpty(message = "비어있을 수 없습니다.")
    private String name;

    //not null, not empty
    //email validation
    @NotEmpty(message = "비어있을 수 없습니다.")
    @Email
    private String email;

    //not null, not empty
    //10글자 이상 입력하세요.
    @NotEmpty
    @Size(min = 10, message = "10글자이상 입력하세요.")
    private String body;
}
