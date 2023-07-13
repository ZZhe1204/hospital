package com.qhit.basePatientInfo.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/21
*/

public interface IBasePatientInfoDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List findByPatientName(Object patientName);

    List findByBirth(Object birth);

    List findBySex(Object sex);

    List findByAddress(Object address);

    List findByPhonenum(Object phonenum);

    List findByPersonid(Object personid);

}