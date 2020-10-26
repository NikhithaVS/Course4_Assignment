package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String submitComment(@PathVariable("imageId") int imageId, @RequestParam String comment, Comment commentObj, HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggeduser");
        commentObj.setCreatedDate(LocalDate.now());
        commentObj.setText(comment);
        Image image = imageService.getImage(imageId);
        commentObj.setImage(image);
        commentObj.setUser(user);

        commentService.saveComment(commentObj);
        return "redirect:/images/" + imageId + "/" + image.getTitle();
    }
}
