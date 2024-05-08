/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ContractDAO;
import DAO.LandLordDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private LandLordView view;
    private LandLordDAO DAO;
    private ContractDAO contractDAO;
    private LandLord model;
    private RequestCreateRoom rqView;
    private ArrayList<ContractLandLordDetail> list;
    public LandlordController(LandLordView view,LandLord model){
        this.view = view;
        this.model = model;
        DAO = new LandLordDAO();
        contractDAO = new ContractDAO();
        view.setActionListenerForBtnCreateRoom(new ClickCreateRoom());
        view.setActionListenerFortablebtn(new ClickRequestDeleteContract(), new ClickRequestExtendContract());
        initDataTable();
    }
    public void initDataTable(){
       list = contractDAO.getContractLandLordDetail(model.getCCCD());
       view.initTable(list);
    }
    public LandLordView render(){
        return view;
    }
    class ClickCreateRoom implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             rqView = new RequestCreateRoom();
             rqView.setActionlistenerforBtnRequest(new ClickRequest());
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
              boolean result = contractDAO.CreateNewLandLordContractAndRoom(contract, LandlordRoom);
              if (result) {
                  JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thành công, chúng tôi sẽ gặp bạn sau 5 ngày nữa");
              }
              else
                  JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thất bại, vui lòng thử lại sau!!");
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
//               boolean rs =  contractDAO.UpdateStatusContract(list.get(view.getSelectedItemTable()).getID(), "GIA HẠN");
//                if (rs){
//                    JOptionPane.showMessageDialog(rqView, "Đã yêu cầu gia hạn thành công");
//                }
//                else{
//                    JOptionPane.showMessageDialog(rqView, "Bạn đã yêu cầu thất bại, vui lòng thử lại sau!!");
//                }
            }
    }
}
