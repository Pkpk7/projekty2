/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kalkulatorkonsolowy;

import java.awt.Canvas;
import java.awt.Graphics;

 public class MyCanvas extends Canvas
   {
       @Override
       public void paint(Graphics d)
       {
          d.drawLine(0, 200, 1000, 200);
          d.drawLine(500, 0, 500, 400);
          d.drawString(""+RysujFunkcje.zakresX, 950, 210);
          d.drawString(""+RysujFunkcje.zakresY, 510, 10);
       }
   }