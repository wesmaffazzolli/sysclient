/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.facede;

import br.ufpr.tads.web.dao.EmpresaDao;
import br.ufpr.tads.web.dao.GenericDao;
import br.ufpr.tads.web.model.Empresa;
import java.util.List;
import javax.validation.ValidationException;

/**
 *
 * @author Wesley
 */
public class EmpresaFacede extends CrudFacede {

    private EmpresaDao dao;

    public Boolean insert(Empresa empresa) {
        EmpresaDao dao = getDao();
        if (dao.insert(empresa)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean update(Empresa empresa) {
        EmpresaDao dao = getDao();
        if (dao.update(empresa)) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean remove(String id) {
        EmpresaDao dao = getDao();
        if (dao.remove(id)) {
            return true;
        } else {
            return false;
        }
    }

    public List<Empresa> findAll() {
        EmpresaDao dao = getDao();
        return dao.findAll();
    }

    public List<Empresa> findByName(String n) {
        EmpresaDao dao = getDao();
        return dao.findByName(n);
    }
    
    public Empresa findById(String id) {
        EmpresaDao dao = getDao();
        return dao.findById(id);
    }

    @Override
    protected EmpresaDao getDao() {
        if (dao == null) {
            dao = new EmpresaDao();
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
