package com.hes.data.parser.dao;

import org.hibernate.Session;

/**
 *
 * @author psvr
 *
 */
public interface IBaseHibernateDAO {

    public Session getSession();
}
