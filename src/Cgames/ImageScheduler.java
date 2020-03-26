package Cgames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


import javax.swing.JFrame;
import javax.swing.Timer;


class ImageScheduler extends MouseAdapter
{
    AcGame imageDisplay;
    Timer timer;
  
    public ImageScheduler(AcGame ac)
    {
        imageDisplay = ac;
        timer = new Timer(5000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                imageDisplay.getPicLabel().setIcon(null);
                ac.getMain_window().setVisible(true);
            }

        });
        timer.setRepeats(false);
    }
}