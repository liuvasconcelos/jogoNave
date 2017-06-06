package conexoes;

import br.com.model.Jogador;
import br.com.view.Jogo;
import br.com.view.TelaJogo;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author liu
 */
public class ConexaoBD {
    public static Statement stm; //prepara e realiza consultas no BD
    public static ResultSet rs; //armazena o resultado de uma pesquisa
    private String driver = "org.postgresql.Driver"; //identifica o servico de BD. conecta com o banco (prepara a conexao)
    private String caminho = "jdbc:postgresql://localhost:5432/jogodenave"; //local do BD
    private String usuario= "postgres";
    private String senha = "postgres";
    public static Connection conn; //realiza a conexao com o BD
    
    public void conexao(){
        
        try {
            System.setProperty("jdbc.Drivers", driver);
            conn = DriverManager.getConnection(caminho, usuario, senha);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONECTAR\nErro: "+ex.getMessage());
        }  
    }
    
    public boolean executaSQL(String sql){//VERIFICA SE JÁ EXISTE UM CADASTRO
        boolean verifica= false;
        
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
            if(rs.next()){
            verifica = true;
            Jogo.jogadorLogado = new Jogador(rs.getInt("id_jogador"), rs.getString("nome"), rs.getInt("pontos"));
            }
            stm.close();
                       
        } catch (SQLException ex) {
     
            JOptionPane.showMessageDialog(null, "Erro!");
        }   
       return verifica;

    }
    
    public int verificaSQL(String sql){//COMPARATIVO DOS PONTOS REALIZADOS COM O SCORE
        int pontos=0;
        
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
            if(rs.next()){
            pontos = rs.getInt("pontos");
            }
            stm.close();
                       
        } catch (SQLException ex) {
     
            JOptionPane.showMessageDialog(null, "Erro!");
        }
        
        
       return pontos;

    }
    
    public String listaSQL(String sql){ //LISTA DO SCORE

        String text= "";
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_SENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
            while(rs.next()){
                text+=rs.getString("nome")+" - "+rs.getInt("pontos")+"pts<br>";
            }
                       
        } catch (SQLException ex) {
     
            JOptionPane.showMessageDialog(null, "Erro!");
        }
       
        return text;
    }
    
    public void desconecta(){
        try {
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERRO AO DESCONECTAR\nErro: "+ex.getMessage());
        }
    }
    
}
