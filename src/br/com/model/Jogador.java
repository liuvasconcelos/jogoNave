
package br.com.model;

/**
 *
 * @author liu
 */
public class Jogador {
    private int id_jogador;
    private String nome;
    private int pontos =0;
    private int erros =0;

    public Jogador(int id_jogador, String nome, int pontos) {
        this.id_jogador = id_jogador;
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    public int getPontos() {
        return pontos;
    }

    public int getErros() {
        return erros;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setErros(int erros) {
        this.erros = erros;
    }
    
  
    public int contaPontos(){
       pontos+=10;
       return pontos;
    }

    public int getId_jogador() {
        return id_jogador;
    }

    public void setId_jogador(int id_jogador) {
        this.id_jogador = id_jogador;
    }
 
}

