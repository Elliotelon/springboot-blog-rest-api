package com.springboot.blog.controller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private PostService postService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){

        PostDto newPost = postService.createPost(postDto);

        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PostResponse allPosts = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    /**URI Path*/
//    @GetMapping(value = "/api/v1/posts/{id}")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id){
//
//        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/v2/posts/{id}", params = "version=2")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable Long id){
//
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }
//
    /**query parameters*/
//    @GetMapping(value = "/api/posts/{id}", params = "version=1")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id){
//
//        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/posts/{id}", params = "version=2")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable Long id){
//
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }

    /**custom headers*/
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=1")
//    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id){
//
//        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/api/posts/{id}", headers = "X-API-VERSION=2")
//    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable Long id){
//
//        PostDto postDto = postService.getPostById(id);
//        PostDtoV2 postDtoV2 = new PostDtoV2();
//        postDtoV2.setId(postDto.getId());
//        postDtoV2.setTitle(postDto.getTitle());
//        postDtoV2.setDescription(postDto.getDescription());
//        postDtoV2.setContent(postDto.getContent());
//        List<String> tags = new ArrayList<>();
//        tags.add("Java");
//        tags.add("Spring boot");
//        tags.add("AWS");
//        postDtoV2.setTags(tags);
//        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
//    }

    /**content negotiation*/
    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.javaguides.v1+json")
    public ResponseEntity<PostDto> getPostByIdV1(@PathVariable Long id){

        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/api/posts/{id}", produces = "application/vnd.javaguides.v2+json")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable Long id){

        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);
        return new ResponseEntity<>(postDtoV2, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Long id){

        return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }

    // Get Posts by Category REST API
    @GetMapping("/api/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable(value = "id") Long categoryId){

        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
