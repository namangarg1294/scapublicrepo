/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.gui;

import scaapp.pojo.EmployeeDetails;
import com.alee.laf.WebLookAndFeel;
import java.awt.AWTKeyStroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import scaapp.dao.BatchDao;
import scaapp.dao.EmployeeDao;
import scaapp.dao.RegistrationDao;
import scaapp.dao.StudentDetailDao;
import scaapp.dao.StudentTransactionDao;
import scaapp.pojo.BatchDetailsDisplay;
import scaapp.pojo.StudentDetail;
import scaapp.pojo.StudentTransaction;
import scaapp.utils.EnrollToMainframe;
import scaapp.utils.GSTReceiptPrinter;
import scaapp.utils.GSTReceiptPrinterProjectBatch;
import scaapp.utils.InternetUtil;

/**
 *
 * @author Comp10
 */
public class BlunderFixer extends javax.swing.JFrame {
    private final BlunderFixer ens = this;
    /**
     * Creates new form EnrollNewStudent
     */
    
    int count=0;
    int EmployeeId;
    StudentDetail sd;
    boolean first=true;
    EmployeeDetails ed;
     ImageIcon icon = new ImageIcon("src\\resources\\loading.gif");
    final JOptionPane optionPane = new JOptionPane( "Loading.....", JOptionPane.NO_OPTION, JOptionPane.NO_OPTION,icon, new Object[]{}, icon);
    final JDialog dialog = new JDialog();
//     @Override
//   public void setVisible(boolean val){
//       if(ed==null){
//        
//            JOptionPane.showMessageDialog(null,"Please Login First");
//            Login lp=new Login();
//            lp.setVisible(true);
//            this.dispose();
//            //return;
//       
//          
//       }
//       else{
//           super.setVisible(val);
//       }
//       
//   }
//    
    
    
    
