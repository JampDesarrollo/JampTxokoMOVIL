  /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package com.example.a2dam.jamp.dataClasses;

  import java.io.Serializable;
  import javafx.beans.property.SimpleStringProperty;

  /**
   *
   * @author Julen
   */
  public class TelephoneBean implements Serializable{
    /**
     *
     */
    private SimpleStringProperty idTelephone;

    /**
     *
     */
    private SimpleStringProperty name;
    /**
     *
     */
    private SimpleStringProperty telephone;

    /**
     *
     */
    private SimpleStringProperty description;

    /**
     *
     */
    private SimpleStringProperty town;

    /**
     *
     */
    public TelephoneBean(){
      this.idTelephone = new SimpleStringProperty();
      this.description = new SimpleStringProperty();
      this.telephone = new SimpleStringProperty();
      this.name = new SimpleStringProperty();
      this.town = new SimpleStringProperty();
    }

    /**
     *
     * @param name
     * @param description
     * @param telephone
     * @param town
     */
    public TelephoneBean(String name,
                         String description,
                         String telephone,
                         String town){
      this.name=new SimpleStringProperty(name);
      this.description=new SimpleStringProperty(description);
      this.telephone=new SimpleStringProperty(telephone);
      this.town = new SimpleStringProperty(town);
    }

    /**
     * @return the id
     */
    public String getId() {
      return this.idTelephone.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(String idTelephone) {
      this.idTelephone.set(idTelephone);
    }

    /**
     * @return the nombre
     */
    public String getName() {
      return this.name.get();
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
      this.name.set(nombre);
    }

    /**
     * @return the telephon
     */
    public String getTelephone() {
      return this.telephone.get();
    }

    /**
     * @param telephone
     * @param telephon the telephon to set
     */
    public void setTelephone(String telephone) {
      this.telephone.set(telephone);
    }

    /**
     * @return the description
     */
    public String getDescription() {
      return this.description.get();
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
      this.description.set(description);
    }

    /**
     *
     * @return
     */
    public String getTown() {
      return this.town.get();
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
      this.town.set(town);
    }

    public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof TelephoneBean)) {
        return false;
      }
      TelephoneBean other = (TelephoneBean) object;
      if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
        return false;
      }
      if ((this.getName() == null && other.getName() != null) || (this.getName() != null && !this.getName().equals(other.getName()))) {
        return false;
      }
      if ((this.getDescription()== null && other.getDescription()!= null) || (this.getDescription() != null && !this.getDescription().equals(other.getDescription()))) {
        return false;
      }
      if ((this.getTelephone()== null && other.getTelephone()!= null) || (this.getTelephone() != null && !this.getTelephone().equals(other.getTelephone()))) {
        return false;
      }
      if ((this.getTown()== null && other.getTown()!= null) || (this.getTown() != null && !this.getTown().equals(other.getTown()))) {
        return false;
      }

      return true;
    }

  }
