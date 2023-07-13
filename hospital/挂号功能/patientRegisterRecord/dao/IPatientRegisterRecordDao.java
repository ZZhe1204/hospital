package com.qhit.patientRegisterRecord.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/

public interface IPatientRegisterRecordDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByPatientId(Object patientId);

    List findByDeptId(Object deptId);

    List findByRegisterDate(Object registerDate);

    List findByRecordUser(Object recordUser);

    List findByDoctorId(Object doctorId);

    List findByStatus(Object status);

}