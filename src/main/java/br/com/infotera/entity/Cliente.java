/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.entity;

import java.io.Serializable;
import java.util.Random;
import javax.persistence.*;

/**
 *
 * @author Arquimedes
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Cliente.buscarPorId",
            query = "SELECT c FROM Cliente c WHERE c.id = :p1"),
    @NamedQuery(name = "Cliente.listarTodos",
            query = "SELECT c FROM Cliente c")})
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nm_cliente")
    private String nmCliente;
    @Column(name = "nm_email")
    private String nmEmail;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getNmEmail() {
        return nmEmail;
    }

    public void setNmEmail(String nmEmail) {
        this.nmEmail = nmEmail;
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return (-new Random().nextInt(100000000));
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if (this != other || (this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.infotera.entity.Cliente[id=" + id + "]";
    }
}
