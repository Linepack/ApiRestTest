package org.linepack.apiresttest.rest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.linepack.apiresttest.model.Mensagem;
import org.linepack.apiresttest.model.Usuario;

/**
 *
 * @author leandro
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuario",
    "mensagem"
})
@XmlRootElement(name = "dados")
public class UsuarioRest {

    @XmlElement(name = "usuario", required = true)
    protected Usuario usuario;

    @XmlElement(name = "mensagem", required = true)
    protected Mensagem mensagem;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

}
