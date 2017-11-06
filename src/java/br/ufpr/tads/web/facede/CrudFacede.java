/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.facede;

import br.ufpr.tads.web.dao.GenericDao;
import javax.validation.ValidationException;

/**
 *
 * @author Wesley
 */
public abstract class CrudFacede {
    
    protected abstract GenericDao getDao(); 
    
    protected abstract void beforeSave(GenericDao dao) throws ValidationException;
    
    protected abstract void beforeDelete(GenericDao dao);
}
