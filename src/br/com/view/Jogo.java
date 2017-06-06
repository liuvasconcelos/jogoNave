
package br.com.view;

import br.com.model.Jogador;

import static com.sun.java.accessibility.util.GUIInitializedMulticaster.add;
import static com.sun.java.accessibility.util.TopLevelWindowMulticaster.add;
import conexoes.ConexaoBD;
import static java.awt.AWTEventMulticaster.add;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static org.jdesktop.el.impl.lang.ELArithmetic.add;
import static java.awt.AWTEventMulticaster.add;
import static java.awt.AWTEventMulticaster.add;
import static java.awt.AWTEventMulticaster.add;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.jdesktop.el.impl.lang.ELArithmetic.add;


/**
 *
 * @author liu
 */
public class Jogo {

    public static Jogador jogadorLogado;  //guarda as informações do jogador que tiver logado
    public static Score score;
    public static ConexaoBD conexaobd = new ConexaoBD();
    
    
    public static void fecha(){
        int pontos =conexaobd.verificaSQL("select pontos from jogador where id_jogador="+jogadorLogado.getId_jogador()+";");
   
        if(pontos < jogadorLogado.getPontos()){ //VERIFICA SE O SCORE É > OU < QUE O Q TA NO BANCO
            try {
                PreparedStatement pst = conexaobd.conn.prepareStatement("update jogador set pontos = ? where id_jogador= ?");
                pst.setInt(1,jogadorLogado.getPontos());
                pst.setInt(2,jogadorLogado.getId_jogador());
                pst.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na insersão.");
            }
        }
        new Score().setVisible(true);
      
    }
     
    public static void main(String[] args) { 
       conexaobd.conexao();
       new TelaCadastro().setVisible(true);
    }
}
