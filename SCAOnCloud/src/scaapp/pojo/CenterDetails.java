/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

/**
 *
 * @author Lenovo
 */
public class CenterDetails {

    public CenterDetails(String centre_name, String centre_address, String centre_phone_no, int centre_incharge) {
        this.centre_name = centre_name;
        this.centre_address = centre_address;
        this.centre_phone_no = centre_phone_no;
        this.centre_incharge = centre_incharge;
    }

    public String getCentre_name() {
        return centre_name;
    }

    public void setCentre_name(String centre_name) {
        this.centre_name = centre_name;
    }

    public String getCentre_address() {
        return centre_address;
    }

    public void setCentre_address(String centre_address) {
        this.centre_address = centre_address;
    }

    public String getCentre_phone_no() {
        return centre_phone_no;
    }

    public void setCentre_phone_no(String centre_phone_no) {
        this.centre_phone_no = centre_phone_no;
    }

    public int getCentre_incharge() {
        return centre_incharge;
    }

    public void setCentre_incharge(int centre_incharge) {
        this.centre_incharge = centre_incharge;
    }

    public CenterDetails() {
    }
    String centre_name, centre_address,centre_phone_no;
int centre_incharge;
}
