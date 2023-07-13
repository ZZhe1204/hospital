package com.qhit.patientRegisterRecord.controller; 

import com.qhit.basePatientInfo.pojo.BasePatientInfo;
import com.qhit.basePatientInfo.service.IBasePatientInfoService;
import com.qhit.basePatientInfo.service.impl.BasePatientInfoServiceImpl;
import com.qhit.baseUser.pojo.BaseUser;
import com.qhit.common.CommonUtil;
import com.qhit.patientRegisterRecord.pojo.PatientRegisterRecord;
import com.qhit.patientRegisterRecord.service.IPatientRegisterRecordService; 
import com.qhit.patientRegisterRecord.service.impl.PatientRegisterRecordServiceImpl; 
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
* Created by GeneratorCode on 2018/12/21
*/
@Controller 
@RequestMapping("/patientRegisterRecord")
public class PatientRegisterRecordController { 

    IPatientRegisterRecordService patientRegisterRecordService = new PatientRegisterRecordServiceImpl();
    IBasePatientInfoService basePatientInfoService = new BasePatientInfoServiceImpl();

    @RequestMapping("/insert") 
    public String insert(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.insert(patientRegisterRecord); 
        return "forward:list.action"; 
    }
    @RequestMapping("/insertguahao")
    public String insertguahao(PatientRegisterRecord patientRegisterRecord, Integer doctor, Integer dept, Integer patientsid, HttpSession session) {
        BaseUser sessionuser = (BaseUser) session.getAttribute("sessionuser");
        if (dept!=null && !"".equals(dept)){
            patientRegisterRecord.setDeptId(dept);
        } if (doctor!=null && !"".equals(doctor)){
            patientRegisterRecord.setDoctorId(doctor);
        }
        patientRegisterRecord.setRecordUser(sessionuser.getUserId());//挂号人
        patientRegisterRecord.setRegisterDate(CommonUtil.dateToStr(new Date()));//挂号日期
        patientRegisterRecord.setStatus(2);//挂号状态
        patientRegisterRecord.setPatientId(patientsid);//病人
        patientRegisterRecordService.insert(patientRegisterRecord);
        if (dept==null || "".equals(dept)){
            return "forward:doctorlist.action";
        } if (doctor==null || "".equals(doctor)){
            return "forward:deptlist.action";
        }else {
            return "forward:list.action";
        }

    }
    @RequestMapping("/doctorlist")
    public String doctorlist(Model model) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.finddoctor();
        model.addAttribute("list",list);
        return "patientRegisterRecord/doctorlist";
    }
    @RequestMapping("/deptlist")
    public String deptlist(Model model) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.finddept();
        model.addAttribute("list",list);
        return "patientRegisterRecord/deptlist";
    }
    @RequestMapping("/delete") 
    public String delete(Integer registerId, HttpServletResponse response) throws IOException { 
        patientRegisterRecordService.delete(registerId); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/update") 
    public String update(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.update(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/updateSelective") 
    public String updateSelective(PatientRegisterRecord patientRegisterRecord) { 
        patientRegisterRecordService.updateSelective(patientRegisterRecord); 
        return "forward:list.action"; 
    } 
 
    @RequestMapping("/load") 
    public String load(Integer registerId, Model model) { 
        PatientRegisterRecord patientRegisterRecord = patientRegisterRecordService.findById(registerId); 
        model.addAttribute("patientRegisterRecord",patientRegisterRecord); 
        return "patientRegisterRecord/edit"; 
    } 
 
    @RequestMapping("/list") 
    public String list(Model model) throws IOException { 
       List<PatientRegisterRecord> list = patientRegisterRecordService.findsAll();
        model.addAttribute("list",list); 
        return "patientRegisterRecord/list"; 
    }
    @RequestMapping("/listPRR")
    public String listPRR(Model model) throws IOException {
       List<BasePatientInfo> list = basePatientInfoService.findAll();
        model.addAttribute("list",list);
        return "patientRegisterRecord/PRRlist";
    }
 
    @RequestMapping("/ajaxList") 
    public void ajaxList(HttpServletResponse response,HttpSession session) throws IOException {
        BaseUser baseUser = (BaseUser) session.getAttribute("sessionuser");
        List<PatientRegisterRecord> list = patientRegisterRecordService.findlistAll(baseUser.getUserId());
        String s = JSON.toJSONString(list); 
        response.getWriter().write(s); 
    }
    @RequestMapping("/ajaxListA")
    public void ajaxListA(HttpServletResponse response,Integer $bingrenId) throws IOException {
        List<BasePatientInfo> list = basePatientInfoService.findbingrenAll($bingrenId);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
    @RequestMapping("/ajaxlists")
    public void ajaxlists(HttpServletResponse response,Integer patientId) throws IOException {
        List<PatientRegisterRecord> list = patientRegisterRecordService.findpatientId(patientId);
        String s = JSON.toJSONString(list);
        response.getWriter().write(s);
    }
 
    @RequestMapping("/search") 
    public String search(PatientRegisterRecord patientRegisterRecord,Model model) { 
        List<PatientRegisterRecord> list = patientRegisterRecordService.search(patientRegisterRecord); 
        model.addAttribute("list",list); 
        model.addAttribute("searchObject",patientRegisterRecord);
        return "patientRegisterRecord/list";
    } 
 
} 
