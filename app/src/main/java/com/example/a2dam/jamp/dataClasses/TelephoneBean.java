  /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package com.example.a2dam.jamp.dataClasses;

  import java.io.Serializable;

  /**
   *
   * @author Julen
   */
  public class TelephoneBean implements Serializable{
    /**
     *
     */
    private String idTelephone;

    /**
     *
     */
    private String name;
    /**
     *
     */
    private String telephone;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String town;

    /**
     *
     */
    public TelephoneBean(){
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
      this.name=name;
      this.description=description;
      this.telephone=telephone;
      this.town = town;
    }

    /**
     * @return the id
     */
    public String getId() {
      return this.idTelephone;
    }

    /**
     * @param idTelephone the id to set
     */
    public void setId(String idTelephone) {
      this.idTelephone=idTelephone;
    }

    /**
     * @return the nombre
     */
    public String getName() {
      return this.name;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setName(String nombre) {
      this.name=nombre;
    }

    /**
     * @return the telephon
     */
    public String getTelephone() {
      return this.telephone;
    }

    /**
     * @param telephone
     * @param telephone the telephon to set
     */
    public void setTelephone(String telephone) {
      this.telephone=telephone;
    }

    /**
     * @return the description
     */
    public String getDescription() {
      return this.description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
      this.description=description;
    }

    /**
     *
     * @return
     */
    public String getTown() {
      return this.town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
      this.town=town;
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
        return (this.getTown() != null || other.getTown() == null) && (this.getTown() == null || this.getTown().equals(other.getTown()));
    }

  }
