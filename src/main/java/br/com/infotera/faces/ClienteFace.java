/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.faces;

import br.com.infotera.entity.Cliente;
import br.com.infotera.services.ClienteSrv;
import br.com.infotera.utils.StatusTelaEnum;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author arquimedes
 */
@SessionScoped
@Named("ClienteFace")
public class ClienteFace implements Serializable {

    private Cliente cliente;
    private List<Cliente> clienteList;
    private StatusTelaEnum statusTelaEnum = StatusTelaEnum.LISTAR;

    @Inject
    private ClienteSrv clienteSrv;

    public ClienteFace() {
        pesquisar();
    }

    public void pesquisar() {
        statusTelaEnum = StatusTelaEnum.LISTAR;
        try {
            clienteList = clienteSrv.listarTodos();
        } catch (Exception ex) {
        }
    }

    public void adicionar() {
        statusTelaEnum = StatusTelaEnum.EDITAR;
        try {
            cliente = new Cliente();
        } catch (Exception ex) {
        }
    }

    public void editar() {
        statusTelaEnum = StatusTelaEnum.EDITAR;
        try {
            cliente = clienteSrv.buscarPorId(cliente.getId());
        } catch (Exception ex) {
        }
    }

    public void remover() {
        try {
            clienteSrv.remover(cliente);
        } catch (Exception ex) {
        }
        pesquisar();
    }

    public void salvar() {
        try {
            if (cliente.getId() == null) {
                clienteSrv.salvar(cliente);
            } else {
                clienteSrv.atualizar(cliente);
            }
        } catch (Exception ex) {
        }
        pesquisar();
    }

    public boolean isConsultarState() {
        return StatusTelaEnum.CONSULTAR.equals(statusTelaEnum);
    }

    public boolean isListarState() {
        return StatusTelaEnum.LISTAR.equals(statusTelaEnum);
    }

    public boolean isEditarState() {
        return StatusTelaEnum.EDITAR.equals(statusTelaEnum);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

}
