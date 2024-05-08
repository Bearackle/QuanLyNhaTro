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
public class TableCellAction extends DefaultCellEditor{
    private ActionListener listener;
    private String img;
      public TableCellAction(ActionListener listener,String img)
      {
          super(new JCheckBox());
          this.listener = listener;
          this.img = img;
      }
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ActionPanel actionPanel = new ActionPanel();
        actionPanel.setBackground(table.getSelectionBackground());
        actionPanel.setImageForActionPanel(img);
        actionPanel.setActionListener(listener);
        return actionPanel;
    }
      
}
