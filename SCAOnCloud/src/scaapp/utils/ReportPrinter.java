/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scaapp.utils;

/**
 *
 * @author Comp8
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import java.util.ArrayList;
import scaapp.pojo.MyPayement;
 
public class ReportPrinter implements Printable{
 
    int[] pageBreaks;  // array of page break line positions.
 
    /* Synthesise some sample lines of text */
    //String[] textLines;
     ArrayList<MyPayement> list;
     ReportOff rp;
//    private void initTextLines() {
//        if (textLines == null) {
//            int numLines=200;
//            textLines = new String[numLines];
//            for (int i=0;i<numLines;i++) {
//                textLines[i]= "This is line number " + i;
//            }
//        }
//    }
 
    public int print(Graphics g, PageFormat pf, int pageIndex)
             throws PrinterException {
 
        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight()+3;
 
        if (pageBreaks == null) {
            //initTextLines();
            int linesPerPage = (int)(pf.getImageableHeight()/lineHeight)-5;
            int numBreaks = (list.size()-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage; 
            }
        }
 
        
        
        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }
 
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         * Since we are drawing text we
         */
        Graphics2D g2d = (Graphics2D)g;
        
        
        
        g2d.translate(pf.getImageableX(), pf.getImageableY());
 
        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */
        int y = 0; 
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                         ? list.size() : pageBreaks[pageIndex];
        for (int line=start; line<end; line++) {
            if(y==0){
                
                
                y += lineHeight;
                if(rp!=null){
                g.drawString("Report Of Cashier:  "+rp.getEmp(), 10, y);
                g.drawString("Report Date:  "+rp.getStartDate(),410, y);
                y += lineHeight*2;
                }
                //g.drawString("      Name                    Contact No                      Batch Id                        Amount Paid", 0, y);
            g.drawString("Name", 10, y);
            g.drawString("Contact No", 140, y);
             g.drawString("Batch Id", 270, y);
              g.drawString("Amount Paid", 470, y);
            //continue;
            
            }
           
           y += lineHeight;
            MyPayement mp=list.get(line);
            //g.drawString(textLines[line], 0, y);
             g.drawString(mp.getStudentName(), 10, y);
            g.drawString(mp.getStudentContactNo(), 140, y);
             g.drawString(mp.getBatchId()+"", 270, y);
              g.drawString(mp.getGrossAmount()+"", 470, y);
        }
 
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }
 
    public void printReport( ArrayList<MyPayement> list) {
        this.list=list;
        //initTextLines();
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
 
//    public static void main(String args[]) {
// 
//        try {
//            String cn = UIManager.getSystemLookAndFeelClassName();
//            UIManager.setLookAndFeel(cn); // Use the native L&F
//        } catch (Exception cnf) {
//        }
//        JFrame f = new JFrame("Printing Pagination Example");
//        f.addWindowListener(new WindowAdapter() {
//           public void windowClosing(WindowEvent e) {System.exit(0);}
//        });
//        JButton printButton = new JButton("Print Pages");
//        printButton.addActionListener(new ReportPrinter());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
//    }

    public void printReport(ArrayList<MyPayement> list, ReportOff rp) {
         this.list=list;
         this.rp=rp;
        //initTextLines();
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
         }
    }
}