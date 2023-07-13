package com.qhit.doctorVisitRecordinfo.service;

import java.util.List;
import com.qhit.doctorVisitRecordinfo.pojo.DoctorVisitRecordinfo;
/**
* Created by GeneratorCode on 2018/12/28
*/

public interface IDoctorVisitRecordinfoService {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object id);

    List findAll();

    DoctorVisitRecordinfo findById(Object id);

    List<DoctorVisitRecordinfo> search(DoctorVisitRecordinfo doctorVisitRecordinfo);

}