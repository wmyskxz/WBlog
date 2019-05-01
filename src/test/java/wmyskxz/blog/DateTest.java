package wmyskxz.blog;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 测试日期
 *
 * @auth:wmyskxz
 * @date:2019/04/10 - 08:47
 */
public class DateTest {
    @Test
    public void tester() {
        Calendar now = Calendar.getInstance();
        List<String> dates = new ArrayList<>();
        Long oneDay = 86400000L;//一天是这些毫秒。
        Long day = now.getTimeInMillis();//获取当前日期的毫秒数。
        int warningDays = 1;
        for (int i = 0; i < warningDays; i++) {//warningDays为你需要的天数。
            day = day - oneDay;//每次循环都将day变为前一天
            Date date = new Date(day);
            System.out.println(date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = sdf.format(date);//日期格式化
            dates.add(dateStr);
        }
        System.out.println(dates.toString());

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sdf.format(cal.getTime()) + " 00:00:00";
        String endTime = sdf.format(cal.getTime()) + " 23:59:59";
        System.out.println(startTime);
        System.out.println(endTime);

    }

    // 获得当天0点时间
    @Test
    public void getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println(cal.getTime());
    }

}
