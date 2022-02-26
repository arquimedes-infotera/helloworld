/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.utils;

/**
 *
 * @author Arquimedes
 */
public enum StatusTelaEnum {

    LISTAR(1),
    ADICIONAR(3),
    EDITAR(4),
    CONSULTAR(5);
    private Integer id;

    StatusTelaEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
