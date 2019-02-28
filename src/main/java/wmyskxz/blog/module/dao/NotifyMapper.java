package wmyskxz.blog.module.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wmyskxz.blog.module.entity.Notify;
import wmyskxz.blog.module.entity.NotifyExample;

public interface NotifyMapper {
    long countByExample(NotifyExample example);

    int deleteByExample(NotifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Notify record);

    int insertSelective(Notify record);

    List<Notify> selectByExample(NotifyExample example);

    Notify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Notify record, @Param("example") NotifyExample example);

    int updateByExample(@Param("record") Notify record, @Param("example") NotifyExample example);

    int updateByPrimaryKeySelective(Notify record);

    int updateByPrimaryKey(Notify record);
}