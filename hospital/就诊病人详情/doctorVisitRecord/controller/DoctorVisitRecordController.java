package com.qhit.doctorVisitRecord.controller; 

import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.common.CommonUtil;
import com.qhit.doctorMenuMedicine.pojo.DoctorMenuMedicine;
import com.qhit.doctorMenuMedicine.service.IDoctorMenuMedicineService;
import com.qhit.doctorMenuMedicine.service.impl.DoctorMenuMedicineServiceImpl;
import com.qhit.doctorVisitRecord.pojo.DoctorVisitRecord;
import com.qhit.doctorVisitRecord.service.IDoctorVisitRecordService; 
import com.qhit.doctorVisitRecord.service.impl.DoctorVisitRecordServiceImpl;
import com.qhit.doctorVisitRecordinfo.pojo.DoctorVisitRecordinfo;
import com.qhit.doctorVisitRecordinfo.service.IDoctorVisitRecordinfoService;
import com.qhit.doctorVisitRecordinfo.service.impl.DoctorVisitRecordinfoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.RequestMapping; 
import javax.annotation.Resource; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON; 
import java.io.IOException;
import java.util.Date;
import java.util.List;

/** 
* Created by GeneratorCode on 2018/12/28
*/ 

@Controller 
@RequestMapping("/doctorVisitRecord") 
public class DoctorVisitRecordController { 

    @Resource 
    IDoctorVisitRecordService doctorVisitRecordService;
    @Resource
    IDoctorVisitRecordinfoService doctorVisitRecordinfoService;
    @Resource
    IDoctorMenuMedicineService doctorMenuMedicineService;

    @RequestMapping("/insert") 
    public String insert(DoctorVisitRecord doctorVisitRecord,HttpSession session,Integer $patientId,String $patient,String $doctorsay,Integer $taocan) {
        //往就诊表里添加数据
        BaseUser sessionUser = (BaseUser) session.getAttribute("sessionuser");
        doctorVisitRecord.setPatientId($patientId);
        doctorVisitRecord.setDoctorId(sessionUser.getUserId());
        doctorVisitRecord.setVisitDate(CommonUtil.dateToStr(new Date()));
        doctorVisitRecord.setSymptom($patient);
        doctorVisitRecord.setAdvice($doctorsay);
        doctorVisitRecord.setStatus(1);
        doctorVisitRecordService.insert(doctorVisitRecord);
        //根据套餐id查到该套餐的药品
     /*   DoctorMenuMedicine doctorMenuMedicine = new DoctorMenuMedicine();//套餐详情表*/
      List<DoctorMenuMedicine> list = doctorMenuMedicineService.findsMedicine($taocan);
        //往就诊详情表里添加数据
        DoctorVisitRecordinfo doctorVisitRecordinfo = new DoctorVisitRecordinfo();
      //利用增强for循环遍历药品和数量
        for (DoctorMenuMedicine doctorMenuMedicine:list) {
            doctorVisitRecordinfo.setVrId(doctorVisitRecord.getVrId());
            doctorVisitRecordinfo.setCodeId(doctorMenuMedicine.getCodeId());
            doctorVisitRecordinfo.setAmt(doctorMenuMedicine.getAmt());
            doctorVisitRecordinfoService.insert(doctorVisitRecordinfo);
        }
        return "forward:listall.action";
    }
    @RequestMapping("/delete") 
    public String delete(Integer vrId, HttpServletResponse response) throws IOException { 
        doctorVisitRecordService.delete(vrId); 
        return "forward:listall.action";
    } 
 
    @RequestMapping("/update") 
    public String update(DoctorVisitRecord doctorVisitRecord) { 
        doctorVisitRecordService.update(doctorVisitRecord); 
        return "forward:listall.action";
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(DoctorVisitRecord doctorVisitRecord) { 
        doctorVisitRecordService.updateSelective(doctorVisitRecord); 
        return "forward:listall.action";
    } 
 
    @RequestMapping("/load") 
    public String load(Integer vrId, Model model) { 
        DoctorVisitRecord doctorVisitRecord = doctorVisitRecordService.findById(vrId); 
        model.addAttribute("doctorVisitRecord",doctorVisitRecord); 
        return "doctorVisitRecord/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
        List<DoctorVisitRecord> list = doctorVisitRecordService.findAll(); 
        model.addAttribute("list",list);
        return "doctorVisitRecord/distribute";
    }
    @RequestMapping("/listall")
    public String listall(Model model) throws IOException {
        List<DoctorVisitRecord> list = doctorVisitRecordService.findAll();
        model.addAttribute("list",list);
        return "doctorVisitRecord/list";
    }

    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response) throws IOException { 
        List<DoctorVisitRecord> list = doctorVisitRecordService.findAll(); 
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    } 
 
    @RequestMapping("/search") 
    public String search(DoctorVisitRecord doctorVisitRecord,Model model) { 
        List<DoctorVisitRecord> list = doctorVisitRecordService.search(doctorVisitRecord); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",doctorVisitRecord); 
        return "doctorVisitRecord/list"; 
    } 
 
} 
