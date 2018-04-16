/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProgressBar.java
 *
 * Created on Jan 27, 2011, 9:55:00 PM
 */

package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.plaf.PanelUI;

/**
 *
 * @author Ahza
 */
public class LaunchProgressBar extends javax.swing.JDialog{

    /** Creates new form ProgressBar */
    public LaunchProgressBar() {
        setUndecorated(true);
        initComponents();
        splashPanel.setUI(new BackgroundUI());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width-getWidth())/2;
        int y = (dim.height-getHeight())/2;
        setLocation(x, y);
       progressBar.setStringPainted(true);
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splashPanel = new javax.swing.JPanel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        splashPanel.setPreferredSize(new java.awt.Dimension(306, 165));

        progressBar.setValue(50);

        javax.swing.GroupLayout splashPanelLayout = new javax.swing.GroupLayout(splashPanel);
        splashPanel.setLayout(splashPanelLayout);
        splashPanelLayout.setHorizontalGroup(
            splashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, splashPanelLayout.createSequentialGroup()
                .addGap(0, 95, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        splashPanelLayout.setVerticalGroup(
            splashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, splashPanelLayout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(splashPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel splashPanel;
    // End of variables declaration//GEN-END:variables

}

class BackgroundUI extends PanelUI {
    ImageIcon background = new ImageIcon(getClass().getResource("/images/begadang.jpg"));
    @Override
    public void paint(Graphics g, JComponent c) {
         g.drawImage(background.getImage(), 0, 0, null);
    }
}
