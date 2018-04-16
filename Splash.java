/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static java.awt.SystemColor.text;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class Splash {

    public static void main(String[] args) {
        OverTimeView otv = new OverTimeView();
        SplashScreen ss = new SplashScreen();
        ss.setVisible(true);

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(50);
                ss.Time.setText(Integer.toString(i) + "%");
                ss.ProgreesBar.setValue(i);
                ss.ProgreesBar.setBounds(100,280,400,30);
                if (i == 100) {
                    ss.setVisible(false);
                    otv.setVisible(true);
                }
            }
        } catch (Exception e) {
        }

    }
}
