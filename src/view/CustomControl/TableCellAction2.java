/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.CustomControl;

import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Admin
 */
public class TableCellAction2 extends DefaultCellEditor{
    private ActionListener listener;
    private ActionListener listener2;
    private ActionListener listener3;
    public TableCellAction2(ActionListener listener1,ActionListener listener2,ActionListener listener3)
      {
          super(new JCheckBox());
          this.listener = listener1;
          this.listener2 = listener2;
          this.listener3 = listener3;
      }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Action2Panel actionPanel = new Action2Panel();
        actionPanel.setBackground(table.getSelectionBackground());
        actionPanel.setActionlistenerForbtnDelete(listener);
        actionPanel.setActionListenerForbtnExtend(listener2);
        actionPanel.setActionListenerForbtnDetail(listener3);
        return actionPanel;
    }
      
}