/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.dao;

import java.awt.AWTKeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Comp8
 */
public class NavigatioJOption {
    public static void main(String[] args) {
        JOptionPane optionPane = new JOptionPane("File haven't save yet." +
            " \n Are you want to save the file?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
    JDialog dialog = optionPane.createDialog("Confirm Payment?");
    Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
    focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
    dialog.setFocusTraversalKeys(0, focusTraversalKeys);
    dialog.setVisible(true);
    dialog.dispose();
    
        
    Integer option = (Integer) optionPane.getValue();
    if(option==null)
        System.out.println("nulla");
    }
}
