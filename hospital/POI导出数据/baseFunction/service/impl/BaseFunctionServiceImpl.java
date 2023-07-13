package com.qhit.baseFunction.service.impl;

import com.qhit.baseFunction.service.IBaseFunctionService;
import java.util.List;
import com.qhit.baseFunction.dao.IBaseFunctionDao;
import com.qhit.baseFunction.dao.impl.BaseFunctionDaoImpl;
import com.qhit.baseFunction.pojo.BaseFunction;

/**
* Created by GeneratorCode on 2018/11/28
*/

public class BaseFunctionServiceImpl  implements IBaseFunctionService {

    IBaseFunctionDao dao = new BaseFunctionDaoImpl();

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
        BaseFunction baseFunction = findById(id); 
        return dao.delete(baseFunction); 
    } 


    @Override 
    public List findAll() { 
        return dao.findAll(); 
    } 


    @Override 
    public BaseFunction findById(Object id) { 
        List<BaseFunction> list = dao.findById(id); 
        return  list.get(0); 
    }

    @Override
    public List<BaseFunction> findsAll() {
        String sql="SELECT * from base_function cs left JOIN base_module cg ON cs.mid=cg.mid";
        List<BaseFunction> list = dao.freeFind(sql);
        return list;

    }

    @Override
    public List<BaseFunction> find(String fname, String mname,String url) {
        String sql="SELECT * from base_function cs JOIN base_module cg ON cs.mid=cg.mid";
        String str=" where 1=1";
        //按照功能表查询
        if  (fname!=null || !"".equals(fname)){
            str+=" and fname like '%"+fname+"%'";
        }
        //按照模板名查询
        if (mname!=null || !"".equals(mname)){
            str+=" and mname like '%"+mname+"%'";
        }
        //按照url查询
        if (url!=null || !"".equals(url)){
            str+=" and url like '%"+url+"%'";
        }


        List<BaseFunction> list = dao.freeFind(sql+str);
        return list;
    }

    @Override
    public List<BaseFunction> findbymid(Integer mid, Integer userId) {
        if(mid==null){
            mid=1;
        }
        String sql = "SELECT DISTINCT bf.*\n" +
                "from base_function bf JOIN base_role_function brf ON bf.fid=brf.fid\n" +
                "\t\t      JOIN base_role br ON brf.rid=br.rid\n" +
                "\t\t      JOIN base_user_role bur ON bur.rid = br.rid\n" +
                "\t\t      JOIN base_user bu ON bu.user_id = bur.uid\n" +
                "\t\t      JOIN base_module bm ON bm.mid = bf.mid\n" +
                "\t\t      AND bur.uid="+userId+"\n" +
                "\t\t      AND bf.mid="+mid;
        return dao.freeFind(sql);
    }

}