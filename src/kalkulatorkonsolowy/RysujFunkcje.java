package kalkulatorkonsolowy;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class RysujFunkcje extends Applet implements ActionListener{
   static double[] x = new double[1000]; 
   static double zakresX = 90, zakresY = 1;
   Graphics gd;
   TextField t = new TextField();
   TextField x1, y1 = new TextField();
   Panel p = new Panel();
   Panel p2 = new Panel();
   Panel funkcja = new Panel();
   Canvas canv = new MyCanvas();
   BorderLayout lbor;
   FlowLayout lflow;
   JButton b1, b2;
   ImageIcon i1, i2;
   @Override
   public void init()
   {
       resize(new Dimension(1300,550));
       lbor = new BorderLayout();
       lflow = new FlowLayout();
       p2.setLayout(lflow);
       p.setLayout(lbor);
       p.add(p2, BorderLayout.NORTH);
       p.add(canv);
       canv.setBackground(new Color(100,255,255));
       canv.setPreferredSize(new Dimension(1000,400));
       p.add(t, BorderLayout.SOUTH);
       t.addActionListener(this);
       x1 = new TextField("Gorna i dolna granica x");
       y1 = new TextField("Gorna i dolna granica y");
       x1.addActionListener(this);
       y1.addActionListener(this);
       p2.add(x1);
       p2.add(y1);
       add(p);     
   }
    @Override
    public void actionPerformed(ActionEvent e) {
        zakresX = Double.parseDouble(x1.getText());
        zakresY = Double.parseDouble(y1.getText());
        gd = canv.getGraphics();
        x[0] = -zakresX;
        x[999] = zakresX;
        for(int i = 1; i<999; i++)
        {
            x[i] = x[i-1] + (2*zakresX)/1000; 
        }
        for(int i = 0; i<999; i++)
        {
            if(!((t.getText().contains("tan")||t.getText().contains("cot"))&&((wartoscFunkcjiWPunkcie(t.getText(), x[i])*wartoscFunkcjiWPunkcie(t.getText(), x[i+1]))<0)))
            gd.drawLine(i, -wartoscFunkcjiWPunkcie(t.getText(), x[i])+200, i+1, -wartoscFunkcjiWPunkcie(t.getText(), x[i+1])+200);
            System.out.println( wartoscFunkcjiWPunkcie(t.getText(), x[i]) +"    " + wartoscFunkcjiWPunkcie(t.getText(), x[i+1]) );
        }
    }
    public int wartoscFunkcjiWPunkcie( String funkcja, double x)
    {
        funkcja = funkcja.replace("exp", "epow");
        funkcja = funkcja.replace("x", Double.toString(x));
        funkcja = funkcja.replace("epow", "exp");
        funkcja = KalkulatorKonsolowy.postfix(funkcja);
        return (int)(KalkulatorKonsolowy.wynikZPostifxu(funkcja)*(200/zakresY));
    }
}
   
  
    
    


    
    

