/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.LandLordDAO;
import DAO.RoomDAO;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.ContractLandLordDetail;
import model.Contract_Landlord;
import model.LandLord;
import model.Room;
import view.LandLordView;
import view.RequestCreateRoom;

/**
 *
 * @author Admin
 */
public class LandlordController {
    private final LandLordView view;
    private final LandLordDAO DAO;
    private final ContractDAO contractDAO;
    private final RoomDAO roomDAO;
    private final LandLord model;
    private RequestCreateRoom rqView;
    private ArrayList<ContractLandLordDetail> list;
    private ArrayList<String> listImage;
    public LandlordController(LandLordView view,LandLord model){
        this.view = view;
        this.model = model;
        DAO = new LandLordDAO();
        contractDAO = new ContractDAO();
        roomDAO = new RoomDAO();
        view.setActionListenerForBtnCreateRoom(new ClickCreateRoom());
        view.setActionListenerFortablebtn(new ClickRequestDeleteContract(), new ClickRequestExtendContract());
        initDataTable();
    }
    private void initDataTable(){
       list = contractDAO.getContractLandLordDetail(model.getCCCD());
       view.initTable(list);
    }
    public LandLordView render(){
        return view;
    }
    class ClickCreateRoom implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             listImage = new ArrayList<>();
             rqView = new RequestCreateRoom();
             rqView.setActionlistenerforBtnRequest(new ClickRequest());
             rqView.setActionListenerUploadImage(new MouseClickUploadImage());
             java.awt.EventQueue.invokeLater(() -> {
               rqView.setDefaultCloseOperation(RequestCreateRoom.DISPOSE_ON_CLOSE);
               rqView.setVisible(true);
            });
        }
    }
    class ClickRequest implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
              Contract_Landlord contract = rqView.CreateContract_Landlord(model);
              Room LandlordRoom = rqView.CreateRoomInfo();
              int RoomID = contractDAO.CreateNewLandLordContractAndRoom(contract, LandlordRoom);
              if (RoomID != 0) {
                  JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thành công, chúng tôi sẽ gặp bạn sau 5 ngày nữa");
              }
              else
                  JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thất bại, vui lòng thử lại sau!!");
              for(String path : listImage){
                  roomDAO.InsertImage(RoomID,path.substring(4).replace("\\", "/"));
              }
              rqView.dispose();
        }
    }
    class ClickRequestDeleteContract implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
           boolean rs =  contractDAO.UpdateStatusContract(list.get(view.getSelectedItemTable()).getID(), "XÓA");
           if (rs){
               JOptionPane.showMessageDialog(rqView, "Đã yêu cầu xóa thành công");
           }
           else{
               JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thất bại, vui lòng thử lại sau!!");
           }
        }
    }
    class ClickRequestExtendContract implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("abc");
               boolean rs =  contractDAO.UpdateStatusContract(list.get(view.getSelectedItemTable()).getID(), "GIA HẠN");
                if (rs){
                    JOptionPane.showMessageDialog(rqView, "Đã yêu cầu gia hạn thành công");
                }
                else{
                    JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thất bại, vui lòng thử lại sau!!");
                }
            }
    }
    class MouseClickUploadImage extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            FileDialog fileDialog = new FileDialog(rqView);
            fileDialog.setFilenameFilter((File dir, String name) -> name.endsWith(".png") || name.endsWith(".jpg") // Bộ lọc chỉ cho phép chọn các file có phần mở rộng là .txt
            );
            fileDialog.setLocationRelativeTo(null);
            fileDialog.setVisible(true);
                Path desPath = Paths.get("src","icon","ROOM_IMAGE",fileDialog.getFile());
                int i = 1;
                while(Files.exists(desPath))
                {
                    desPath = Paths.get("src","icon","ROOM_IMAGE",fileDialog.getFile().substring(0,fileDialog.getFile().lastIndexOf('.'))+"_"+
                            String.valueOf(i)+fileDialog.getFile().substring(fileDialog.getFile().lastIndexOf('.')));
                    i++;
                }
                try{
                Files.copy(Paths.get(fileDialog.getDirectory(),fileDialog.getFile()),desPath,StandardCopyOption.REPLACE_EXISTING);
                } catch(IOException ex){
                    ex.printStackTrace();
                    return;
                }
                listImage.add(desPath.toString());
                rqView.UpdateStatusField(desPath.getFileName().toString(),new RemoveFile());
            }
        }
    class RemoveFile implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             
        }
        
    }
}
