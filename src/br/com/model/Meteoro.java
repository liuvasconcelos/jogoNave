package br.com.model;

import br.com.view.TelaJogo;
import br.com.view.Jogo;
import static br.com.view.TelaJogo.janelaJogo;
import static br.com.view.TelaJogo.qtdVida;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author liu
 */
public class Meteoro extends JLabel{
    private ImageIcon imagemMeteoro;
    private int posicaoX;
    private int posicaoY;
    private Timer timer;
    private Boolean atingido = false;
    
    public Meteoro() {
        int posicaoRandomica = 0;
        Random gerador = new Random();
        posicaoRandomica = gerador.nextInt(681);
        this.posicaoX = posicaoRandomica;
        this.posicaoY = 0;
        
        
        setBounds(posicaoX,posicaoY, 100, 100);
        imagemMeteoro = new ImageIcon("imagens/meteoro.png");
        imagemMeteoro = new ImageIcon(imagemMeteoro.getImage().getScaledInstance(32, 56, Image.SCALE_DEFAULT));
        setIcon(imagemMeteoro);
        setVisible(true);
                
    
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    public ImageIcon getImagemMeteoro() {
        return imagemMeteoro;
    }

    public void setImagemMeteoro(ImageIcon imagemMeteoro) {
        this.imagemMeteoro = imagemMeteoro;
    }

    public void setAtingido(Boolean atingido) {
        this.atingido = atingido;
    }

    public Boolean getAtingido() {
        return atingido;
    }
    
    
    
    public void movimentar(){
     
        //TIMER PARA O METEORO SE MOVER
        int tempoRandomico = 0;
        
        do{
            Random geradorTempo = new Random();
            tempoRandomico= geradorTempo.nextInt(11);
        }while(tempoRandomico<5); //para não ficar TÃO rápido
        
        
        Timer currentTimer = new Timer(tempoRandomico, null);

        currentTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBounds(posicaoX, posicaoY+1, 70, 70);
                posicaoY=getY();
                
                int erros = Jogo.jogadorLogado.getErros();
                    if(posicaoX >= TelaJogo.nave.getX()-45 && posicaoX<=(TelaJogo.nave.getX()+52) 
                            && posicaoY == 500
                            && isVisible() && !atingido  && erros<5){
                        atingido = true;
                        Jogo.jogadorLogado.setErros(erros+1);
                        qtdVida =  "VIDAS: " + String.valueOf(5 - Jogo.jogadorLogado.getErros());
                        TelaJogo.mostraVida.setText(qtdVida);
                        mudaImagemBomba();  
                    }
                    if(erros == 5){
                          currentTimer.stop();
                    }
            }
        });
        currentTimer.start();    
    }
    
    public void mudaImagemPontuacao(){
        imagemMeteoro.getImage().flush();
        imagemMeteoro = new ImageIcon("imagens/10.png");
        imagemMeteoro = new ImageIcon(imagemMeteoro.getImage().getScaledInstance(40, 37, Image.SCALE_DEFAULT));
        setIcon(imagemMeteoro);
        setVisible(true);
        
        
        Timer currentTimer = new Timer(300, null);

        currentTimer.addActionListener(new ActionListener() { //para a imagem sumir depois de 300milisegundos.
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                currentTimer.stop();
            }
        });
        currentTimer.start();
    }
    
    
    public void mudaImagemBomba(){
        imagemMeteoro.getImage().flush();
        imagemMeteoro = new ImageIcon("imagens/bomba.png");
        imagemMeteoro = new ImageIcon(imagemMeteoro.getImage().getScaledInstance(60, 55, Image.SCALE_AREA_AVERAGING));
        setIcon(imagemMeteoro);
        setVisible(true);
        
        
        Timer currentTimer = new Timer(300, null);

        currentTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //para a imagem sumir depois de 300milisegundos.
                setVisible(false);
                currentTimer.stop();
            }
        });
        currentTimer.start();
    }
}
