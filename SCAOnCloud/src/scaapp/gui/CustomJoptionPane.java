/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.gui;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Comp8
 */
public class CustomJoptionPane {
    public static void main(String[] args) {
         ImageIcon icon = new ImageIcon("d:/loading.gif");
       
          final JOptionPane optionPane = new JOptionPane( "Loading.....", JOptionPane.NO_OPTION, JOptionPane.NO_OPTION,icon, new Object[]{}, icon);
         
         //final JOptionPane optionPane = new JOptionPane("Hello world", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);

final JDialog dialog = new JDialog();
dialog.setTitle("Message");
dialog.setModal(true);

dialog.setContentPane(optionPane);
dialog.setLocationRelativeTo(null);
dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
dialog.pack();
dialog.enable(false);

//create timer to dispose of dialog after 5 seconds
Timer timer = new Timer(5000, new AbstractAction() {
    @Override
    public void actionPerformed(ActionEvent ae) {
        dialog.dispose();
        dialog.setVisible(true);
    }
});
timer.setRepeats(false);//the timer should only go off once

//start timer to close JDialog as dialog modal we must start the timer before its visible
timer.start();

dialog.setVisible(true);
    }
}
