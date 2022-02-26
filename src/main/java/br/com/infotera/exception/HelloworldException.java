/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.exception;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;

/**
 *
 * @author arquimedes
 */
@ApplicationException(rollback = true)
public class HelloworldException extends Exception {

    private static final long serialVersionUID = 1L;

    private Object[] expectionEnumParameters = null;
    private String mensagem = null;
    private List<String> mensagens = null;
    private String dsStackTrace = null;

    private static Logger logger;
    private static ResourceBundle msg;

    static {
        logger = Logger.getLogger(HelloworldException.class.getName());
    }
    //NOVOS CONSTRUTORES OS DEMAIS SERÃO CANCELADOS

    public HelloworldException(String message) {
        super(message);
    }

    public HelloworldException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getMensagem() {
        return mensagem;
    }

    public Object[] getExpectionEnumParameters() {
        return expectionEnumParameters;
    }

    public void setExpectionEnumParameters(Object[] expectionEnumParameters) {
        this.expectionEnumParameters = expectionEnumParameters;
    }

    public String getDsStackTrace() {
        return dsStackTrace;
    }
}
