/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.RoomDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import model.Room;
import model.User;
import view.Room.Post_Form;

/**
 *
 * @author Admin
 */
public class PostController {
    private final Post_Form post;
    private final RoomDAO roomDAO;
    private List<Room> allRoom;
    private final User user;
    public PostController(Post_Form post_Form,User user)
    {
        this.post = post_Form;
        roomDAO = new RoomDAO();
        post.setActionListenerforlbl2M(new clickforlbl2M());
        post.setActionListenerforlbl2to4(new clickforlbl2to4());
        post.setActionListenerforlbl4to7(new clickforlbl4to7());
        post.setActionListnerforlbl7(new clickforlbl7());
        post.setActionListenerforlblAllPrice(new clickForlblAllPrice());
        post.setMouseEventForJlist(new clickforItem());
        this.user = user;
        renderList(0, 0,0);
    }
    public Post_Form renderForm()
    {
        return post;
    }
    private void renderList(int codePrice, int codeLocation,int codeCategory)
    {
        allRoom = roomDAO.getAllRoom("TRỐNG");
        filterPrice(allRoom, codePrice);
        filterLocation(allRoom, codeLocation);
        filterCategory(allRoom, codeCategory);
        post.initList(allRoom);
        post.initOverviewData(roomDAO.getDataRoom());
        post.validate();
        post.repaint();
    }
    public void filterPrice(List<Room> list, int codePrice)
    {
        switch (codePrice){  
            case 1 -> list.removeIf(r -> r.getPrices() > 2000000);    // code price 1
            case 2 -> list.removeIf(r -> r.getPrices() < 2000000 || r.getPrices() > 4000000); // 2
            case 3 -> list.removeIf(r -> r.getPrices() < 4000000 || r.getPrices() > 7000000); // 3
            case 4 -> list.removeIf(r -> r.getPrices() < 7000000); // 4
        }
    }
    public void filterLocation(List<Room> list, int codeLocation)
    {
        list.removeIf(r -> { 
            return r.getLocation().getDistrict().trim()
                    .equalsIgnoreCase(post.listLocation[codeLocation]);
                    });
    }
    public void filterCategory(List<Room> list, int Category)
    {
        list.removeIf(r -> Category*100==r.getCategoryId());
    }
    class clickforlbl2M implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           //
            post.addToFilter("2 Triệu");
            renderList(1, post.getSelectedCmbLocation(), post.getSelectedcmbCategory());
        }
    }
    class clickforlbl2to4 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           post.addToFilter("2 - 4 Triệu");
            renderList(2,post.getSelectedCmbLocation(), post.getSelectedcmbCategory());
        }
    }
    class clickforlbl4to7 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("4 - 7 Triệu");
            renderList(3,post.getSelectedCmbLocation(), post.getSelectedcmbCategory());
        }
        
    }
    class clickforlbl7 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("Trên 7 triệu");
            renderList(4,post.getSelectedCmbLocation(), post.getSelectedcmbCategory());
        }
    }
    class clickForlblAllPrice implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            post.addToFilter("Tất cả");
            renderList(5,post.getSelectedCmbLocation(), post.getSelectedcmbCategory());
        }
    }
    class clickforItem extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent evt)
        {
            java.awt.EventQueue.invokeLater(() -> {
                RoomDetailController roomdetail = new RoomDetailController(allRoom.get(post.getIndexofSelectedItem()),user);
            });
       }
    }
}
