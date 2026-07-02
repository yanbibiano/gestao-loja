/**
 * © 2026 Yan Deryc Rodrigues Bibiano. Todos os direitos reservados.
 *
 * Este arquivo é parte integrante do Sistema de Gestão de Lojas.
 * Desenvolvido originalmente como portfólio de engenharia de software.
 *
 * @author Yan Deryc Rodrigues Bibiano
 * @version 1.0
 */
package br.com.gestaoloja.modelo;

public class Usuario {

    private int id;
    private int lojaId;
    private String nome;
    private String cargo;
    private String senhaHash;

    public Usuario() {
    }

    public Usuario(int id, int lojaId, String nome, String cargo, String senhaHash ) {
        this.id = id;
        this.lojaId = lojaId;
        this.nome = nome;
        this.cargo = cargo;
        this.senhaHash = senhaHash;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getLojaId(){
        return lojaId;
    }
    public void setLojaId(int lojaId){
        this.lojaId = lojaId;
    }

    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCargo(){
        return cargo;
    }
    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public String getSenhaHash(){
        return senhaHash;
    }
    public void setSenhaHash(String senhaHash){
        this.senhaHash = senhaHash;
    }

}
