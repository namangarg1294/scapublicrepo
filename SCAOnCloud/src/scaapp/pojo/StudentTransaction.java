/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.pojo;

/**
 *
 * @author Comp8
 */
public class StudentTransaction {

    @Override
    public String toString() {
            return transactionId + "        " + employeeId + "      " + centreId + "        " + grossAmount + "     " + dateOfPayement ;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getCentreId() {
        return centreId;
    }

    public void setCentreId(int centreId) {
        this.centreId = centreId;
    }

    public int getPayementModeId() {
        return PayementModeId;
    }

    public void setPayementModeId(int PayementModeId) {
        this.PayementModeId = PayementModeId;
    }

   public String getDateOfPayement() {
        return dateOfPayement;
    }

    public void setDateOfPayement(String dateOfPayement) {
        this.dateOfPayement = dateOfPayement;
    }

    public String getChequeId() {
        return chequeId;
    }

    public void setChequeId(String chequeId) {
        this.chequeId = chequeId;
    }

   
   
    
   String transactionId;
   String studentId;
   int employeeId;
   String batchId;
   int centreId;
   int PayementModeId;
   double grossAmount;

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getSgstAmount() {
        return sgstAmount;
    }

    public void setSgstAmount(double sgstAmount) {
        this.sgstAmount = sgstAmount;
    }

    public double getCgstAmount() {
        return cgstAmount;
    }

    public void setCgstAmount(double cgstAmount) {
        this.cgstAmount = cgstAmount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   double netAmount;
   double sgstAmount;
   double cgstAmount;
   String dateOfPayement;
   String chequeId;
   String empName;
   String email;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
           
           
}
