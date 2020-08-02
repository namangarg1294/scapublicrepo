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
public class EmployeeDetails {

    public EmployeeDetails() {
    }

    public EmployeeDetails(String emp_name, String emp_phone_no, String password, int emp_id) {
        this.emp_name = emp_name;
        this.emp_phone_no = emp_phone_no;
        this.password = password;
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone_no() {
        return emp_phone_no;
    }

    public void setEmp_phone_no(String emp_phone_no) {
        this.emp_phone_no = emp_phone_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }
    String emp_name,emp_phone_no,password;
int emp_id;
int centre_id;
String batchType;

    public String getBatchType() {
        return batchType;
    }

    public void setBatchType(String batchType) {
        this.batchType = batchType;
    }

    public int getCentre_id() {
        return centre_id;
    }

    public void setCentre_id(int center_id) {
        this.centre_id = center_id;
    }
}
