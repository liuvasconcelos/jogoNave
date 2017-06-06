package br.com.view;

import br.com.model.Jogador;
import br.com.model.Meteoro;
import br.com.model.Nave;
import br.com.model.Tiro;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TelaJogo {

	public static JFrame janelaJogo;
	JPanel painelJogo;
	JLabel fundoJogo;
	ImageIcon imagemFundoJogo;
        Timer timerMeteoro;
        JLabel vidas;
        ImageIcon imagemVida;
        JLabel pontos;
        ImageIcon imagemPontos;
        public static JLabel mostraPontos;
        public static String qtdPontos;
        public static JLabel mostraVida;
        public static String qtdVida;
        public static Nave nave = new Nave();
        public static int vida = 0;
        public static ArrayList<Meteoro> meteoros = new ArrayList<>();
   
        
        public void moverNave(){
             

             janelaJogo.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent e) {   
                    if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
                      nave.setBounds(nave.getX()+10, nave.getY(), nave.getWidth(), nave.getHeight());         
                    }
                    if(e.getKeyCode() == KeyEvent.VK_LEFT){
                                   
			nave.setBounds(nave.getX()-10, nave.getY(), nave.getWidth(), nave.getHeight());
                        
                    }
                    if(e.getKeyCode() == KeyEvent.VK_SPACE){          
                        
                        Tiro tiro = new Tiro();  
                        painelJogo.add(tiro, 0); 
                        tiro.movimentar(); //chama a movimentação do tiro
                        
                    }
                    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                        janelaJogo.dispose();
                        Jogo.fecha();
                    }
				
		}

                 public void keyTyped(KeyEvent e) {
                 }

                 public void keyReleased(KeyEvent e) {
           
                 }	
		});     
        }
        
        public Meteoro criaMeteoro(){
            Meteoro meteoro = new Meteoro();
            painelJogo.add(meteoro, 0); //mostra o meteoro
            meteoro.movimentar();//chama a movimentação do meteoro
            
            return meteoro;
        }
     	
	public TelaJogo() {
		//CONFIGURAÇÕES DO JFRAME:
		janelaJogo = new JFrame("JOGO DA NAVE - LÍVIA VASCONCELOS");
		janelaJogo.setSize(700, 600);
		janelaJogo.setLayout(null);
		janelaJogo.setLocationRelativeTo(null); //fica no meio da tela
		janelaJogo.setResizable(false); //deixa sem o botão de maximizar 
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//CONFIGURAÇÕES DO PAINEL DE JOGO:
		painelJogo = new JPanel();
		painelJogo.setLayout(null);
		painelJogo.setBounds(0, 0, janelaJogo.getWidth(), janelaJogo.getHeight());
		painelJogo.setVisible(true);
                janelaJogo.add(painelJogo, -1);

		//CONFIGURAÇÕES DO BACKGROUND:
		fundoJogo = new JLabel();
		fundoJogo.setBounds(0, 0, janelaJogo.getWidth(), janelaJogo.getHeight());
		imagemFundoJogo = new ImageIcon("imagens/céu.jpg");
		imagemFundoJogo = new ImageIcon(imagemFundoJogo.getImage().getScaledInstance(janelaJogo.getWidth(), janelaJogo.getHeight(), Image.SCALE_DEFAULT));
		fundoJogo.setIcon(imagemFundoJogo);
		fundoJogo.setVisible(true);
		painelJogo.add(fundoJogo, 0);
		
		
                Jogo.jogadorLogado.setPontos(0);
                Jogo.jogadorLogado.setErros(0);
		
                janelaJogo.setVisible(true);
                
                
                janelaJogo.requestFocus();
                painelJogo.add(nave, 0);
                moverNave();
                
                //DEFINICOES DO METEORO
                
                timerMeteoro = new Timer(500, null); // 500 miliseg. - tempo pra criar um novo meteoro
                
                timerMeteoro.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                         
                            meteoros.add(criaMeteoro());  
                         
                         if(Jogo.jogadorLogado.getErros()== 5){
                            timerMeteoro.stop();
                            janelaJogo.dispose();
                            Jogo.fecha();
                         }
                    }
                });
                
                timerMeteoro.start();
                
                
                //DEFINIÇÕES DO CONTADOR DE PONTOS
                qtdPontos = "PONTOS: "+ String.valueOf(Jogo.jogadorLogado.getPontos());
                mostraPontos = new JLabel(qtdPontos);
                mostraPontos.setBounds(550, 40, 200, 50);
                mostraPontos.setForeground(Color.white);
                Font font = new Font("Tahoma",Font.BOLD, 17);
                mostraPontos.setFont(font);
                painelJogo.add(mostraPontos, 0);
                
                pontos = new JLabel();
                pontos.setBounds(500, 50, 30, 32);
                imagemPontos = new ImageIcon("imagens/pontos.png");
                imagemPontos = new ImageIcon(imagemPontos.getImage().getScaledInstance(30, 32, Image.SCALE_DEFAULT));
                pontos.setIcon(imagemPontos);
                pontos.setVisible(true);
                painelJogo.add(pontos, 0);
                
                //DEFINIÇÕES DO CONTADOR DE VIDAS
                qtdVida =  "VIDAS: "+String.valueOf(5 - Jogo.jogadorLogado.getErros());
                mostraVida = new JLabel(qtdVida);
                mostraVida.setBounds(550, 70, 200, 50);
                mostraVida.setForeground(Color.white);
                mostraVida.setFont(font);
                painelJogo.add(mostraVida, 0);
                
                vidas = new JLabel();
                vidas.setBounds(500, 80, 30, 30);
                imagemVida = new ImageIcon("imagens/vida.png");
                imagemVida = new ImageIcon(imagemVida.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
                vidas.setIcon(imagemVida);
                vidas.setVisible(true);
                painelJogo.add(vidas, 0);
               

	} // FIM DO CONSTRUTOR DO JOGO
        
        
        
    public JPanel getJogo() {
        return painelJogo;
    }

    public JFrame getJanelaJogo() {
        return janelaJogo;
    }

    void setVisible(boolean b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