    public BlunderFixer() {
        initComponents();
        setLocationRelativeTo(null);
        JPanel panel = (JPanel)this.getContentPane();
        InputMap im = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
         setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
        im.put(KeyStroke.getKeyStroke("ctrl pressed B"), "cB");
        panel.getActionMap().put("cB", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                ens.hide();
            }
        });
         im.put(KeyStroke.getKeyStroke("alt pressed C"), "ac");
        panel.getActionMap().put("ac", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
             startSet();
            }
        });
        
        dialog.setContentPane(optionPane);
       
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        Amount.setText("100");
        Amount.setEnabled(false);
        
        try {
            ArrayList <String> list=EmployeeDao.getAllEmployees();
            for(String s: list){
                jEmpList.addItem(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(scaapp.gui.financemanager.ViewDayWiseFeeCollectionByCashier.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          ArrayList <String> batchesList=null;
        try {
            batchesList=BatchDao.getAllActiveBatchId(1);
        } catch (SQLException ex) {
            Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
         for(String s: batchesList)
          batches.addItem(s);
        
         
//         batches.addItem("B0001-MSKJ630E17OCT18");
//         batches.addItem("B0002-MSKP430E03OCT18");
//         batches.addItem("B0003-MSSC530E13OCT18");
         
//        BatchIdGenerator big=new BatchIdGenerator();
//        big.setCenter(1);
//        big.setFaculty(EmployeeDao.getEmployeeId(null));
//        big.setCourse(awtCourse.getSelectedIndex()+1);
//        SimpleDateFormat sdf=new SimpleDateFormat(awtStartDate.getDateFormatString());
//        big.setTime(txtTime.getText());
//        big.setDate(awtStartDate.getDate());
//        String start=sdf.format(awtStartDate.getDate());
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../resources/logo.png")));
    }

    BlunderFixer(EmployeeDetails ed) {
       this();
       this.ed=ed;
       employeeName.setText(ed.getEmp_name());
       EmployeeId=ed.getEmp_id();
          ArrayList <String> batchesList=null;
        try {
            batchesList=BatchDao.getAllActiveBatchId(ed.getCentre_id());
        } catch (SQLException ex) {
            Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
        }
         for(String s: batchesList)
          batches.addItem(s);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        studentId = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtcourseName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtBatchDays = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtBatchTime = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        batches = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jtBatchFaculty = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        Amount = new javax.swing.JTextField();
        lock = new javax.swing.JCheckBox();
        lockVisual = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        fee = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        studentName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        studentEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        studentSem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        studentCollage = new javax.swing.JTextField();
        studentContact = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        Register = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        employeeName = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jEmpList = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setTitle("Enroll new student - SCAOnCloud v1.0.0");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Contact details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel1.setText("Contact number/Barcode");

        studentId.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(studentId, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(studentId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Batch details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel2.setText("Course");

        jtcourseName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtcourseName.setEnabled(false);

        jLabel3.setText("Days");

        jtBatchDays.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtBatchDays.setEnabled(false);

        jLabel4.setText("Time");

        jtBatchTime.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtBatchTime.setEnabled(false);

        jLabel9.setText("Batch code");

        batches.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                batchesItemStateChanged(evt);
            }
        });
        batches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batchesActionPerformed(evt);
            }
        });
        batches.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                batchesKeyPressed(evt);
            }
        });

        jLabel10.setText("Faculty");

        jtBatchFaculty.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtBatchFaculty.setEnabled(false);

        jLabel12.setText("Reg Amount RS.");

        Amount.setText("100");
        Amount.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Amount.setEnabled(false);

        lock.setText("Lock this batch for registrations");
        lock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockActionPerformed(evt);
            }
        });

        lockVisual.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lockVisual.setForeground(new java.awt.Color(255, 51, 51));

        jLabel13.setText("Course Fee Rs.");

        fee.setEditable(false);
        fee.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lockVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(batches, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lock, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtcourseName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtBatchDays, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtBatchTime, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtBatchFaculty)
                            .addComponent(Amount, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(fee))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtcourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jtBatchDays, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtBatchTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jtBatchFaculty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(fee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batches, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lock)
                    .addComponent(lockVisual, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Student Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel5.setText("Name");

        studentName.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentName.setEnabled(false);

        jLabel6.setText("Email");

        studentEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentEmail.setEnabled(false);

        jLabel7.setText("Semester");

        studentSem.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentSem.setEnabled(false);

        jLabel8.setText("College");

        studentCollage.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentCollage.setEnabled(false);

        studentContact.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        studentContact.setEnabled(false);

        jLabel11.setText("Contact Number");

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
                    .addComponent(jLabel8)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(studentContact, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(studentEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(studentName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(studentSem)
                    .addComponent(studentCollage))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(studentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(studentEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentSem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentCollage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegisterActionPerformed(evt);
            }
        });
        Register.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RegisterKeyPressed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "User details"));

        jLabel16.setText("You are logged in as ");

        employeeName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        employeeName.setForeground(new java.awt.Color(0, 153, 51));
        employeeName.setText("Anonymous");

        jCheckBox1.setText("Take Fee Also");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jEmpList, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143)
                .addComponent(jCheckBox1)
                .addGap(135, 135, 135))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(employeeName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jEmpList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/plus.png"))); // NOI18N
        jMenu1.setText("New");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user.png"))); // NOI18N
        jMenuItem6.setText("Enroll student");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/plus.png"))); // NOI18N
        jMenuItem3.setText("Add new student");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/rupee.png"))); // NOI18N
        jMenu2.setText("Fee");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/give-money.png"))); // NOI18N
        jMenuItem7.setText("Collect fees");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/time-left.png"))); // NOI18N
        jMenuItem9.setText("Pending fees");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print.png"))); // NOI18N
        jMenu3.setText("Print");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/id-card.png"))); // NOI18N
        jMenuItem1.setText("ID card");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/id-card.png"))); // NOI18N
        jMenuItem2.setText("Print Id Of Full Batch");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/id-card.png"))); // NOI18N
        jMenuItem12.setText("Duplicate Reciept");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/eye-open.png"))); // NOI18N
        jMenu4.setText("View");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/user.png"))); // NOI18N
        jMenuItem4.setText("Student details");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/document.png"))); // NOI18N
        jMenuItem5.setText("Daily Collection");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/document.png"))); // NOI18N
        jMenuItem13.setText("Take Attendance");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/document.png"))); // NOI18N
        jMenuItem14.setText("View Attendance");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/lock.png"))); // NOI18N
        jMenu5.setText("Private");

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/logout.png"))); // NOI18N
        jMenuItem11.setText("Logout");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/reload.png"))); // NOI18N
        jMenuItem8.setText("Change password");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Register)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)
                                .addGap(8, 8, 8)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Register)
                            .addComponent(jButton2))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentIdActionPerformed

    private void studentIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studentIdKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            if(!(studentId.getText().length()==10||studentId.getText().length()==12))
            {
                JOptionPane.showMessageDialog(null,"Enter Valid Phone Number or barcode");
                studentId.setText("");
                //JOptionPane.showMessageDialog(null,"Student have not filled form Yet!!");
                studentId.requestFocus();
                return;
            }
             
        }
        
        
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          try {
               dialog.setLocationRelativeTo(BlunderFixer.this);
              dialog.setVisible(true);
              this.setEnabled(false);
              
             // System.out.println("On 609 "+studentId.getText().substring(0,10));
            sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
            
              //System.out.println(sd);
            if(sd==null)
            {
                    dialog.dispose();
               this.setEnabled(true);
                JOptionPane.showMessageDialog(null,"Student has not provided his basic details yet!!");
                studentId.setText("");
               studentId.requestFocus();
                return;
            }
            
               studentName.setText(sd.getStudentName().toUpperCase());
               studentContact.setText(studentId.getText().substring(0,10));
               studentEmail.setText(sd.getStudentEmail().toUpperCase());
               studentCollage.setText(sd.getStudentCollege().toUpperCase());
               studentSem.setText(sd.getStudentSem());
              System.out.println("Enter called");
//               enterfee.setEnabled(true);
//               enterfee.requestFocus(true);
               studentId.setEnabled(false);
               if(lock.isSelected()){
                   dialog.dispose();
               this.setEnabled(true);
                    register();}
               else{  
                   dialog.dispose();
               this.setEnabled(true);
            batches.requestFocus();           
            batches.showPopup();}
               
                
         }
        catch (SQLException ex) {
                Logger.getLogger(FeePayment.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null,"Something went wrong try again later!","Error",JOptionPane.ERROR_MESSAGE);
                  return;
            }
        
        
        
         }  

    }//GEN-LAST:event_studentIdKeyPressed

    private void batchesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_batchesKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            
         if(studentName.getText().length()!=0||studentId.getText().length()!=0)
            register();
         else
             JOptionPane.showMessageDialog(null,"No Student selected","Error",JOptionPane.ERROR_MESSAGE);
                 
            
            
            //jButton1.requestFocus();
            
        }
          
    }//GEN-LAST:event_batchesKeyPressed

    private void RegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegisterActionPerformed
        register();
    }//GEN-LAST:event_RegisterActionPerformed

    private void RegisterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RegisterKeyPressed
        //JOptionPane.showMessageDialog(null,"printing");
    }//GEN-LAST:event_RegisterKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
