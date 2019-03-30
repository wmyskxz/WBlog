package wmyskxz.blog.web.service;

import wmyskxz.blog.module.vo.es.EsBlog;

import java.util.List;

/**
 * 搜索Service
 *
 * @auth:wmyskxz
 * @date:2019/03/30 - 22:28
 */
public interface SearchService {
    /**
     * 搜索关键字
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<EsBlog> search(String keyword, int pageNum, int pageSize);
}
