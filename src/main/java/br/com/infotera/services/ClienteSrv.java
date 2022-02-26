/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.services;

import br.com.infotera.entity.Cliente;
import br.com.infotera.exception.HelloworldException;
import br.com.infotera.utils.DAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author arquimedes
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ClienteSrv extends DAO<Cliente> {

    public Cliente buscarPorId(Integer id) throws HelloworldException {
        return singleByNamed("Cliente.buscarPorId", id);
    }

    public List<Cliente> listarTodos() throws HelloworldException {
        return listByNamed("Cliente.listarTodos");
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void salvar(Cliente cliente) throws HelloworldException {
        save(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizar(Cliente cliente) throws HelloworldException {
        update(cliente);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Cliente cliente) throws HelloworldException {
        remove(cliente);
    }
}
