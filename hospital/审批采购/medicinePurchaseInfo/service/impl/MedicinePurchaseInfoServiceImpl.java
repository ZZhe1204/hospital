package com.qhit.medicinePurchaseInfo.service.impl;

import com.qhit.common.CommonUtil;
import com.qhit.medicinePurchaseInfo.service.IMedicinePurchaseInfoService;

import java.util.Date;
import java.util.List;
import com.qhit.medicinePurchaseInfo.dao.IMedicinePurchaseInfoDao;
import com.qhit.medicinePurchaseInfo.dao.impl.MedicinePurchaseInfoDaoImpl;
import com.qhit.medicinePurchaseInfo.pojo.MedicinePurchaseInfo;
import com.qhit.medicineReqPlan.pojo.MedicineReqPlan;

import javax.servlet.http.HttpSession;

/**
* Created by GeneratorCode on 2018/12/05
*/

public class MedicinePurchaseInfoServiceImpl  implements IMedicinePurchaseInfoService {

    IMedicinePurchaseInfoDao dao = new MedicinePurchaseInfoDaoImpl();

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
        MedicinePurchaseInfo medicinePurchaseInfo = findById(id); 
        return dao.delete(medicinePurchaseInfo); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public MedicinePurchaseInfo findById(Object id) { 
        List<MedicinePurchaseInfo> list = dao.findById(id); 
        return  list.get(0); 
    } 


    @Override 
    public boolean freeUpdate(String sql) {
        return dao.freeUpdate(sql);
    }


    @Override 
    public List<MedicinePurchaseInfo> search(MedicinePurchaseInfo medicinePurchaseInfo) {
            String sql = "select * from medicine_purchase_info where 1=1 "; 
            if (medicinePurchaseInfo.getMedicineCodeid()!=null && !"".equals(medicinePurchaseInfo.getMedicineCodeid())){        
                sql+=" and MEDICINE_CODEID like '%"+medicinePurchaseInfo.getMedicineCodeid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getManCode()!=null && !"".equals(medicinePurchaseInfo.getManCode())){        
                sql+=" and MAN_CODE like '%"+medicinePurchaseInfo.getManCode()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchAmt()!=null && !"".equals(medicinePurchaseInfo.getPchAmt())){        
                sql+=" and PCH_AMT like '%"+medicinePurchaseInfo.getPchAmt()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchPrice()!=null && !"".equals(medicinePurchaseInfo.getPchPrice())){        
                sql+=" and PCH_PRICE like '%"+medicinePurchaseInfo.getPchPrice()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchTotal()!=null && !"".equals(medicinePurchaseInfo.getPchTotal())){        
                sql+=" and PCH_TOTAL like '%"+medicinePurchaseInfo.getPchTotal()+"%' ";        
            } 
            if (medicinePurchaseInfo.getStatus()!=null && !"".equals(medicinePurchaseInfo.getStatus())){        
                sql+=" and STATUS like '%"+medicinePurchaseInfo.getStatus()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchUserid()!=null && !"".equals(medicinePurchaseInfo.getPchUserid())){        
                sql+=" and PCH_USERID like '%"+medicinePurchaseInfo.getPchUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getPchDate()!=null && !"".equals(medicinePurchaseInfo.getPchDate())){        
                sql+=" and PCH_DATE like '%"+medicinePurchaseInfo.getPchDate()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvUserid()!=null && !"".equals(medicinePurchaseInfo.getApprvUserid())){        
                sql+=" and APPRV_USERID like '%"+medicinePurchaseInfo.getApprvUserid()+"%' ";        
            } 
            if (medicinePurchaseInfo.getApprvDate()!=null && !"".equals(medicinePurchaseInfo.getApprvDate())){        
                sql+=" and APPRV_DATE like '%"+medicinePurchaseInfo.getApprvDate()+"%' ";        
            } 
            List<MedicinePurchaseInfo> list = dao.freeFind(sql);        
            return list;        
    }
//汇总
    @Override
    public void collection(HttpSession session) {
        //更行需求计划表，改为状态3(已汇总)，2（未汇总）
        String sql1="UPDATE medicine_req_plan SET STATUS=3 WHERE STATUS=2";
        String sql2="SELECT medicine_codeid,cast(SUM(reqamt) as char) AS sumamt " +
                "from medicine_req_plan " +
                "WHERE STATUS=2 GROUP BY medicine_codeid";
        List<MedicineReqPlan> list = dao.freeFind(sql2);
        dao.freeUpdate(sql1);
        //往表中插入数据
        for (MedicineReqPlan medicineReqPlan:list
             ) {
            //通过查询药品计划表的药品id 查询药品采购表要添加汇总中的药品ID是否有这个药品。
            String sql3="SELECT * from medicine_req_plan mrp JOIN  medicine_purchase_info mpi ON mpi.MEDICINE_CODEID=mrp.MEDICINE_CODEID  WHERE mpi.MEDICINE_CODEID='"+medicineReqPlan.getMedicineCodeid()+"'";

            List<MedicineReqPlan> list1 = dao.freeFind(sql3);
            if (list1!=null){
              //已存在该药品,修改该药品的数量
                MedicinePurchaseInfo byId = this.findBycodeid(medicineReqPlan);
                //修改药品数量
                for (MedicineReqPlan lists:list1) {
                    byId.setPchAmt(byId.getPchAmt()+lists.getReqamt());
                    this.updateSelective(byId);
                }
            }else{
                MedicinePurchaseInfo medicinePurchaseInfo=new MedicinePurchaseInfo();
                medicinePurchaseInfo.setMedicineCodeid(medicineReqPlan.getMedicineCodeid());
                medicinePurchaseInfo.setPchAmt(Integer.parseInt(medicineReqPlan.getSumamt()));
                medicinePurchaseInfo.setStatus(1);//1已汇总
                medicinePurchaseInfo.setPchDate(CommonUtil.dateToStr(new Date()));
                medicinePurchaseInfo.setPchUserid(CommonUtil.getUserId(session));
                dao.insert(medicinePurchaseInfo);

            }
        }

    }

