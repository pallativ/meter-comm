package com.hes.data.parser.dao;

import org.hibernate.Session;

/**
 *
 * @author psvr
 *
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {

    @Override
    public Session getSession() {
        return HibernateSessionFactory.getSession();
    }

}
