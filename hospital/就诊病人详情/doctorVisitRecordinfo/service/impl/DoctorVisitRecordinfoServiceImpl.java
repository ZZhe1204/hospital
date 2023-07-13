package com.qhit.doctorVisitRecordinfo.service.impl;

import com.qhit.doctorVisitRecordinfo.service.IDoctorVisitRecordinfoService;
import java.util.List;
import com.qhit.doctorVisitRecordinfo.dao.IDoctorVisitRecordinfoDao;
import com.qhit.doctorVisitRecordinfo.pojo.DoctorVisitRecordinfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource; 

/**
* Created by GeneratorCode on 2018/12/28
*/

@Service 
public class DoctorVisitRecordinfoServiceImpl  implements IDoctorVisitRecordinfoService {

    @Resource 
    IDoctorVisitRecordinfoDao dao;

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
        DoctorVisitRecordinfo doctorVisitRecordinfo = findById(id); 
        return dao.delete(doctorVisitRecordinfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public DoctorVisitRecordinfo findById(Object id) { 
        List<DoctorVisitRecordinfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public List<DoctorVisitRecordinfo> search(DoctorVisitRecordinfo doctorVisitRecordinfo) { 
        return dao.search(doctorVisitRecordinfo); 
    } 


}