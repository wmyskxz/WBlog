package wmyskxz.blog.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import wmyskxz.blog.module.dao.es.EsBlogRepository;
import wmyskxz.blog.module.vo.es.EsBlog;
import wmyskxz.blog.web.service.SearchService;

import java.util.List;

/**
 * SearchService实现类
 *
 * @auth:wmyskxz
 * @date:2019/03/30 - 22:31
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired EsBlogRepository esBlogRepository;

    @Override
    public List<EsBlog> search(String keyword, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esBlogRepository.findByTitleLikeOrSummaryLikeOrContentLike(keyword, keyword, keyword, pageable);
    }
}
