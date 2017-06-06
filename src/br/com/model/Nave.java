package br.com.model;

import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 *
 * @author liu
 */
public class Nave extends JLabel {
    private ImageIcon imagemNave;

    
    public Nave() {
    
        setBounds(0, 530, 52, 62);
        imagemNave = new ImageIcon("imagens/nave.png");
        imagemNave = new ImageIcon(imagemNave.getImage().getScaledInstance(52, 72, Image.SCALE_DEFAULT));
        setIcon(imagemNave);
        setVisible(true);
        
    }
       
}
