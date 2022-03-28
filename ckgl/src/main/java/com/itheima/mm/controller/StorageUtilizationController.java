package com.itheima.mm.controller;
import com.itheima.framework.anno.Controller;
import com.itheima.framework.anno.RequestMapping;
import com.itheima.mm.entry.PageResult;
import com.itheima.mm.entry.QueryPageBean;
import com.itheima.mm.entry.Result;
import com.itheima.mm.pojo.StorageUtilization;
import com.itheima.mm.service.StorageUtilizationService;
import com.itheima.mm.utils.JsonUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 包名:com.itheima.mm.controller
 *
 * @author Leevi
 * 日期2020-08-02  11:04
 */
@Controller
public class  StorageUtilizationController {
    private StorageUtilizationService storageUtilizationService = new StorageUtilizationService();
    @RequestMapping("/storageUtilization/add")
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数封装到StorageUtilization对象中
            StorageUtilization storageUtilization = JsonUtils.parseJSON2Object(request, StorageUtilization.class);
            //3. 调用业务层的方法保存 storageUtilization 的信息
            storageUtilizationService.add(storageUtilization);

            JsonUtils.printResult(response,new Result(true,"添加学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"添加学科失败"));
        }
    }
    @RequestMapping("/storageUtilization/findByPage")
    public void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数封装到QueryPageBean对象
            QueryPageBean queryPageBean = JsonUtils.parseJSON2Object(request, QueryPageBean.class);
            Map queryParams = queryPageBean.getQueryParams();
            queryParams.put("status",queryParams.get("status")+"");

            //2. 调用业务层的方法进行分页查询
            PageResult pageResult = storageUtilizationService.findByPage(queryPageBean);
            //3. 将响应结果进行封装，并且转换成json输出到客户端
            JsonUtils.printResult(response,new Result(true,"查询学科列表成功",pageResult));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,"查询学科列表失败"));
        }
    }

    @RequestMapping("/storageUtilization/update")
    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 接收请求参数封装到Course中
            StorageUtilization storageUtilization = JsonUtils.parseJSON2Object(request, StorageUtilization.class);
            //2. 调用业务层的方法修改学科信息
            storageUtilizationService.update(storageUtilization);

            //修改成功
            JsonUtils.printResult(response,new Result(true,"修改学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            //修改失败
            JsonUtils.printResult(response,new Result(false,"修改学科失败"));
        }
    }
    @RequestMapping("/storageUtilization/delete")
    public void deleteByFactory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            //1. 获取请求参数factory
            String factory = request.getParameter("factory");
            //2. 调用业务层的方法，根据factory 删除学科
            storageUtilizationService.deleteByFactory(factory);
            //删除成功
            JsonUtils.printResult(response,new Result(true,"删除学科成功"));
        } catch (Exception e) {
            e.printStackTrace();
            JsonUtils.printResult(response,new Result(false,e.getMessage()));
        }
    }

}


