/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entites.Personne;
import entites.PersonneJpaController;
import entites.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ideal-Info
 */
public class PersonneDAO {
    
    //Class DAO
    private  EntityManagerFactory emf;
private PersonneJpaController jpaController;
    public PersonneDAO() {
        emf=Persistence.createEntityManagerFactory("jpaPU2");
        jpaController=new PersonneJpaController(emf);
    }
    public void addPersonne(Personne per){
        jpaController.create(per);
    }
    public List<Personne> getList(){
       return jpaController.findPersonneEntities();
    }
    public  void removePersonne(Integer id) throws NonexistentEntityException{
        jpaController.destroy(id);
    }
}
