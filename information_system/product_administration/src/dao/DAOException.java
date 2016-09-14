/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author milba845
 */
public class DAOException extends RuntimeException {

    /**
     * Creates a new instance of <code>DAOException</code> without detail
     * message.
     * @param message
     */
    public DAOException(String message) {
       super(message); 
    }

    /**
     * Constructs an instance of <code>DAOException</code> with the specified
     * detail message.
     *
     * @param mesg
     * @param cause
     */
    public DAOException(String mesg, Exception cause) {
        super(mesg);
    }
}
