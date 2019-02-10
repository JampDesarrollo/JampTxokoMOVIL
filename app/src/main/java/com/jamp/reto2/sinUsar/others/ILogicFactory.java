/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jamp.reto2.sinUsar.others;


import com.jamp.reto2.sinUsar.logic.EventLogic;
import com.jamp.reto2.sinUsar.logic.ExpenseLogic;
import com.jamp.reto2.sinUsar.logic.ProductLogic;
import com.jamp.reto2.sinUsar.logic.UserLogic;
import com.jamp.reto2.sinUsar.logicControllers.EventLogicController;
import com.jamp.reto2.sinUsar.logicControllers.ExpenseLogicController;
import com.jamp.reto2.sinUsar.logicControllers.ProductLogicController;
import com.jamp.reto2.sinUsar.logicControllers.UserLogicController;

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
     * @return the UserLogicController
     */
    public static UserLogic getUserLogic() {
        return new UserLogicController();
    }

    public static EventLogic getEventLogic() {
        return new EventLogicController();
    }
    public static ProductLogic getProductLogic() {
        return new ProductLogicController();
    }
    /**
     * Metodo que va a devolver un nuevo objeto que implementa la interfaz.
     * Method that returns the new object of the implementation
     *
     * @return the ExpenselogicController
     */
    public static ExpenseLogic getExpenseLogic(){return new ExpenseLogicController();}


}