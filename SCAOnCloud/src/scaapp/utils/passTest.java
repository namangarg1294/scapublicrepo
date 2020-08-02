/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Naman
 */
public class passTest {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter a password:");
        JPasswordField pass = new JPasswordField(10);
        panel.add(label);
        panel.add(pass);
        String[] options = new String[]{"OK", "Cancel"};
        int option = JOptionPane.showOptionDialog(null, panel, "The title",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0) // pressing OK button
        {
            char[] password = pass.getPassword();
            System.out.println("Your password is: " + new String(password));
        }
    }
 
}
