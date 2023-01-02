package com.hes.lis.mltd.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hes.lis.mltd.vo.HesDemandRequestDetails;
import com.hes.lis.mltd.vo.Masterdata;
import com.hes.lis.mltd.vo.MeterReplica;
import com.hes.lis.mltd.vo.VeryFirstCommunication;


/**
 * 
 * @author psvr
 *
 */
public class MeterRuleEngineDAO extends BaseHibernateDAO{
	
	public Masterdata fetchMeterMasterData(String meterSerialNumber){
		Masterdata meterdataObj=null;
		Session session=null;

		try{
			session=getSession();
			Query<?> query=session.createQuery("from Masterdata where meterNo='"+meterSerialNumber+"'");
			meterdataObj=(Masterdata) query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session.isOpen())
				session.close();
		}
		return meterdataObj;
	}
	
	
	public MeterReplica fetchMeterReplica(String meterSerialNumber){
		MeterReplica meterReplica=null;
		Session session=null;

		try{
			session=getSession();
			Query<?> query=session.createQuery("from MeterReplica where meterSerialNumber='"+meterSerialNumber+"'");
			meterReplica=(MeterReplica) query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session.isOpen())
				session.close();
		}
		return meterReplica;
	}
	
	public VeryFirstCommunication fetchMeterVeryFirstComm(String meterSerialNumber){
		VeryFirstCommunication firstComm=null;
		Session session=null;

		try{
			session=getSession();
			Query<?> query=session.createQuery("from VeryFirstCommunication where meterSerialNumber='"+meterSerialNumber+"'");
			firstComm=(VeryFirstCommunication) query.uniqueResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session.isOpen())
				session.close();
		}
		return firstComm;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<HesDemandRequestDetails> fetchHesDemandRequestDetails(String meterSerialNumber){
		List<HesDemandRequestDetails> onDemandList=null;
		Session session=null;

		try{
			session=getSession();
			Query<?> query=session.createQuery("from HesDemandRequestDetails where meterSerialNumber='"+meterSerialNumber+"' and requestStatus='REQUEST_SET'");
			onDemandList=(List<HesDemandRequestDetails>) query.list();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(session.isOpen())
				session.close();
		}
		return onDemandList;
	}

	public boolean saveObject(Object object){
		Session session=null;
		Transaction tr=null;
		boolean returnStatus=false;
		
		try{
			session=getSession();
			tr=session.beginTransaction();
			session.save(object);
			tr.commit();
			returnStatus=true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}finally{
			if(session.isOpen())
				session.close();
		}
		
		return returnStatus;
	}
	
	
	public boolean saveOrUpdateObject(Object object){
		Session session=null;
		Transaction tr=null;
		boolean returnStatus=false;
		
		try{
			session=getSession();
			tr=session.beginTransaction();
			session.saveOrUpdate(object);
			tr.commit();
			returnStatus=true;
		}catch (Exception e) {
			tr.rollback();
			e.printStackTrace();
		}finally{
			if(session.isOpen())
				session.close();
		}
		
		return returnStatus;
	}
	
	
	
	
	public static void main(String[] args) {
		
		try {
			
			String meterNumber="20417901";
			
//			Masterdata meterdataObj=new MeterRuleEngineDAO().fetchMeterMasterData(meterNumber);
//			System.out.println(meterdataObj);
			
//			VeryFirstCommunication fetchMeterVeryFirstComm=new MeterRuleEngineDAO().fetchMeterVeryFirstComm(meterNumber);
//			System.out.println(fetchMeterVeryFirstComm);
			
			
			List<HesDemandRequestDetails> onDemandList=new MeterRuleEngineDAO().fetchHesDemandRequestDetails(meterNumber) ;
			System.out.println(onDemandList.size());
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
