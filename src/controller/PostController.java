/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Room;
import view.Post_Form;

/**
 *
 * @author Admin
 */
public class PostController {
    private Post_Form post;
    private RoomDAO roomDAO;
    public PostController(Post_Form post_Form)
    {
        this.post = post_Form;
        roomDAO = new RoomDAO();
        post.setActionListenerforlbl2M(new clickforlbl2M());
    }
    public Post_Form renderForm()
    {
        List<Room> allRoom = roomDAO.getAllRoom();
        post.initList(allRoom);
        return post;
    }
    class clickforlbl2M implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("<2");
        }
    }
}
