package com.hes.data.parser.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

public class MeterRuleEngineDAO extends BaseHibernateDAO {

    public void fetchMeterVeryFirstComm(String meterSerialNumber) {
        Session session = null;

        try {
            session = getSession();
            Query query = session.createSQLQuery("select * from VERY_FIRST_COMMUNICATION");
            List<?> objectList = query.list();

            System.out.println(objectList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

    }

    public String saveObject(Object object) {
        Session session = null;
        Transaction tr = null;
        String returnStatus = "false";

        try {
            session = getSession();
            tr = session.beginTransaction();
            session.save(object);
            tr.commit();
            returnStatus = "true";
        } catch (ConstraintViolationException e) {
            returnStatus = "duplicate";
            e.printStackTrace();
            tr.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            if (e.getMessage().contains("ConstraintViolationException")) {
                returnStatus = "duplicate";
            }
            tr.rollback();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return returnStatus;
    }

    public boolean saveOrUpdateObject(Object object) {
        Session session = null;
        Transaction tr = null;
        boolean returnStatus = false;

        try {
            session = getSession();
            tr = session.beginTransaction();
            session.saveOrUpdate(object);
            tr.commit();
            returnStatus = true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }

        return returnStatus;
    }

    public static void main(String[] args) {

        try {
            new MeterRuleEngineDAO().fetchMeterVeryFirstComm("dfsf");
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
