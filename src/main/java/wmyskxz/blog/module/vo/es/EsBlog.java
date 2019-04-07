package wmyskxz.blog.module.vo.es;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 用于Elasticsearch搜索的Blog数据模型
 *
 * @auth:wmyskxz
 * @date:2019/03/30 - 17:32
 */
@Document(indexName = "blogs", type = "blog")
public class EsBlog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long blogId;// 博客id
    private String title;// 博客标题
    private String summary;// 博客简介
    private String content;// 博文内容
    private Integer voteSize;// 点赞数量
    private Integer readSize;// 阅读数
    private Integer commentSize;// 评论数量

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVoteSize() {
        return voteSize;
    }

    public void setVoteSize(Integer voteSize) {
        this.voteSize = voteSize;
    }

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Integer getCommentSize() {
        return commentSize;
    }

    public void setCommentSize(Integer commentSize) {
        this.commentSize = commentSize;
    }
}
