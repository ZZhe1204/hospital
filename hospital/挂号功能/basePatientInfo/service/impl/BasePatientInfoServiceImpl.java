package com.qhit.basePatientInfo.service.impl;

import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import java.util.List;
import com.qhit.basePatientInfo.dao.IBasePatientInfoDao;
import com.qhit.basePatientInfo.dao.impl.BasePatientInfoDaoImpl;
import com.qhit.basePatientInfo.pojo.BasePatientInfo;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class BasePatientInfoServiceImpl  implements IBasePatientInfoService {

    IBasePatientInfoDao dao = new BasePatientInfoDaoImpl();

    @Override 
    public boolean insert(Object object) { 
        return dao.insert(object); 
    } 


    @Override 
    public boolean update(Object object) { 
        return dao.update(object); 
    } 


    @Override 
    public boolean updateSelective(Object object) { 
        return dao.updateSelective(object); 
    } 


    @Override 
    public boolean delete(Object id) { 
        BasePatientInfo basePatientInfo = findById(id); 
        return dao.delete(basePatientInfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BasePatientInfo findById(Object id) { 
        List<BasePatientInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<BasePatientInfo> search(BasePatientInfo basePatientInfo) {
            String sql = "select * from base_patient_info where 1=1 "; 
            if (basePatientInfo.getPatientName()!=null && !"".equals(basePatientInfo.getPatientName())){        
                sql+=" and patient_name like '%"+basePatientInfo.getPatientName()+"%' ";        
            } 
            if (basePatientInfo.getBirth()!=null && !"".equals(basePatientInfo.getBirth())){        
                sql+=" and birth like '%"+basePatientInfo.getBirth()+"%' ";        
            } 
            if (basePatientInfo.getSex()!=null && !"".equals(basePatientInfo.getSex())){        
                sql+=" and sex like '%"+basePatientInfo.getSex()+"%' ";        
            } 
            if (basePatientInfo.getAddress()!=null && !"".equals(basePatientInfo.getAddress())){        
                sql+=" and address like '%"+basePatientInfo.getAddress()+"%' ";        
            } 
            if (basePatientInfo.getPhonenum()!=null && !"".equals(basePatientInfo.getPhonenum())){        
                sql+=" and phonenum like '%"+basePatientInfo.getPhonenum()+"%' ";        
            } 
            if (basePatientInfo.getPersonid()!=null && !"".equals(basePatientInfo.getPersonid())){        
                sql+=" and personid like '%"+basePatientInfo.getPersonid()+"%' ";        
            } 
            List<BasePatientInfo> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<BasePatientInfo> findbingrenAll(Integer $bingrenId) {
        String sql = "SELECT * from base_patient_info WHERE patient_id = '"+$bingrenId+"'";
        return dao.freeFind(sql);
    }


}