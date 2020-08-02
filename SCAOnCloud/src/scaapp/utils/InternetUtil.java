/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Comp10
 */
public class InternetUtil {
    public static boolean check() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        System.out.println(check());
    }
}
