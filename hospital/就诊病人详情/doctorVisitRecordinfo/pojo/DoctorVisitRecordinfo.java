package com.qhit.doctorVisitRecordinfo.pojo;


/**
* Created by GeneratorCode on 2018/12/28
*/

public class DoctorVisitRecordinfo {

    private Integer infoId;    //主键
    private Integer vrId;    //就诊记录ID
    private Integer codeId;    //药品
    private Integer amt;    //药品数量

    public Integer getInfoId() { 
        return infoId;
    }
 
    public void setInfoId(Integer infoId) { 
        this.infoId = infoId;
    }
 
    public Integer getVrId() { 
        return vrId;
    }
 
    public void setVrId(Integer vrId) { 
        this.vrId = vrId;
    }
 
    public Integer getCodeId() { 
        return codeId;
    }
 
    public void setCodeId(Integer codeId) { 
        this.codeId = codeId;
    }
 
    public Integer getAmt() { 
        return amt;
    }
 
    public void setAmt(Integer amt) { 
        this.amt = amt;
    }
 

 }