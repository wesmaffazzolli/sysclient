/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Wesley
 * @param <T>
 */
public interface GenericDao<T> {
    public List<T> findAll();
    
    public List<T> findByName(String n);
    
    public T findById(String id);
    
    public boolean insert(T ob);
    
    public boolean update(T ob);
    
    public boolean remove(String id);
}