//        this.setVisible(false);
//        new MainFrame().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyReleased

    private void batchesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_batchesItemStateChanged
        try {
            // TODO add your handling code here:
          
            BatchDetailsDisplay bdd=BatchDao.showRecord(batches.getSelectedItem().toString());
              
            jtcourseName.setText(bdd.getCourseName());
            jtBatchDays.setText(bdd.getBatchDays());
            jtBatchTime.setText(bdd.getBatchTime());
            jtBatchFaculty.setText(bdd.getFacultyName());
            fee.setText(bdd.getFee()+"");
            System.out.println(bdd);
        } catch (SQLException ex) {
          
               JOptionPane.showMessageDialog(null,"Something went wrong try again later!","Error",JOptionPane.ERROR_MESSAGE);
           return;
        }
       
              
        
    }//GEN-LAST:event_batchesItemStateChanged

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        new BlunderFixer(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
new MainFrame(ed).setVisible(true);
        this.dispose();        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
        new MainFrame(ed).setVisible(true);
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new GenerateIDCard(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        new ViewStudentDetails(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        new ViewDayWiseFeeCollectionByCashier(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        new PendingFeeDetails(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void batchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batchesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batchesActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        new Login().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
startSet();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new StudentDetailsNew(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void lockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockActionPerformed
        // TODO add your handling code here:
        if(lock.isSelected())
        {
            lockVisual.setText("Locked");
            batches.setEnabled(false);
        }
        else
        {
            lockVisual.setText("");
            batches.setEnabled(true);
        }
        studentId.requestFocus();
    }//GEN-LAST:event_lockActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new GenerateIDCardOfFullBatch(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        System.out.println("Hello");
        new TakeStudentAttendance(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:

        new ViewAttendance(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
          new ChangePassword(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        new GenerateDuplicateReciept(ed).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
            Amount.setEnabled(true);
        else{
            Amount.setText(100+"");
             Amount.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(BlunderFixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BlunderFixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BlunderFixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BlunderFixer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WebLookAndFeel.install();
                new BlunderFixer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Amount;
    private javax.swing.JButton Register;
    private javax.swing.JComboBox batches;
    private javax.swing.JLabel employeeName;
    private javax.swing.JTextField fee;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jEmpList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jtBatchDays;
    private javax.swing.JTextField jtBatchFaculty;
    private javax.swing.JTextField jtBatchTime;
    private javax.swing.JTextField jtcourseName;
    private javax.swing.JCheckBox lock;
    private javax.swing.JLabel lockVisual;
    private javax.swing.JTextField studentCollage;
    private javax.swing.JTextField studentContact;
    private javax.swing.JTextField studentEmail;
    private javax.swing.JTextField studentId;
    private javax.swing.JTextField studentName;
    private javax.swing.JTextField studentSem;
    // End of variables declaration//GEN-END:variables
public void startSet(){
     sd=null;
        studentName.setText("");
        studentContact.setText("");
        studentEmail.setText("");
        studentCollage.setText("");
        studentSem.setText("");
        //enterfee.setText("");
        studentId.setText("");
        //enterfee.setEnabled(false);
        studentId.setEnabled(true);
        studentId.requestFocus(true);
        batches.hidePopup();
        jCheckBox1.setSelected(false);
        Amount.setText("100");
        Amount.setEnabled(false);
          this.setEnabled(true);
               dialog.dispose();
}
public void register(){
    if(!InternetUtil.check())
    {
         JOptionPane.showMessageDialog(null,"Please check your Internet Connection","NO INTERNET",JOptionPane.ERROR_MESSAGE);
         return;
    }
            
        
         if(Double.parseDouble(Amount.getText())<=0)  {
                   // dialog.dispose();
                 JOptionPane.showMessageDialog(null,"Please enter vailid amount!");
                
                  
                 
                 return;
             }
     if((Double.parseDouble(Amount.getText())%100)!=0)  {
         //dialog.dispose();
                 JOptionPane.showMessageDialog(null,"Amount should be entered in multiples of 100");
               
                 
                 return;
             }
       if(Double.parseDouble(fee.getText())<Double.parseDouble(Amount.getText()))
             {
                // dialog.dispose();
                 JOptionPane.showMessageDialog(null,"Course Fee is Rs."+fee.getText());
//                 enterfee.setText("");
//                 this.setEnabled(true);
//                 enterfee.requestFocus();
                
                 return;
             }
        
          dialog.setVisible(true);
              this.setEnabled(false);
              Runnable r1=()->{
      try {
             if(RegistrationDao.isRegistered(studentId.getText().substring(0,10), batches.getSelectedItem().toString()))
                 {
                     this.setEnabled(true);
                     dialog.dispose();
                    
                    JOptionPane.showMessageDialog(null,"Student Already Registered");
                     studentId.setText("");
                      studentId.setEnabled(true);
                      studentId.requestFocus();
                      startSet();
                return;
                 }
//                StudentTransactionDao.addStudentTransaction(st);
//                new GSTReceiptPrinter().printGstReciept(st,sd.getStudentName());
//                startSet();
                
                
                
                
                // 
            
               String message="Register "+studentName.getText()+" in "+jtcourseName.getText()+" of "+jtBatchFaculty.getText()+" at "+jtBatchTime.getText()+" Batch-Id-"+batches.getSelectedItem().toString();
               
               
               JOptionPane optionPane = new JOptionPane(message+"\n\nWould you like to confirm registration?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
    JDialog dialog = optionPane.createDialog("Confirm Payment?");
    Set<AWTKeyStroke> focusTraversalKeys = new HashSet<AWTKeyStroke>(dialog.getFocusTraversalKeys(0));
    focusTraversalKeys.add(AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.VK_UNDEFINED));
    dialog.setFocusTraversalKeys(0, focusTraversalKeys);
    dialog.setVisible(true);
    dialog.dispose();
    
        
    Integer dialogResult = (Integer) optionPane.getValue();
               
               
               
              // int dialogResult = JOptionPane.showConfirmDialog (null, message+"\n\nWould you like to confirm registration?","Warning",JOptionPane.YES_NO_OPTION);
if(dialogResult==null || dialogResult == JOptionPane.NO_OPTION || dialogResult == JOptionPane.CLOSED_OPTION){
  // Saving code here
               
               this.setEnabled(true);
               dialog.dispose();
     JOptionPane.showMessageDialog(null,"Transaction Cancelled!!");
      studentId.setText("");
                      studentId.setEnabled(true);
                      studentId.requestFocus();
                       startSet();
    return;
}
//               if(!InternetUtil.check())
//    {
//               dialog.dispose();
//               this.setEnabled(true);
//         JOptionPane.showMessageDialog(null,"Please check your Internet Connection","NO INTERNET",JOptionPane.ERROR_MESSAGE);
//         this.setEnabled(true);
//         return;
//    }
                
               //sd=StudentDetailDao.getStudentDetailsByContactNo(studentId.getText().substring(0,10));
               sd.setStudentContact(studentId.getText().substring(0,10));
               studentName.setText(sd.getStudentName().toUpperCase());
               studentContact.setText(sd.getStudentContact());
               studentEmail.setText(sd.getStudentEmail().toUpperCase());
               studentCollage.setText(sd.getStudentCollege().toUpperCase());
               studentSem.setText(sd.getStudentSem());
               System.out.println(sd);
            } catch (SQLException ex) {
                dialog.dispose();
                this.setEnabled(true);
                    JOptionPane.showMessageDialog(null,"Something went wrong try again later!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
            }
//         if(first){
//            first=false;
//            return;
//                 }
         
             Date d=null;
             try {
          d=StudentTransactionDao.getDate();
                 //st.setTransactionId("MN/2018-2019/1008");
            } catch (Exception ex) {
                dialog.dispose();
                this.setEnabled(true);
                    JOptionPane.showMessageDialog(null,"Something went wrong try again later!","Error",JOptionPane.ERROR_MESSAGE);
                    return;
            }
            Calendar c=Calendar.getInstance();
            c.setTime(d);
            int year;
             int month = c.get(Calendar.MONTH);
                if(month>=3)
                    year=c.get(Calendar.YEAR);
                else
                    year=c.get(Calendar.YEAR)-1;
             int amount=Integer.parseInt(Amount.getText());
              int eid=10;
        try {
            eid=EmployeeDao.getEmployeeId(jEmpList.getSelectedItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(BlunderFixer.class.getName()).log(Level.SEVERE, null, ex);
        }
             StudentTransaction st=new StudentTransaction();
             st.setEmployeeId(eid);
             st.setStudentId(sd.getStudentContact());
            
             st.setPayementModeId(1);
             st.setBatchId(batches.getSelectedItem().toString());
             st.setCentreId(1);
             st.setGrossAmount(amount);
             st.setCgstAmount(amount*0.09);
             st.setSgstAmount(amount*0.09);
             st.setNetAmount(amount-(st.getSgstAmount()+st.getCgstAmount()));
             SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd");
             String date=sdf.format(d);
             st.setDateOfPayement(date);
             st.setEmail(sd.getStudentEmail());
             if(!InternetUtil.check())
                    {
                    dialog.dispose();
                    this.setEnabled(true);
                    JOptionPane.showMessageDialog(null,"Please check your Internet Connection","NO INTERNET",JOptionPane.ERROR_MESSAGE);
                    return;
                    }
               while(true){  
            try {
                
              
                // if(ed.getBatchType().equalsIgnoreCase("project"))
                              st.setTransactionId("A2I/"+year+"-"+(year+1)+"/"+StudentTransactionDao.getNewTransactionId(null));
                          //else
                             // st.setTransactionId("SCA/"+year+"-"+(year+1)+"/"+StudentTransactionDao.getNewTransactionId(null));
                        //st.setTransactionId("MN/2018-2019/79");
                System.out.println(st.getTransactionId());
               // StudentTransactionDao.addStudentTransaction(st);
               // RegistrationDao.registerStudentInBatch(st.getStudentId(), st.getBatchId());
                RegistrationDao.registerStudentInBatch(st,0);//using batch update
               // new GSTReceiptPrinter().printGstReciept(st,sd.getStudentName());
               
                
                
                
                
                // 
            } catch (SQLException ex) {
                if(ex.getMessage().contains("Duplicate entry")){
                             System.out.println("Error due to Duplicate entry");
                             continue;
                         }
                  dialog.dispose();
                  this.setEnabled(true);
                  JOptionPane.showMessageDialog(null,"Something went wrong try again later!","Error",JOptionPane.ERROR_MESSAGE);
                  return;
            }
                break;
               }
                String s=sd.getStudentName();
               
               
             
                       
              // int dialogResult = JOptionPane.showConfirmDialog (null, message+"\n\nWould you like to confirm registration?","Warning",JOptionPane.YES_NO_OPTION);

//                String emp=ed.getEmp_name();
//                Runnable r=null;
//                if(ed.getBatchType().equalsIgnoreCase("NORMAL"))
//                r=()-> new GSTReceiptPrinter().printGstReciept(st,s,emp);
//                else
//                r=()-> new GSTReceiptPrinterProjectBatch().printGstReciept(st,s,emp);
//                Thread t=new Thread(r);
//                t.start();
                dialog.dispose();
               this.setEnabled(true);
                
                      startSet();
                      studentId.setText("");
                      studentId.setEnabled(true);
                      studentId.requestFocus();
                      startSet();
                      
                    
};
        Thread t=new Thread(r1);
                t.start();       
}

}
