package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


//todo : JdbcTemplate vs NamedParameterJdbcTemplate 비교해보기
@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateArticleRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("ARTICLE_TB")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public void save(Article article) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(article);
        Number key = simpleJdbcInsert.executeAndReturnKey(param);
        article.create(key.longValue(), article);
    }

    @Override
    public void update(Article updatedArticle) {
        String sql = "UPDATE ARTICLE_TB SET TITLE = :TITLE, CONTENT = :CONTENT, createTime = :createTime WHERE ID = :ID";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("TITLE", updatedArticle.getTitle())
                .addValue("CONTENT", updatedArticle.getContent())
                .addValue("createTime", updatedArticle.getCreateTime())
                .addValue("ID", updatedArticle.getId());

        template.update(sql, param);
    }

    @Override
    public List<Article> gatherAll() {
        String sql = "SELECT * FROM ARTICLE_TB";
        return template.query(sql, articleRowMapper());
    }

    @Override
    public Optional<Article> findById(long id) {
        String sql = "SELECT * FROM ARTICLE_TB WHERE ID = :ID";
        Map<String, Object> param = Map.of("ID", id);
        try (Stream<Article> result = template.queryForStream(sql, param, articleRowMapper())) {
            return result.findFirst();
        }
    }

    public RowMapper<Article> articleRowMapper() {
        return BeanPropertyRowMapper.newInstance(Article.class);
    }
}
