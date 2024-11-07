package ar.unrn.blog.web;

import ar.unrn.blog.api.PostService;
import ar.unrn.blog.domain.dto.AuthorPostCountDTO;
import ar.unrn.blog.domain.dto.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/")
public class BlogController {

    @Autowired
    private PostService postService;

    @GetMapping("byauthor")
    List<AuthorPostCountDTO> getAuthorsAndNumberOfPost() {
        return this.postService.findAuthorsAndNumberOfPost();
    }

    @GetMapping("/search/{text}")
    List<PostDTO> getPostsByText(@PathVariable String text) {
        return this.postService.findPostByText(text);
    }
}