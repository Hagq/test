package com.itheima.mm.dao;

import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.pojo.StorageUtilization;

import java.util.List;

public interface StorageUtilizationDao {

    void add(StorageUtilizationDao storageUtilizationDao);

    Long findTotalCount(QueryPageBean queryPageBean);

    List<StorageUtilization> findPageList(QueryPageBean queryPageBean);

    void update(StorageUtilization storageUtilization);

    void deleteByFactory(String factory);

    List<StorageUtilization> findAll(String status);
}
