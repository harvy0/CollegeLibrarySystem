import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;

public class Print implements Printable {
	
	 public int print(Graphics g, PageFormat pf, int page, JFrame frame)
		 throws PrinterException {
			    if (page > 0) {
			        return NO_SUCH_PAGE;
			    }

			    Graphics2D g2d = (Graphics2D)g;
			    g2d.translate(pf.getImageableX(), pf.getImageableY());

			    // Print the entire visible contents of a
			    // java.awt.Frame.
			    frame.printAll(g);

			    return PAGE_EXISTS;
   
}

	public int print(Graphics arg0, PageFormat arg1, int arg2)
			throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void printComponent(final Component comp){

		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      comp.paint(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
		}
	
}