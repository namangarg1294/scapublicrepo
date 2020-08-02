/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.gui;

import scaapp.pojo.EmployeeDetails;
import com.alee.laf.WebLookAndFeel;
import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableRow;
import scaapp.dao.RegistrationDao;
import scaapp.dao.StudentDetailDao;
import scaapp.dao.StudentTransactionDao;
import scaapp.pojo.StudentDetail;
import scaapp.pojo.StudentTotalFeeDetail;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.InternetUtil;
import scaapp.utils.OldBatchDetector;

/**
 *
 * @author Comp10
 */
public class EditTransactionFrame extends javax.swing.JFrame {

    /**
     * Creates new form ViewStudentDetails
     */
    StudentDetail sd;
     DefaultTableModel model;
     EmployeeDetails ed;
     FeeDetailsTable fdt;
     Date d=null;
     ArrayList<StudentTransaction> list=null;
    public EditTransactionFrame() {
        initComponents();
        setLocationRelativeTo(null);
        
         d=StudentTransactionDao.getDate();
        this.list=new ArrayList();
          this.model = (DefaultTableModel)jtStudentData.getModel();
         jtStudentData.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        int row = jtStudentData.rowAtPoint(evt.getPoint());
        int col = jtStudentData.columnAtPoint(evt.getPoint());
         if(d==null){
        JOptionPane.showMessageDialog(null,"Try after some time");
              return;
         }
        if(Double.parseDouble(model.getValueAt(row, 2).toString())==0.0)
        {
            JOptionPane.showMessageDialog(null,"Transaction has alerady been Zeroed");
              return;
        }
        if (row >= 0) {
          // String batch= model.getValueAt(row, 0).toString();
           String transaction= model.getValueAt(row, 0).toString();
//           String remark= model.getValueAt(row, 6).toString();
//            System.out.println(batch);
//           String studentId=studentContact.getText();
           
           
              JOptionPane optionPane = new JOptionPane(transaction+"\n\nWould you like to nullify following transaction?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
    JDialog dialog = optionPane.createDialog("Zero the transaction?");
    Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
    focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
    dialog.setFocusTraversalKeys(0, focusTraversalKeys);
    dialog.setVisible(true);
    dialog.dispose();
    Integer dialogResult = (Integer) optionPane.getValue();
      if(dialogResult==null || dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.CLOSED_OPTION){
          // Saving code here
            
              JOptionPane.showMessageDialog(null,transaction+" Transaction has not been zeroed");
              return;
        }
      else
    try {
        String message =  JOptionPane.showInputDialog(null,"Enter a message");
        if(message==null || message.equals("")){
            JOptionPane.showMessageDialog(null,"Remark is required to zero the transaction");
            return;
            
        }
            
                StudentTransactionDao.nullifyStudentTransaction(transaction);
                 StudentTransactionDao.addRemarkForNullifingTransaction(transaction,message,d.toString(),ed.getEmp_id());
                model.setValueAt(0,row,2);
            JOptionPane.showMessageDialog(null,transaction+"Transaction zeroed!!");
                //JOptionPane.showMessageDialog(null,s);
            } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null,"Some error occured try again later");
            }
        }
    }
        }); 
         TableColumn col1 = jtStudentData.getColumnModel().getColumn(2);
         col1.setCellRenderer(new CustomRenderer1());
         TableColumn col2 = jtStudentData.getColumnModel().getColumn(3);
         col2.setCellRenderer(new CustomRenderer2());
          setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
            Thread dateThread=new Thread(()->{
         
          d=StudentTransactionDao.getDate();
           
       }
       );
       dateThread.start();
        
          
          
    }
    public EditTransactionFrame(EmployeeDetails ed){
        this();
        this.ed=ed;
        employeeName2.setText(ed.getEmp_name());
         JPanel panel = (JPanel)this.getContentPane();
        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        im.put(KeyStroke.getKeyStroke("ctrl pressed B"), "cB");
        panel.getActionMap().put("cB", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
               
            }
        });
         im.put(KeyStroke.getKeyStroke("alt pressed C"), "ac");
        panel.getActionMap().put("ac", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
             startSet();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        studentId = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtStudentData = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        studentName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        studentEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        studentSem = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        studentCollage = new javax.swing.JTextField();
        studentContact = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        employeeName2 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel5.setText("Name");

        jTextField5.setEnabled(false);

        jLabel6.setText("Email");

        jTextField6.setEnabled(false);

        jLabel7.setText("Semester");

        jTextField7.setEnabled(false);

        jLabel8.setText("College");

        jTextField8.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student details - SCAOnCloud v1.0.0");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fee details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Contact number/Barcode");

        studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentIdActionPerformed(evt);
            }
        });
        studentId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                studentIdKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(studentId, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(studentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fee details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jtStudentData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jtStudentData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction Id", "Batch Id", "Fee paid", "Fee Left"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtStudentData.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(jtStudentData);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel13.setText("Name");

        studentName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentName.setEnabled(false);

        jLabel14.setText("Email");

        studentEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentEmail.setEnabled(false);

        jLabel15.setText("Semester");

        studentSem.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentSem.setEnabled(false);

        jLabel16.setText("College");

        studentCollage.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentCollage.setEnabled(false);

        studentContact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentContact.setEnabled(false);

        jLabel11.setText("Contact Number");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentContact, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(studentEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(studentName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studentSem)
                    .addComponent(studentCollage))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(studentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(studentContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(studentSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(studentCollage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "User details"));

        jLabel18.setText("You are logged in as ");

        employeeName2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        employeeName2.setForeground(new java.awt.Color(0, 153, 51));
        employeeName2.setText("Anonymous");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeName2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(employeeName2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton8.setText("Logout");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Back");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jButton9))
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIdActionPerformed

    private void studentIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentIdKeyPressed

        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER||evt.getKeyCode()==KeyEvent.VK_SPACE){
            if(!(studentId.getText().length()==10||studentId.getText().length()==12))
            {
                JOptionPane.showMessageDialog(null,"Enter Valid Phone Number or barcode");
                studentId.setText("");
                //JOptionPane.showMessageDialog(null,"Student have not filled form Yet!!");
                studentId.requestFocus();
                return;
            }
             try {
            sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
            if(sd==null)
            {
                studentId.setText("");
                JOptionPane.showMessageDialog(null,"Student have not filled form Yet!!");
                return;
            }
              System.out.println("Enter called");
//               enterfee.setEnabled(true);
//               enterfee.requestFocus(true);
               //studentId.setEnabled(false);
               // studentId.setText("");
               //sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
               sd.setStudentContact(studentId.getText().substring(0,10));
               studentName.setText(sd.getStudentName().toUpperCase());
               studentContact.setText(sd.getStudentContact());
               studentEmail.setText(sd.getStudentEmail().toUpperCase());
               studentCollage.setText(sd.getStudentCollege().toUpperCase());
               studentSem.setText(sd.getStudentSem());
               
              showDataInTable(studentId.getText().substring(0,10));
               System.out.println(sd);
                studentId.setText("");
            } catch (SQLException ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
            }
         }        
        
      
    }//GEN-LAST:event_studentIdKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         studentName.setText("");
        studentContact.setText("");
        studentEmail.setText("");
        studentCollage.setText("");
        studentSem.setText("");
        studentId.setEnabled(true);
        studentId.setText("");
        studentId.requestFocus();
        model.setRowCount(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        ProfilesSelector mf=new ProfilesSelector(ed);
        mf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EditTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditTransactionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                new EditTransactionFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel employeeName2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTable jtStudentData;
    private javax.swing.JTextField studentCollage;
    private javax.swing.JTextField studentContact;
    private javax.swing.JTextField studentEmail;
    private javax.swing.JTextField studentId;
    private javax.swing.JTextField studentName;
    private javax.swing.JTextField studentSem;
    // End of variables declaration//GEN-END:variables
private void showDataInTable(String contactNo)
    {
   
      model.setRowCount(0);
//        try
//        {
//            ArrayList<Performance> performanceList=PerformanceDAO.getAllData();
//            if(performanceList.isEmpty())
//            {
//                JOptionPane.showMessageDialog(null,"Sorrry! No STudent Has Given Any Exam Yet.","Error!",JOptionPane.ERROR_MESSAGE);
//                return;
//            }
        
         //StudentDetail sd=null;
             try {
             list=StudentTransactionDao.getStudentTransactionDetails(contactNo);
            if(list.isEmpty())
            {
                studentId.setText("");
                JOptionPane.showMessageDialog(null,"Student have not done any payement Yet!!");
                return;
            }
              System.out.println("Enter called");
               
               //studentId.setEnabled(false);
                
               //sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
               //sd.setStudentContact(studentId.getText().substring(0,10));
//               studentName.setText(sd.getStudentName().toUpperCase());
//               studentContact.setText(sd.getStudentContact());
//               studentEmail.setText(sd.getStudentEmail().toUpperCase());
//               studentCollage.setText(sd.getStudentCollege().toUpperCase());
//               studentSem.setText(sd.getStudentSem());
             // studentList.add(sd);
              
               //System.out.println(sd);
            } catch (SQLException ex) {
                Logger.getLogger(EditTransactionFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
             for(StudentTransaction st:list){
            Object[] rows=new Object[7];                                                                                                                                      // 7 indicates the number of columns of table.
           
                rows[0]=st.getTransactionId();
                rows[1]=st.getBatchId();
                rows[2]=st.getGrossAmount();
                
                
                
                
              
                model.addRow(rows);
             }
            
//        }
//        catch(SQLException e)
//        {
//            JOptionPane.showMessageDialog(null,"SQLException:"+e,"Error!",JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
    }

  public void startSet(){
     //sd=null;
        studentName.setText("");
        studentContact.setText("");
        studentEmail.setText("");
        studentCollage.setText("");
        studentSem.setText("");
       
        studentId.setText("");
        
        studentId.setEnabled(true);
        studentId.requestFocus(true);
       model.setRowCount(0);
       list.clear();
}


}
