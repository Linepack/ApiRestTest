/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.linepack.apiresttest.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author leandro
 */
@XmlRootElement(name = "dados")
public class Mensagem implements Serializable {

    private String texto;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "texto=" + texto + '}';
    }

}
