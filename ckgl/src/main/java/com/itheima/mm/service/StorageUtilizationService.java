package com.itheima.mm.service;

import com.itheima.mm.dao.StorageUtilizationDao;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.StorageUtilization;
import com.itheima.mm.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class StorageUtilizationService {
    public void add(StorageUtilization storageUtilization) throws Exception {
        //调用Dao层的方法添加储位利用率信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StorageUtilizationDao storageUtilizationDao = sqlSession.getMapper(StorageUtilizationDao.class);
        storageUtilizationDao.add((StorageUtilizationDao) storageUtilization);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public PageResult findByPage(QueryPageBean queryPageBean) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StorageUtilizationDao storageUtilizationDao = sqlSession.getMapper(StorageUtilizationDao.class);
        //1. 调用Dao层的方法，查询学科的总条数
        Long total = storageUtilizationDao.findTotalCount(queryPageBean);

        //2. 调用Dao层的方法，查询当前页的学科列表
        List<StorageUtilization> storageUtilizationList = storageUtilizationDao.findPageList(queryPageBean);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return new PageResult(total,storageUtilizationList);
    }

    public void update(StorageUtilization storageUtilization) throws Exception {
        //调用Dao层的方法修改学科信息
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StorageUtilizationDao storageUtilizationDao = sqlSession.getMapper(StorageUtilizationDao.class);

        storageUtilizationDao.update(storageUtilization);
        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public void deleteByFactory(String factory) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StorageUtilizationDao storageUtilizationDao = sqlSession.getMapper(StorageUtilizationDao.class);
        storageUtilizationDao.deleteByFactory(factory);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
    }

    public List<StorageUtilization> findAll(String status) throws Exception {
        SqlSession sqlSession = SqlSessionFactoryUtils.openSqlSession();
        StorageUtilizationDao storageUtilizationDao = sqlSession.getMapper(StorageUtilizationDao.class);
        List<StorageUtilization> storageUtilizationList = storageUtilizationDao.findAll(status);

        SqlSessionFactoryUtils.commitAndClose(sqlSession);
        return storageUtilizationList;
    }
}