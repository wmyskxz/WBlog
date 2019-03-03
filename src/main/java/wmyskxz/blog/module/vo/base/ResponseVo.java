package wmyskxz.blog.module.vo.base;

/**
 * 用于与前台交互的Response数据模型
 *
 * @auth:wmyskxz
 * @date:2019/02/28 - 15:05
 */
public class ResponseVo<T> {
    private Integer status;
    private String msg;
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}
