/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ibrahim
 */
public class TextAreaRenderer extends JTextArea
    implements TableCellRenderer {

  JTextArea textArea;
    
  public TextAreaRenderer() {      
      
      this.setSize(500,20);
      setLineWrap(true);
      setWrapStyleWord(true);     
      
    
  }

  public Component getTableCellRendererComponent(JTable jTable,
      Object obj, boolean isSelected, boolean hasFocus, int row,
      int column) {       
       setText((String)obj);
    return this;
  }
}