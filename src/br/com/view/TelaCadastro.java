package br.com.view;

import br.com.model.Jogador;
import static br.com.view.Jogo.conexaobd;
import static br.com.view.TelaJogo.janelaJogo;
import static br.com.view.TelaJogo.nave;
import conexoes.ConexaoBD;
import static conexoes.ConexaoBD.rs;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class TelaCadastro {
    
    JFrame janelaCadastro;
    JPanel painelCadastro;
    JLabel fundoCadastro;
    ImageIcon imagemFundoCadastro;
    JButton login;
    JButton cadastro;
    JButton sobre;
    String regras = "SPACE WAR ----- REGRAS: \n"
                            + "***Cada meteoro acertado lhe dará 10 pontos!\n"
                            + "***Cuidado com eles! Eles não podem acertar sua nave mais que 5 vezes!\n"
                            + "***Sua nave se movimenta com as SETAS e os tiros são dados com a tecla ESPAÇO\n"
                            + "***Para sair do jogo a qualquer momento, aperte ESC!\n"
                            + "\n\nBOA SORTE!";
    
    
    public TelaCadastro(){
            //CONFIGURAÇÕES DO JFRAME:
            janelaCadastro = new JFrame("CADASTRO DE USUÁRIOS");
            janelaCadastro.setSize(683, 548);
            janelaCadastro.setLayout(null);
            janelaCadastro.setLocationRelativeTo(null); //fica no meio da tela
            janelaCadastro.setResizable(false); //deixa sem o botão de maximizar 
            janelaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //CONFIGURAÇÕES DO PAINEL:
            painelCadastro = new JPanel();
            painelCadastro.setLayout(null);
            painelCadastro.setBounds(0, 0, janelaCadastro.getWidth(), janelaCadastro.getHeight());
            painelCadastro.setVisible(true);
            janelaCadastro.add(painelCadastro, -1);

            //CONFIGURAÇÕES DO BACKGROUND:
            fundoCadastro = new JLabel();
            fundoCadastro.setBounds(0, 0, janelaCadastro.getWidth(), janelaCadastro.getHeight());
            imagemFundoCadastro = new ImageIcon("imagens/cadastro.png");
            imagemFundoCadastro = new ImageIcon(imagemFundoCadastro.getImage().getScaledInstance(janelaCadastro.getWidth(), janelaCadastro.getHeight(), Image.SCALE_DEFAULT));
            fundoCadastro.setIcon(imagemFundoCadastro);
            fundoCadastro.setVisible(true);
            painelCadastro.add(fundoCadastro, 0);

            //CONFIGURAÇÕES DO BOTÃO LOGIN:
            login = new JButton("LOGIN");
            login.setBounds(250, 250, 200, 40);
            login.setVisible(true);
            login.setBackground(Color.white);
            painelCadastro.add(login, 0);
            
            //CONFIGURAÇÕES DO BOTÃO CADASTRO:
            cadastro = new JButton("Não possui cadastro?");
            cadastro.setBounds(250, 300, 200, 40);
            cadastro.setVisible(true);
            cadastro.setBackground(Color.white);
            painelCadastro.add(cadastro, 0);
            
            //CONFIGURAÇÕES DO BOTÃO DE INFORMAÇÕES:
            sobre = new JButton("SOBRE");
            sobre.setBounds(250, 350, 200, 40);
            sobre.setVisible(true);
            sobre.setBackground(Color.white);
            painelCadastro.add(sobre, 0);

            janelaCadastro.setVisible(true);
            janelaCadastro.requestFocus();
            
            login.addMouseListener(new MouseAdapter() {
                
                public void mousePressed(MouseEvent e){
                    String nomedigitado = JOptionPane.showInputDialog("Digite seu nome: ");
                    String passworddigitado = JOptionPane.showInputDialog("Digite seu password: ");        
                    
                    boolean verifica =conexaobd.executaSQL("select * from jogador where nome = '"+nomedigitado+"' and senha = "
                            + "'"+passworddigitado+"';");

                    if(verifica==true){
                        JOptionPane.showMessageDialog(null, regras);
                        Jogo.jogadorLogado.setPontos(0);
                        janelaCadastro.dispose();
                        new TelaJogo().setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null, "Jogador não cadastrado!");
                    } 
                    
                   
                }

                private void dispose() {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
            });
            
            
             cadastro.addMouseListener(new MouseAdapter() {
                
                public void mousePressed(MouseEvent e){
                    String nome = JOptionPane.showInputDialog("Digite seu nome: ");
                    String senha = JOptionPane.showInputDialog("Digite um password: ");
                    try {
                        PreparedStatement pst = conexaobd.conn.prepareStatement("insert into jogador (nome, senha) values (?,?)");
                        pst.setString(1, nome);
                        pst.setString(2, senha);
                        pst.executeUpdate();
                        
                        
                        JOptionPane.showMessageDialog(null, "Salvo com sucesso");
                        
                        boolean verifica =conexaobd.executaSQL("select * from jogador where nome = '"+nome+"' and senha = "
                            + "'"+senha+"';");

                        if(verifica==true){
                            JOptionPane.showMessageDialog(null, regras);
                            Jogo.jogadorLogado.setPontos(0);
                            janelaCadastro.dispose();
                            new TelaJogo().setVisible(true);
                        }

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro na insersão.");
                    }
                }
            });
             
            sobre.addMouseListener(new MouseAdapter() {
                
                public void mousePressed(MouseEvent e){
                    JOptionPane.showMessageDialog(null, "JOGO DESENVOLVIDO POR:\n\n"
                            + "Lívia Tenório Vasconcelos\n\n"
                            + "Disciplina: Programação Estruturada\n"
                            + "Prof. Jair da Silva Farias\n"
                            + "2º período - 2017/1\n\n"
                            + "SISTEMAS DE INFORMAÇÃO - CESMAC/AL", "SOBRE O JOGO", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            
            
            
    }

    void setVisible(boolean b) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
