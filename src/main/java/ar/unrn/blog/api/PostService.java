package ar.unrn.blog.api;

import ar.unrn.blog.domain.dto.AuthorPostCountDTO;
import ar.unrn.blog.domain.dto.PostDTO;
import ar.unrn.blog.domain.dto.ShortPostDTO;

import java.util.List;

public interface PostService {

    PostDTO findPostById(String id);
    List<ShortPostDTO> findLatestPosts();
    List<PostDTO> findPostByAuthorName(String name);
    List<PostDTO> findPostByText(String text);
    List<AuthorPostCountDTO> findAuthorsAndNumberOfPost();
    void createPost(PostDTO newPost);
}
