/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.facede;

import br.ufpr.tads.web.dao.FuncionarioDao;
import br.ufpr.tads.web.dao.GenericDao;
import java.util.List;
import javax.validation.ValidationException;

/**
 *
 * @author Wesley
 */
public class FuncionarioFacede extends CrudFacede {

    private FuncionarioDao dao = new FuncionarioDao();
    
    @Override
    protected GenericDao getDao() {
        if (dao == null) {
            dao = new FuncionarioDao();
        }
        return dao;
    }

    @Override
    protected void beforeSave(GenericDao dao) throws ValidationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void beforeDelete(GenericDao dao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
