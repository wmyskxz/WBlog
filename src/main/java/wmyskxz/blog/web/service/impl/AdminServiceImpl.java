package wmyskxz.blog.web.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wmyskxz.blog.module.dao.BlogInfoMapper;
import wmyskxz.blog.module.dao.UserMapper;
import wmyskxz.blog.module.entity.BlogInfoExample;
import wmyskxz.blog.module.entity.UserExample;
import wmyskxz.blog.module.vo.AdminHomeChartVo;
import wmyskxz.blog.module.vo.AdminHomeVo;
import wmyskxz.blog.web.service.AdminService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * AdminService实现类
 *
 * @auth:wmyskxz
 * @date:2019/04/10 - 08:36
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource UserMapper userMapper;
    @Resource BlogInfoMapper blogInfoMapper;

    @Override
    @Transactional// 开启事务
    public AdminHomeVo home() {
        AdminHomeVo resultObject = new AdminHomeVo();
        List<AdminHomeChartVo> chartVos = new LinkedList<>();

        // 1.查询用户相关的东西
        UserExample userExample = new UserExample();
        userExample.or();// 无条件查询即查询所有
        Integer totalUsers = Math.toIntExact(userMapper.countByExample(userExample));
        resultObject.setTotalUsers(totalUsers);// 总用户
        userExample.or().andCreateTimeGreaterThanOrEqualTo(getCurrentZeroTime());
        Integer addUsers = Math.toIntExact(userMapper.countByExample(userExample));
        resultObject.setAddUsers(addUsers);// 今日新增用户

        // 2.查询博客相关的东西
        BlogInfoExample blogInfoExample = new BlogInfoExample();
        blogInfoExample.or();// 无条件查询即查询所有
        Integer totalBlogs = Math.toIntExact(blogInfoMapper.countByExample(blogInfoExample));
        resultObject.setTotalBlogs(totalBlogs);// 总博客
        blogInfoExample.or().andCreateTimeGreaterThanOrEqualTo(getCurrentZeroTime());
        Integer addBlogs = Math.toIntExact(blogInfoMapper.countByExample(blogInfoExample));
        resultObject.setAddBlogs(addBlogs);// 今日新增博客

        // 3.查询走势图相关的东西（最近7天，也就是从今天起往前推7天）
        AdminHomeChartVo chartVo;
        for (int i = 7; i >= 0; i--) {
            chartVo = new AdminHomeChartVo();
            userExample = new UserExample();
            userExample.or().andCreateTimeGreaterThanOrEqualTo(getLastDaysZeroTime(i))
                       .andCreateTimeLessThanOrEqualTo(getLastDaysZeroTime(i - 1));
            chartVo.setAddUsers(Math.toIntExact(userMapper.countByExample(userExample)));// 新增用户
            blogInfoExample = new BlogInfoExample();
            blogInfoExample.or().andCreateTimeGreaterThanOrEqualTo(getLastDaysZeroTime(i))
                           .andCreateTimeLessThanOrEqualTo(getLastDaysZeroTime(i - 1));
            chartVo.setAddBlogs(Math.toIntExact(blogInfoMapper.countByExample(blogInfoExample)));// 新增博客
            chartVo.setDate(getDateStr(getLastDaysZeroTime(i)));// 设置日期
            chartVos.add(chartVo);
        }
        resultObject.setChartVos(chartVos);

        return resultObject;
    }


    /**
     * 获取今天0点的时间，例如今天1-1，则获取1-1号0点时间
     *
     * @return
     */
    public Date getCurrentZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取前几天的0点时间
     * 例如：今天1-2，days传入1则返回1-10点时间
     *
     * @param days
     * @return
     */
    public Date getLastDaysZeroTime(int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getCurrentZeroTime().getTime() - 3600 * 24 * 1000 * days);
        return cal.getTime();
    }

    /**
     * 格式化日期数据并转化为String类型
     *
     * @param date
     * @return
     */
    public String getDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        String dateStr = sdf.format(date);//日期格式化
        return dateStr;
    }
}
