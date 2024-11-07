package ar.unrn.blog.web;

import ar.unrn.blog.api.PostService;
import ar.unrn.blog.domain.dto.PostDTO;
import ar.unrn.blog.domain.dto.ShortPostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/latest")
    List<ShortPostDTO> findLatestPosts(){
        return postService.findLatestPosts();
    }

    @GetMapping("/{id}")
    List<PostDTO> findPostById(@PathVariable String id){

        return Collections.singletonList(this.postService.findPostById(id));
    }

    @GetMapping("/author/{nombreautor}")
    List<PostDTO> findPostByAuthorName(@PathVariable String nombreautor){
        return this.postService.findPostByAuthorName(nombreautor);
    }

    @PostMapping("/create")
    void createPost(@RequestBody PostDTO newPost){
        this.postService.createPost(newPost);
    }
}
