/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.gui;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Comp8
 */
public class GifTest {
    public static void main(String[] args) {
        ImageIcon icon = new ImageIcon("d:/loading.gif");
        JOptionPane.showMessageDialog(null, "Cerrando sesión...", "About", JOptionPane.INFORMATION_MESSAGE, icon);
         JOptionPane.showOptionDialog(null, "Hello","Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, icon);
    }
 
}
