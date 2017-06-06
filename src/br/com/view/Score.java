package br.com.view;

import br.com.model.Jogador;
import static br.com.view.Jogo.conexaobd;
import static br.com.view.TelaJogo.janelaJogo;
import static br.com.view.TelaJogo.nave;
import conexoes.ConexaoBD;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author liu
 */
public class Score {
    
    JFrame janelaScore;
    JPanel painelScore;
    JLabel fundoScore;
    ImageIcon imagemFundoScore;
    JButton jogarNovamente;
    JLabel textoScore;
    String texto = "<html>";
        
    public Score(){
            //CONFIGURAÇÕES DO JFRAME:
            janelaScore = new JFrame("SCORE");
            janelaScore.setSize(683, 548);
            janelaScore.setLayout(null);
            janelaScore.setLocationRelativeTo(null); //fica no meio da tela
            janelaScore.setResizable(false); //deixa sem o botão de maximizar 
            janelaScore.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //CONFIGURAÇÕES DO PAINEL DE JOGO:
            painelScore = new JPanel();
            painelScore.setLayout(null);
            painelScore.setBounds(0, 0, janelaScore.getWidth(), janelaScore.getHeight());
            painelScore.setVisible(true);
            janelaScore.add(painelScore, -1);

            //CONFIGURAÇÕES DO BACKGROUND:
            fundoScore = new JLabel();
            fundoScore.setBounds(0, 0, janelaScore.getWidth(), janelaScore.getHeight());
            imagemFundoScore = new ImageIcon("imagens/score.png");
            imagemFundoScore = new ImageIcon(imagemFundoScore.getImage().getScaledInstance(janelaScore.getWidth(), janelaScore.getHeight(), Image.SCALE_DEFAULT));
            fundoScore.setIcon(imagemFundoScore);
            fundoScore.setVisible(true);
            painelScore.add(fundoScore, 0);
            
            //TEXTO DO SCORE:
            texto+=conexaobd.listaSQL("select nome, pontos from jogador order by pontos desc limit 10");
            
            texto+="<br>**Seu score no jogo atual: "+Jogo.jogadorLogado.getPontos()+" pts</html>";
                        
            textoScore = new JLabel(texto);
            textoScore.setBounds(270, 100, 600, 500);
            textoScore.setForeground(Color.white);
            Font font = new Font("Tahoma",Font.BOLD, 17);
            textoScore.setFont(font);
            painelScore.add(textoScore, 0);
            
            //BOTÃO JOGAR NOVAMENTE
            jogarNovamente = new JButton("Deseja jogar novamente?");
            jogarNovamente.setBounds(janelaScore.getWidth()-300, janelaScore.getHeight()- 50, 250,20);
            jogarNovamente.setVisible(true);
            jogarNovamente.setBackground(Color.white);
            painelScore.add(jogarNovamente, 0);
            
            janelaScore.setVisible(true);
            janelaScore.requestFocus();
            
                        
            jogarNovamente.addMouseListener(new MouseAdapter() {
                
                public void mousePressed(MouseEvent e){
                    janelaScore.dispose();
                    new TelaJogo().setVisible(true);
                }

                
            });
       
    }

    void setVisible(boolean b) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
