package com.qhit.patientRegisterRecord.service.impl;

import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService;
import java.util.List;
import com.qhit.patientRegisterRecord.dao.IPatientRegisterRecordDao;
import com.qhit.patientRegisterRecord.dao.impl.PatientRegisterRecordDaoImpl;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;

/**
* Created by GeneratorCode on 2018/12/21
*/

public class PatientRegisterRecordServiceImpl  implements IPatientRegisterRecordService {

    IPatientRegisterRecordDao dao = new PatientRegisterRecordDaoImpl();

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
        PatientRegisterRecord patientRegisterRecord = findById(id); 
        return dao.delete(patientRegisterRecord); 
    } 

    @Override 
    public PatientRegisterRecord findById(Object id) { 
        List<PatientRegisterRecord> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<PatientRegisterRecord> search(PatientRegisterRecord patientRegisterRecord) {
            String sql = "select * from patient_register_record where 1=1 "; 
            if (patientRegisterRecord.getPatientId()!=null && !"".equals(patientRegisterRecord.getPatientId())){        
                sql+=" and patient_id like '%"+patientRegisterRecord.getPatientId()+"%' ";        
            } 
            if (patientRegisterRecord.getDeptId()!=null && !"".equals(patientRegisterRecord.getDeptId())){        
                sql+=" and dept_id like '%"+patientRegisterRecord.getDeptId()+"%' ";        
            } 
            if (patientRegisterRecord.getRegisterDate()!=null && !"".equals(patientRegisterRecord.getRegisterDate())){        
                sql+=" and register_date like '%"+patientRegisterRecord.getRegisterDate()+"%' ";        
            } 
            if (patientRegisterRecord.getRecordUser()!=null && !"".equals(patientRegisterRecord.getRecordUser())){        
                sql+=" and record_user like '%"+patientRegisterRecord.getRecordUser()+"%' ";        
            } 
            if (patientRegisterRecord.getDoctorId()!=null && !"".equals(patientRegisterRecord.getDoctorId())){        
                sql+=" and doctor_id like '%"+patientRegisterRecord.getDoctorId()+"%' ";        
            } 
            if (patientRegisterRecord.getStatus()!=null && !"".equals(patientRegisterRecord.getStatus())){        
                sql+=" and status like '%"+patientRegisterRecord.getStatus()+"%' ";        
            } 
            List<PatientRegisterRecord> list = dao.freeFind(sql);        
            return list;        
    }

    @Override
    public List<PatientRegisterRecord> findsAll() {
        String sql="SELECT pr.*, bd.*,bpi.*,bu.*,CAST(bus.cname AS CHAR) AS cname from patient_register_record pr JOIN base_dept bd ON pr.dept_id=bd.dept_id\n" +
                "                    JOIN base_patient_info bpi ON pr.patient_id=bpi.patient_id\n" +
                "                    JOIN base_user bu ON bu.user_id=pr.record_user\n" +
                "                   JOIN base_user bus ON  bus.user_id=pr.doctor_id";
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> finddoctor() {
        String sql="SELECT pr.*,bpi.*,bu.*,CAST(bus.cname AS CHAR) AS cname from patient_register_record pr \n" +
                "                    JOIN base_patient_info bpi ON pr.patient_id=bpi.patient_id\n" +
                "                    JOIN base_user bu ON bu.user_id=pr.record_user\n" +
                "                   JOIN base_user bus ON  bus.user_id=pr.doctor_id where pr.dept_id is null";
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> finddept() {
        String sql="SELECT * from patient_register_record pr JOIN base_dept bd ON pr.dept_id=bd.dept_id\n" +
                "                    JOIN base_patient_info bpi ON pr.patient_id=bpi.patient_id\n" +
                "                    JOIN base_user bu ON bu.user_id=pr.record_user where pr.doctor_id is null" ;
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> findlistAll(Integer userId) {
  String sql="SELECT * from patient_register_record pr\n" +
          "JOIN  base_patient_info bp ON pr.patient_id=bp.patient_id\n" +
          "WHERE pr.status=2 AND pr.doctor_id='"+userId+"'";
        return dao.freeFind(sql);
    }

    @Override
    public List<PatientRegisterRecord> findpatientId(Integer patientId) {
        String sql="select * from patient_register_record where patient_id='"+patientId+"' ";
        return dao.freeFind(sql);
    }


}