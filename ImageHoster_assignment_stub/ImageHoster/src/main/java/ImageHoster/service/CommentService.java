package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByImageId(int id) {return commentRepository.getCommentsByImageId(id);}

    public Comment saveComment(Comment comment) {return commentRepository.saveComment(comment);}

}