    @Override
    public List<MedicinePurchaseInfo> findsall() {
        String sql="SELECT * from medicine_purchase_info mp " +
                "JOIN medicine_code mc ON mp.MEDICINE_CODEID=mc.code_id " +
                "JOIN medicine_req_plan mrp ON mrp.MEDICINE_CODEID = mc.code_id "+
                "JOIN base_user bu ON mrp.APPRV_USERID=bu.user_id " +
                "group by mc.code_id";
        return dao.freeFind(sql);
    }

    @Override
    public MedicinePurchaseInfo findbyname(Integer pchUserid) {
        String sql="SELECT*from medicine_purchase_info mp JOIN base_user bu ON mp.pch_userid=bu.user_id";
        List<MedicinePurchaseInfo> list = dao.freeFind(sql);
        if (list!=null && list.size()>0){
           return list.get(0);
       }
        return null;
    }

    @Override
    public List<MedicineReqPlan> findlist() {
        String sql="SELECT*from medicine_req_plan WHERE STATUS=2";
        return dao.freeFind(sql);
    }

    @Override
    public List<MedicinePurchaseInfo> findalls() {
        String sql="SELECT * from medicine_purchase_info mp " +
                "JOIN medicine_code mc ON mp.MEDICINE_CODEID=mc.code_id " +
                "JOIN medicine_req_plan mrp ON mrp.MEDICINE_CODEID = mc.code_id "+
                "JOIN base_user bu ON mrp.APPRV_USERID=bu.user_id WHERE mp.STATUS=2 " +
                "group by mc.code_id ";
        return dao.freeFind(sql);
    }

    @Override
    public MedicinePurchaseInfo findBycodeid(MedicineReqPlan medicineReqPlan) {
        String sql="SELECT * from  medicine_purchase_info  WHERE MEDICINE_CODEID='"+medicineReqPlan.getMedicineCodeid()+"'";
        List<MedicinePurchaseInfo> list = dao.freeFind(sql);
        if (list!=null && list.size()>0){
           return  list.get(0);
       }
        return null;
    }

}