package com.qhit.basePatientInfo.service;

import java.util.List;
import com.qhit.basePatientInfo.pojo.BasePatientInfo;
/**
* Created by GeneratorCode on 2018/12/21
*/

public interface IBasePatientInfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    BasePatientInfo findById(Object id);

    boolean freeUpdate(String sql);

    List<BasePatientInfo> search(BasePatientInfo basePatientInfo);

    List<BasePatientInfo> findbingrenAll(Integer $bingrenId);
}