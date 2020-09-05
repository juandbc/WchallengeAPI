package com.jdbc.wchallenge_api.service;

import com.jdbc.wchallenge_api.model.*;
import com.jdbc.wchallenge_api.repository.UserRepository;
import com.jdbc.wchallenge_api.repository.UserWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Juan David Bermudez
 * @version 1.0
 */
@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  private AlbumService albumService;
  @Autowired
  private PostService postService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private PhotoService photoService;

  @Autowired
  public UserService(UserWebRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(int id) {
    return userRepository.findById(id);
  }

  public List<Comment> findUserComments(int id) {
    List<Comment> userComments = new ArrayList<>();
    List<Post> userPosts = postService.findByUser(id);

    userPosts.forEach(post ->
            userComments.addAll(commentService.findAll()
                    .stream()
                    .filter(comment -> comment.getPostId() == post.getId())
                    .collect(Collectors.toList()))
    );

    return userComments;
  }

  public List<Photo> findUserPhotos(int id) {
    List<Photo> userPhotos = new ArrayList<>();
    List<Album> userAlbums = albumService.findByUser(id);

    userAlbums.forEach(album ->
            userPhotos.addAll(photoService.findAll()
                    .stream()
                    .filter(photo -> photo.getAlbumId() == album.getId())
                    .collect(Collectors.toList()))
    );
    return userPhotos;
  }
}
