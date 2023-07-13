package com.qhit.baseUser.dao.impl;

import com.qhit.baseUser.dao.IBaseUserDao;
import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/11/26
*/

public class BaseUserDaoImpl extends BaseDao implements IBaseUserDao {

    @Override 
    public List findAll() { 
        String sql = "select * from base_user"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findById(Object id) { 
        String sql = "select * from base_user where user_id='"+id+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByUserName(Object userName) { 
        String sql = "select * from base_user where user_name='"+userName+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPassword(Object password) { 
        String sql = "select * from base_user where password='"+password+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByCname(Object cname) { 
        String sql = "select * from base_user where cname='"+cname+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findBySex(Object sex) { 
        String sql = "select * from base_user where sex='"+sex+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByDeptId(Object deptId) { 
        String sql = "select * from base_user where dept_id='"+deptId+"'"; 
        return freeFind(sql); 
    } 


    @Override 
    public List findByPostId(Object postId) { 
        String sql = "select * from base_user where post_id='"+postId+"'"; 
        return freeFind(sql); 
    } 




}