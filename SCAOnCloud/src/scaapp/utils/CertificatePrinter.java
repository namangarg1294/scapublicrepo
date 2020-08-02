

package scaapp.utils;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.print.*;
import java.util.ArrayList;
import scaapp.pojo.IDCardPrintingDetails;

public class CertificatePrinter implements Printable{
ArrayList<IDCardPrintingDetails> printingDataList=new ArrayList<>();
    int[] pageBreaks;  // array of page break line positions.
String name;
    /* Synthesise some sample lines of text */
    String[] textLines;
    private void initTextLines() {
        if (textLines == null) {
            int numLines=200;
            textLines = new String[numLines];
            for (int i=0;i<numLines;i++) {
                textLines[i]= "This is line number " + i;
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int pageIndex)
             throws PrinterException {
Graphics2D g2 = (Graphics2D)g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Segoe Script", Font.BOLD, 220));
        g2.setColor(Color.RED);

        // Rotate 90 degree to make a vertical text
        AffineTransform at = new AffineTransform();
        at.setToRotation(Math.toRadians(90), 400, 400);
        g2.setTransform(at);
        
        Font font = new Font("Serif", Font.PLAIN, 220);
        FontMetrics metrics = g2.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        if (pageBreaks == null) {
            initTextLines();
            int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
            int numBreaks = (printingDataList.size()-1)/linesPerPage;
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
       
         //g2.drawString("This is a vertical text", 0, 5);
        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */
        int y = 40; 
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                         ? printingDataList.size() : pageBreaks[pageIndex];
       
            int x=3150;
           // String name="R";
            //String name="Rajat Nigam";
           // name="Prayatna Jain";
            //String name="Rajat Prayatna Naman";
            x=(int) (x-((name.length()/2.0)*140));
            System.out.println(x);
        g2.drawString(name, x, -1600);
        g2.drawLine(100, 130, 3000, 1300);
        g2.setFont(new Font("verdana", Font.PLAIN, 100));
//        g2.drawString("who has successfully participated in and complete workshop on", 1300, -1200);
//         g2.drawString("Planet Food - Restraunt Billing System", 1900, -1050);
//          g2.drawString("A comprehensive set of topics pertaining JAVA (SE) were covered in the workshop", 1100, -900);
//           g2.drawString("and the student was able to present a satisfactory project at the completion of", 1100, -750);
//            g2.drawString("workshop", 2600, -600);
           
        g2.drawString("18-Dec-1995", 1250, -150);
        
           //g.drawString(textLines[line], 0, y);
        

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void print(String name) {
        
        
        
        
        this.name=name;
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
//        printButton.addActionListener(new CertificatePrinter());
//        f.add("Center", printButton);
//        f.pack();
//        f.setVisible(true);
//    }
}