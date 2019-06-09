package com.core.domain;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "PERSONS")
public class Person implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    private Long identification;

    @Column(name = "FIRSTNAME1", nullable = false, length = 20)
    private String firstName1;

    @Column(name = "FIRSTNAME2")
    private String firstName2;

    @Column(name = "LASTNAME1", nullable = false, length = 20)
    private String lastName1;

    @Column(name = "LASTNAME2")
    private String lastName2;

      
    @Column(name = "BIRTHDAY")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date birthday;

    @Column(name = "CELLPHONE")
    private String cellphone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PLACE")
    private String place;

    @Column(name = "TYPEPLACE")
    private String typePlace;

    @Column(name = "BARRIO")
    private String barrio;

    @Column(name = "VOLUNTEER")
    private boolean volunteer;

//    @Column(name = "PHOTO")
    @Column(name = "LIDER")
    private Long lider;

    private String profesion;
    private String compromise;
    private Image photo;

    private boolean jury;
    private boolean witness;

    private boolean giveVehicle;
    private String typeVehicle;
    private ArrayList vehicles;

    @Column(name = "LAST_UPDATED_TIME")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date updatedTime;

    public Person() {
    }

    public Person(String firstName1, String firstName2, String lastName1, String lastName2) {
        this.firstName1 = firstName1;
        this.firstName2 = firstName2;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
    }

    public Person(Person person) {
        if (person != null) {
            this.identification = person.getIdentification();
            this.firstName1 = person.getFirstName1();
            this.firstName2 = person.getFirstName2();
            this.lastName1 = person.getLastName1();
            this.lastName2 = person.getLastName2();
            this.lider = person.getLider();
            this.cellphone = person.getCellphone();
            this.profesion = person.getProfesion();
            this.address = person.getAddress();
            this.barrio = person.getBarrio();
            this.place = person.getPlace();
            this.typePlace = person.getTypePlace();
            this.birthday = person.getBirthday();
            this.email = person.getEmail();
            
            this.witness = person.isWitness();
            this.jury = person.isJury();
            this.volunteer = person.isVolunteer();
            this.compromise = person.getCompromise();
            this.giveVehicle = person.isGiveVehicle();
            this.typeVehicle = person.getTypeVehicle();
            this.vehicles = person.getVehicles();
            this.updatedTime = person.getUpdatedTime();
            this.photo = person.getPhoto();
        }
    }

    public Long getIdentification() {
        return identification;
    }

    public void setIdentification(Long identification) {
        this.identification = identification;
    }

    public String getFirstName1() {
        return firstName1;
    }

    public void setFirstName1(String firstName1) {
        this.firstName1 = firstName1;
    }

    public String getFirstName2() {
        return firstName2;
    }

    public void setFirstName2(String firstName2) {
        this.firstName2 = firstName2;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = new Date(updatedTime);
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTypePlace() {
        return typePlace;
    }

    public void setTypePlace(String typePlace) {
        this.typePlace = typePlace;
    }

    public boolean isVolunteer() {
        return volunteer;
    }

    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;
    }

    public Long getLider() {
        return lider;
    }

    public void setLider(Long lider) {
        this.lider = lider;
    }

    public boolean isJury() {
        return jury;
    }

    public void setJury(boolean jury) {
        this.jury = jury;
    }

    public boolean isWitness() {
        return witness;
    }

    public void setWitness(boolean witness) {
        this.witness = witness;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public void setPhoto(byte[] photoBs) {
        if (photoBs != null) {
            this.photo = Toolkit.getDefaultToolkit().createImage(photoBs);
        }
    }

    public String getCompromise() {
        return compromise;
    }

    public void setCompromise(String compromise) {
        this.compromise = compromise;
    }

    public boolean isGiveVehicle() {
        return giveVehicle;
    }

    public void setGiveVehicle(boolean giveVehicle) {
        this.giveVehicle = giveVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    public ArrayList getVehicles() {
        return vehicles;
    }

    public String getFullName() {
        return firstName1 + " " + (firstName2 != null ? firstName2 : "") + " " + lastName1 + " " + (lastName2 != null ? lastName2 : "");
    }

    public String getFullNameRv() {
        return lastName1 + " " + (lastName2 != null ? lastName2 : "") + " " + firstName1 + " " + (firstName2 != null ? firstName2 : "");
    }

    public String getNames() {
        return firstName1 + " " + firstName2;
    }

    public String getShortName() {
        return firstName1 + " " + lastName1;
    }

    public boolean isLider() {
        return lider == null || lider == 0L;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Identificacion: ").append(identification);
        str.append("\nApellidos: ").append(lastName1).append(" ").append(lastName2);
        str.append("\nNombres: ").append(firstName1).append(" ").append(firstName2);
        str.append("\nFecha Nac: ").append(birthday);
        str.append("\nCelular: ").append(cellphone);
        str.append("\nResponsable: ").append(address);
        str.append("\nBarrio: ").append(barrio);
        str.append("\nLugar: ").append(place);
        str.append("\nTipo Lugar: ").append(typePlace);
        str.append("\nJurado?: ").append(jury);
        str.append("\nTestigo?: ").append(witness);
        str.append("\nvoluntario?: ").append(volunteer);
        str.append("\nProfesion: ").append(profesion);
        str.append("\nCompromiso: ").append(compromise);
        str.append("\nLider: ").append(lider);

        return str.toString();
    }

}
