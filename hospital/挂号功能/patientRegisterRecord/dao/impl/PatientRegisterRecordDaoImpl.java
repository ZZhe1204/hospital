package com.qhit.patientRegisterRecord.dao.impl;

import com.qhit.patientRegisterRecord.dao.IPatientRegisterRecordDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class PatientRegisterRecordDaoImpl extends BaseDao implements IPatientRegisterRecordDao {

    @Override 
    public List findAll() { 
        String sql = "select * from patient_register_record"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from patient_register_record where register_id='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPatientId(Object patientId) { 
        String sql = "select * from patient_register_record where patient_id='"+patientId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDeptId(Object deptId) { 
        String sql = "select * from patient_register_record where dept_id='"+deptId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByRegisterDate(Object registerDate) { 
        String sql = "select * from patient_register_record where register_date='"+registerDate+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByRecordUser(Object recordUser) { 
        String sql = "select * from patient_register_record where record_user='"+recordUser+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDoctorId(Object doctorId) { 
        String sql = "select * from patient_register_record where doctor_id='"+doctorId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByStatus(Object status) { 
        String sql = "select * from patient_register_record where status='"+status+"'"; 
        return freeFind(sql); 
    } 




}