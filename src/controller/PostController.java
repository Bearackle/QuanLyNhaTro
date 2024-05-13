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
    private User user;
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
        renderList(0, "Tất cả", "Tất cả");
    }
    public Post_Form renderForm()
    {
        return post;
    }
    private void renderList(int codePrice, String codeLocation,String codeCategory)
    {
        allRoom = roomDAO.getAllRoom("TRỐNG");
        filterPrice(allRoom, codePrice);
        filterCategory(allRoom, codeLocation);
        filterCategory(allRoom, codeCategory);
        post.initList(allRoom);
        post.initOverviewData(roomDAO.getDataRoom());
    }
    public void filterPrice(List<Room> list, int codePrice)
    {
        switch (codePrice){
            case 1 -> list.removeIf(r -> r.getPrices() > 2000000);
            case 2 -> list.removeIf(r -> r.getPrices() < 2000000 && r.getPrices() > 4000000);
            case 3 -> list.removeIf(r -> r.getPrices() < 4000000 && r.getPrices() > 7000000);
            case 4 -> list.removeIf(r -> r.getPrices() < 7000000);
            default -> list.removeIf(r -> r.getPrices() < 0);
        }
    }
    public void filterLocation(List<Room> list, String codeLocation)
    {
        list.removeIf(r -> r.getLocation().getDistrict().equalsIgnoreCase(codeLocation));
    }
    public void filterCategory(List<Room> list, String Category)
    {
        list.removeIf(r -> {int temp = r.getCategoryId()/100;
            return Category.contains(String.valueOf(temp));
            });
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
    class clickforItem extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent evt)
        {
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() 
            {
               RoomDetailController roomdetail = new RoomDetailController(allRoom.get(post.getIndexofSelectedItem()),user); 
            }
        });
       }
    }
}
