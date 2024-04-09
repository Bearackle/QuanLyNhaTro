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
        post.setActionListenerforlbl2to4(new clickforlbl2to4());
        post.setActionListenerforlbl4to7(new clickforlbl4to7());
        post.setActionListnerforlbl7(new clickforlbl7());
        post.setActionListenerforlblAllPrice(new clickForlblAllPrice());
       
    }
    public Post_Form renderForm()
    {
        List<Room> allRoom = roomDAO.getAllRoom();
        post.initList(allRoom);
        post.initOverviewData(roomDAO.getDataRoom());
        return post;
    }
    class clickforlbl2M implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           //
            post.addToFilter("2 Triệu");
        }
    }
    class clickforlbl2to4 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           post.addToFilter("2 - 4 Triệu");
        }
    }
    class clickforlbl4to7 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("4 - 7 Triệu");
        }
        
    }
    class clickforlbl7 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("Trên 7 triệu");
        }
    }
    class clickForlblAllPrice implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("Tất cả");
        }
    }
}
