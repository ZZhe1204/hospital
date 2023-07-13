package com.qhit.doctorMenuMedicine.service;

import java.util.List;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
/**
* Created by GeneratorCode on 2018/12/25
*/

public interface IDoctorMenuMedicineService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    DoctorMenuMedicine findById(Object id);

    List<DoctorMenuMedicine> search(DoctorMenuMedicine doctorMenuMedicine);

    List<DoctorMenuMedicine> findsMedicine(Integer $taocan);

    List<DoctorMenuMedicine> findmedicinetaocanid(DoctorMenuMedicine doctorMenuMedicine);

/*    List<DoctorMenuMedicine> findMedicine(Integer $taocan);*/

}