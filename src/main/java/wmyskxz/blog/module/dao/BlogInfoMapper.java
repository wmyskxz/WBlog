package wmyskxz.blog.module.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wmyskxz.blog.module.entity.BlogInfo;
import wmyskxz.blog.module.entity.BlogInfoExample;

public interface BlogInfoMapper {
    long countByExample(BlogInfoExample example);

    int deleteByExample(BlogInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlogInfo record);

    int insertSelective(BlogInfo record);

    List<BlogInfo> selectByExample(BlogInfoExample example);

    BlogInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlogInfo record, @Param("example") BlogInfoExample example);

    int updateByExample(@Param("record") BlogInfo record, @Param("example") BlogInfoExample example);

    int updateByPrimaryKeySelective(BlogInfo record);

    int updateByPrimaryKey(BlogInfo record);
}