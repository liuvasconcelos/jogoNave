
package br.com.model;


import br.com.view.TelaJogo;
import br.com.view.Jogo;
import static br.com.view.TelaJogo.janelaJogo;
import static br.com.view.TelaJogo.mostraPontos;
import static br.com.view.TelaJogo.qtdPontos;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author liu
 */
public class Tiro extends JLabel {
   
    private ImageIcon imagemTiro;
    private int posicaoX;
    private int posicaoY;
    private Timer timer;

    public Tiro() {

        this.posicaoX = TelaJogo.nave.getX()+15;
        this.posicaoY = 490;
        
        
        setBounds(posicaoX,posicaoY, 20, 31);
        imagemTiro = new ImageIcon("imagens/balatiro.png");
        imagemTiro = new ImageIcon(imagemTiro.getImage().getScaledInstance(20, 31, Image.SCALE_DEFAULT));
        setIcon(imagemTiro);
        setVisible(true); 
    }


    public void movimentar(){
     
        //TIMER PARA A BALA SE MOVER
        timer = new Timer(10, null);

        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(posicaoX, posicaoY-1, 20, 31);
                posicaoY=getY();
                 
                for (Meteoro meteoro : TelaJogo.meteoros) {
                    if(posicaoX >= meteoro.getPosicaoX()-15 && posicaoX<=(meteoro.getPosicaoX()+31) 
                            && posicaoY >= meteoro.getPosicaoY()&& posicaoY <= (meteoro.getPosicaoY()+60)
                            && isVisible() && !meteoro.getAtingido()){
                        
                        setVisible(false);
                        meteoro.setAtingido(true);
                        meteoro.mudaImagemPontuacao();
                        Jogo.jogadorLogado.contaPontos();
                        qtdPontos =  "PONTOS: "+String.valueOf(Jogo.jogadorLogado.getPontos());
                        mostraPontos.setText(qtdPontos);
                    }      
                }
            }
        });
        timer.start();
    } 
}
