/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.persistence.dao;

import com.core.domain.Person;
import java.util.ArrayList;

/**
 *
 * @author ballestax
 */
public interface PersonDAO {
    
   

    public Person getPerson(Long identification) throws DAOException;

    public ArrayList<Person> getPersonList() throws DAOException;

    public void addPerson(Person person) throws DAOException;

    public void deletePerson(Long identication) throws DAOException;

    public void updatePerson(Person person) throws DAOException;

}
