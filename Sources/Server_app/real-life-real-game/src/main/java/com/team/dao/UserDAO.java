/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.team.dao;

import com.team.domain.User;
import com.team.exeption.DaoExeption;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO extends BaseHibernateDAO<User>{
     public User login(String username,String password) throws DaoExeption{
         String value[] = {username,password};
         return findUniqueByQuery("from User where username = ? and  password = ? ", value);
     }
}
