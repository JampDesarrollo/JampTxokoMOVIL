/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.logic;


/**
 * Clase que devuelve objetos que implementan la interfaz. Si yo necesito
 * un objeto de la logica, hay que pedirselo a la factoria.
 * Class that returns the objects that implements the interface
 *
 * @author Julen
 * @author Ander
 * @author Markel
 * @author Paula
 */
public class ILogicFactory {

    /**
     * Metodo que va a devolver un nuevo objeto que implementa la interfaz.
     * Method that returns the new object of the implementation
     *
     * @return the logicImplementation
     */
    public static ILogic getILogic() {

        return new ILogicImplementation();

    }

}
