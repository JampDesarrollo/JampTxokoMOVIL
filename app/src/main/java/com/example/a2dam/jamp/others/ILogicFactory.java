/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.a2dam.jamp.others;
import com.example.a2dam.jamp.logicControllers.EventLogicController;
import com.example.a2dam.jamp.logicControllers.ExpenseLogicController;
import com.example.a2dam.jamp.logicControllers.TelephoneLogicController;
import com.example.a2dam.jamp.logicControllers.UserLogicController;
import com.example.a2dam.jamp.logics.EventLogic;
import com.example.a2dam.jamp.logics.ExpenseLogic;
import com.example.a2dam.jamp.logics.ProductLogic;
import com.example.a2dam.jamp.logics.TelephoneLogic;
import com.example.a2dam.jamp.logics.UserLogic;

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
    public static UserLogic getUserLogic() {
        return new UserLogicController();
    }

    public static EventLogic getEventLogic() {
        return new EventLogicController();
    }

    public static ProductLogic getProductLogic() {
        return new UserLogicController();
    }

    public static ExpenseLogic getExpenseLogic(){return new ExpenseLogicController();}

    public static TelephoneLogic getTelephoneLogic() {
        return new TelephoneLogicController();
    }

}