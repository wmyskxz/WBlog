package wmyskxz.blog.util;

import wmyskxz.blog.module.vo.base.PageResultVo;
import wmyskxz.blog.module.vo.base.ResponseVo;

import java.util.List;

/**
 * 返回前台工具类
 *
 * @auth:wmyskxz
 * @date:2019/03/02 - 14:31
 */
public class ResultUtil {
    public static ResponseVo success() {
        return vo(ConstCode.SUCCESS_CODE, null, null);
    }

    public static ResponseVo success(String msg) {
        return vo(ConstCode.SUCCESS_CODE, msg, null);
    }

    public static ResponseVo success(String msg, Object data) {
        return vo(ConstCode.SUCCESS_CODE, msg, data);
    }

    public static ResponseVo error() {
        return vo(ConstCode.FAIL_CODE, null, null);
    }

    public static ResponseVo error(String msg) {
        return vo(ConstCode.FAIL_CODE, msg, null);
    }

    public static ResponseVo error(String msg, Object data) {
        return vo(ConstCode.FAIL_CODE, msg, data);
    }

    public static PageResultVo table(List<?> list, Long total) {
        return new PageResultVo(list, total);
    }

    public static ResponseVo vo(Integer status, String message, Object data) {
        return new ResponseVo<>(status, message, data);
    }

}
