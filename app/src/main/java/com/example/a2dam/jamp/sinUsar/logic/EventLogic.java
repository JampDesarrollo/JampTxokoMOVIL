package com.example.a2dam.jamp.sinUsar.logic;
import com.example.a2dam.jamp.exceptions.NameNotOkException;
import com.example.a2dam.jamp.exceptions.IdNotOkException;
import com.example.a2dam.jamp.dataClasses.EventBean;
import com.example.a2dam.jamp.sinUsar.exceptions.BusinessLogicException;

import java.util.Collection;

/**
 * Logic interface. To do a call between two objects of different classes we need one.
 *
 * @author Paula
 */
public interface EventLogic {
    /**
     * Method to delete an event
     * @param event The event we want to delete
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public void deleteEvent(EventBean event) throws BusinessLogicException;
    /**
     * Method to create an event
     * @param event the event we want to create
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public void createEvent(EventBean event) throws BusinessLogicException;
    /**
     * Method to find all events of my txoko
     * @param idTxoko the id of our txoko
     * @return it returns a collection of the events
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */

    public Collection<EventBean> findAllEvents(String idTxoko) throws BusinessLogicException;
    /**
     * Method to find an event by the id and the txoko
     * @param idEvent the id of the event
     * @param idTxoko the id of the txoko
     * @return it returns an event
     * @throws IdNotOkException throws this exceptions if the id of the event is wrong
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public EventBean findEventByIdByTxoko(String idEvent, String idTxoko) throws IdNotOkException, BusinessLogicException;
    /**
     * Method to find an event by the name and the txoko
     * @param name the name of the txoko
     * @param idTxoko the id of the txoko
     * @return it returns an event
     * @throws NameNotOkException throws this exceptions if the name of the event is wrong
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public EventBean findEventByName(String name, String idTxoko) throws NameNotOkException, BusinessLogicException;
    /**
     * Method to get ALL the events
     * @return it returns a collection of the events
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public Collection<EventBean> getAllEvents() throws BusinessLogicException;
    /**
     * Method to update an event
     * @param event the event we want to change
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    public void updateEvent(EventBean event) throws BusinessLogicException;
}