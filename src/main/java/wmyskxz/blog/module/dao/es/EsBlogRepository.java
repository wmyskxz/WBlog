package wmyskxz.blog.module.dao.es;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wmyskxz.blog.module.vo.es.EsBlog;

import java.util.List;

/**
 * ES Blog Dao层
 *
 * @auth:wmyskxz
 * @date:2019/03/30 - 20:28
 */
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, Long> {
    /**
     * 模糊查询(去重)
     *
     * @param title
     * @param summary
     * @param content
     * @return
     */
    List<EsBlog> findByTitleLikeOrSummaryLikeOrContentLike(String title, String summary, String content,
                                                           Pageable pageable);
}
