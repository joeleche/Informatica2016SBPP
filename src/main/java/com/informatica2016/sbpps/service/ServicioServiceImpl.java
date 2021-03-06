/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.informatica2016.sbpps.service;

import com.informatica2016.sbpps.entity.Servicio;
import com.informatica2016.sbpps.repository.ServicioRepository;
import com.informatica2016.sbpps.util.Criterio;
import com.informatica2016.sbpps.util.Paginado;
import java.util.List;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CARLOS
 */
@Service
public class ServicioServiceImpl extends BaseServiceImpl<Servicio, Integer>
        implements ServicioService {

    @Autowired
    private ServicioRepository serviciorepository;

    @Autowired
    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        super(servicioRepository);
    }

    @Override
    public Paginado getPaginationList(Integer page, Integer size, String search) {
        Criterio criterio = Criterio.forClass(Servicio.class);
        criterio.add(Restrictions.like("nombre", search, MatchMode.ANYWHERE));
        Long totalRows = serviciorepository.countResultForCriteria(criterio);

        criterio.setFirstResult(size * (page - 1));
        criterio.setMaxResults(size);

        List<Servicio> items = serviciorepository.searchForCriteria(criterio);

        return Paginado.build(page, size, search, totalRows, items);
    }
}

//    
//    @Override
//    public List<Servicio> listarTodos() {
//        return serviciorepository.listarTodos();
//    }
