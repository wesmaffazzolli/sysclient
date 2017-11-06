/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.dao;

import java.util.List;

/**
 *
 * @author Wesley
 * @param <T>
 */
public abstract class GenericDao<T> {
    public abstract List<T> findAll();
    
    public abstract List<T> findByName(String n);
    
    public abstract T findById(String id);
    
    public abstract boolean insert(T ob);
    
    public abstract boolean update(T ob);
    
    public abstract boolean remove(String id);
}

