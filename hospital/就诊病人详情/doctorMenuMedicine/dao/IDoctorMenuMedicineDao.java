package com.qhit.doctorMenuMedicine.dao;

import org.springframework.stereotype.Repository;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
import java.util.List;

/**
* Created by GeneratorCode on 2018/12/25
*/

@Repository  
public interface IDoctorMenuMedicineDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    boolean freeUpdate(String sql);

    List<DoctorMenuMedicine> search(DoctorMenuMedicine doctorMenuMedicine);

    List findByMenuId(Object menuId);

    List findByCodeId(Object codeId);

    List findByAmt(Object amt);

    List<DoctorMenuMedicine> distributeLeft(Integer menuId);

    List<DoctorMenuMedicine> distributeRight(Integer menuId);

    List<DoctorMenuMedicine> findsMedicine(Integer $taocan);

    List<DoctorMenuMedicine> findmedicinetaocanid(DoctorMenuMedicine doctorMenuMedicine);
}