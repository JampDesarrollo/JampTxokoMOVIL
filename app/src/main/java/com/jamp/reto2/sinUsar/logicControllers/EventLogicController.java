package com.jamp.reto2.sinUsar.logicControllers;

import com.jamp.reto2.dataClasses.EventBean;
import com.jamp.reto2.sinUsar.exceptions.BusinessLogicException;
import com.jamp.reto2.sinUsar.exceptions.IdNotOkException;
import com.jamp.reto2.sinUsar.exceptions.NameNotOkException;
import com.jamp.reto2.sinUsar.logic.EventLogic;
import com.jamp.reto2.sinUsar.rest.EventRESTClient;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Collection;
import java.util.List;

public class EventLogicController implements EventLogic {

    private EventRESTClient webClient;
    private static final Logger LOGGER = Logger.getLogger("javafxapplicationud3example");

    /**
     * Create a EventLogicController object. It constructs a web client for
     * accessing a RESTful service that provides business logic in an application
     * server.
     */
    public EventLogicController() {
        webClient = new EventRESTClient();
    }
    /**
     * Method to delete an event
     * @param event The event we want to delete
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public void deleteEvent(EventBean event) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "Deleting event");
            webClient.deleteEvent(event.getIdEvent());
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception deleting exceptiion, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error deleting exception" + e.getMessage());
        }
    }
    /**
     * Method to create an event
     * @param event the event we want to create
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public void createEvent(EventBean event) throws BusinessLogicException {
        try {
            LOGGER.log(Level.INFO, "Creating event {0}."+event.getName());
            //Send user data to web client for creation.
            webClient.createEvent(event);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception creating event, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error creating event" + e.getMessage());

        }
    }
    /**
     * Method to find all events of my txoko
     * @param idTxoko the id of our txoko
     * @return it returns a collection of the events
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public Collection<EventBean> findAllEvents(String idTxoko) throws BusinessLogicException {
        List<EventBean> events = null;
        try {
            LOGGER.info("EventsManager: Finding all events of my txoko from REST service (XML).");
            events = webClient.findAllEvents(new GenericType<List<EventBean>>() {
            }, idTxoko);
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all EVENTS, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all EVENTS:\n" + e.getMessage());
        }

        return events;
    }
    /**
     * Method to find an event by the id and the txoko
     * @param idEvent the id of the event
     * @param idTxoko the id of the txoko
     * @return it returns an event
     * @throws IdNotOkException throws this exceptions if the id of the event is wrong
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public EventBean findEventByIdByTxoko(String idEvent, String idTxoko) throws IdNotOkException, BusinessLogicException {
        EventBean event = null;
        try {
            LOGGER.info("EventsManager: Finding finding an event of my txoko by id from REST service (XML).");
            event = webClient.findEventByIdByTxoko(EventBean.class, idEvent, idTxoko);
            if (event == null) {
                throw new IdNotOkException("ID DE EVENTO INCORRECTO");
            }
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding event by id, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Exception finding event by id" + e.getMessage());
        }
        return event;
    }
    /**
     * Method to find an event by the name and the txoko
     * @param name the name of the txoko
     * @param idTxoko the id of the txoko
     * @return it returns an event
     * @throws NameNotOkException throws this exceptions if the name of the event is wrong
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public EventBean findEventByName(String name, String idTxoko) throws NameNotOkException, BusinessLogicException {
        EventBean event = null;
        try {
            LOGGER.info("EventsManager: Finding finding an event of my txoko by name from REST service (XML).");
            event = webClient.findEventByName(EventBean.class, name, idTxoko);
            if (event == null) {
                throw new NameNotOkException("NOMBRE DE EVENTO INCORRECTO");
            }
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding event by name, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Exception finding event by name" + e.getMessage());
        }
        return event;
    }
    /**
     * Method to get ALL the events
     * @return it returns a collection of the events
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public Collection<EventBean> getAllEvents() throws BusinessLogicException {
        List<EventBean> events = null;
        try {
            LOGGER.info("EventsManager: Finding all events from REST service (XML).");
            events = webClient.findAll(new GenericType<List<EventBean>>() {
            });
        } catch (ClientErrorException e) {
            LOGGER.log(Level.SEVERE,
                    "EventsManager: Exception finding all events, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error finding all events:\n" + e.getMessage());
        }

        return events;
    }
    /**
     * Method to update an event
     * @param event the event we want to change
     * @throws BusinessLogicException throws this exceptions if something is wrong.
     */
    @Override
    public void updateEvent(EventBean event) throws BusinessLogicException {

        try{
            LOGGER.log(Level.INFO,"UsersManager: Updating user {0}.");
            webClient.update(event);
        }catch(ClientErrorException e){
            LOGGER.log(Level.SEVERE,
                    "UsersManager: Exception updating event, {0}",
                    e.getMessage());
            throw new BusinessLogicException("Error updating event:\n"+e.getMessage());
        }
    }

}
