/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.awt.AWTKeyStroke;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Comp8
 */
public class EnrollToMainframe {
    public static void execute(JFrame j){
        System.out.println("running enrolltomain class");
        JOptionPane optionPane = new JOptionPane("\n\nWould you like to take fees as well?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        JDialog dialog = optionPane.createDialog("Confirm Payment?");
        Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
        focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
        dialog.setFocusTraversalKeys(0, focusTraversalKeys);
        dialog.setVisible(true);
        dialog.dispose();


        Integer dialogResult = (Integer) optionPane.getValue();
        if(dialogResult==null || dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.CLOSED_OPTION){
          // Saving code here

            return;
        }
    }
}
