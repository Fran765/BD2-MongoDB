package ar.unrn.blog.services;

import ar.unrn.blog.api.PostService;
import ar.unrn.blog.domain.dto.AuthorPostCountDTO;
import ar.unrn.blog.domain.dto.PostDTO;
import ar.unrn.blog.domain.dto.ShortPostDTO;
import ar.unrn.blog.repositories.PostRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO findPostById(String id) {

        return PostDTO.fromDocument(postRepository.findPostById(id));
    }

    @Override
    public List<ShortPostDTO> findLatestPosts() {

        List<Document> postsList = this.postRepository.findLatestPosts();

        return postsList.stream()
                .map(ShortPostDTO::fromDocument)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostByAuthorName(String name) {

        List<Document> postList = this.postRepository.findPostsByAuthorName(name);

        return postList.stream()
                .map(PostDTO::fromDocument)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostByText(String text) {

        List<Document> postList = this.postRepository.findPostByText(text);

        return postList.stream()
                .map(PostDTO::fromDocument)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorPostCountDTO> findAuthorsAndNumberOfPost() {
         return this.postRepository.findAuthorsAndNumberOfPost().stream()
                 .map(AuthorPostCountDTO::fromDocument)
                 .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDTO newPost) {
        this.postRepository.createPost(newPost.getTitle(),
                newPost.getText(),
                newPost.getTag(),
                newPost.getResume(),
                newPost.getRelatedLinks(),
                newPost.getAuthor(),
                new Date()
        );
    }
}
