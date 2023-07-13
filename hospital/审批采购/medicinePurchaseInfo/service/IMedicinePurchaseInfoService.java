package com.qhit.medicinePurchaseInfo.service;

import java.util.List;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;

import javax.servlet.http.HttpSession;

/**
* Created by GeneratorCode on 2018/12/05
*/

public interface IMedicinePurchaseInfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    MedicinePurchaseInfo findById(Object id);

    boolean freeUpdate(String sql);

    List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo);

    void collection(HttpSession session);

    List<MedicinePurchaseInfo> findsall();

    MedicinePurchaseInfo findbyname(Integer pchUserid);

    List<MedicineReqPlan> findlist();

    List<MedicinePurchaseInfo> findalls();

    MedicinePurchaseInfo findBycodeid(MedicineReqPlan medicineReqPlan);
}