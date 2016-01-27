/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author sony
 */
@Stateless
public class usersManager {
    @PersistenceContext private EntityManager um;
     public List<Users> getAll(){
          TypedQuery <Users> query = um.createQuery("select u from Users u ",Users.class);
         List<Users> result = query.getResultList();
//          for(Users u:result){                  
//              System.out.println(u.getUserName());
//          }
                  return result;
//               
     }
    public Boolean checkPassword(String userName,String password){
         TypedQuery query = um.createQuery("select u from Users u ",Users.class);
          List<Users> result = query.getResultList();
		for(Users u:result){
                  System.out.println(u.getUserName());
                    if(u.getUserName().equals(userName)&&u.getPassword().equals(password)){
                       System.out.println(u.getPassword());       
                      return true;  
                   }
               }
		     return false; 
    }
    public void createAUser(String userName,String password){
          Users u=new Users();
          u.setUserName(userName);
          u.setPassword(password);
          um.persist(u);
    }
    }

