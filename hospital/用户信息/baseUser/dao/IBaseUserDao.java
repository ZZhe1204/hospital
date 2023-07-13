package com.qhit.baseUser.dao;

import com.qhit.utils.BaseDao;
import java.util.List;

/**
* Created by GeneratorCode on 2018/11/26
*/

public interface IBaseUserDao {

    boolean insert(Object object);

    boolean  update(Object object);

    boolean  updateSelective(Object object);

    boolean delete(Object object);

    List freeFind(String sql);

    List findAll();

    List findById(Object id);

    List findByUserName(Object userName);

    List findByPassword(Object password);

    List findByCname(Object cname);

    List findBySex(Object sex);

    List findByDeptId(Object deptId);

    List findByPostId(Object postId);

}