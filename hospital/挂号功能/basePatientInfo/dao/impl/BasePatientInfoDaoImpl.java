package com.qhit.basePatientInfo.dao.impl;

import com.qhit.basePatientInfo.dao.IBasePatientInfoDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class BasePatientInfoDaoImpl extends BaseDao implements IBasePatientInfoDao {

    @Override 
    public List findAll() { 
        String sql = "select * from base_patient_info"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from base_patient_info where patient_id='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPatientName(Object patientName) { 
        String sql = "select * from base_patient_info where patient_name='"+patientName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByBirth(Object birth) { 
        String sql = "select * from base_patient_info where birth='"+birth+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findBySex(Object sex) { 
        String sql = "select * from base_patient_info where sex='"+sex+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByAddress(Object address) { 
        String sql = "select * from base_patient_info where address='"+address+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPhonenum(Object phonenum) { 
        String sql = "select * from base_patient_info where phonenum='"+phonenum+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPersonid(Object personid) { 
        String sql = "select * from base_patient_info where personid='"+personid+"'"; 
        return freeFind(sql); 
    } 




}