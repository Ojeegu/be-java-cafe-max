package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.Comment;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateCommentRepository implements CommentRepository{

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateCommentRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("COMMENT_TB")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Comment comment) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
        Number key = simpleJdbcInsert.executeAndReturnKey(param);

        comment.create(key.longValue(), comment);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM COMMENT_TB WHERE id = :id";
        Map<String, Object> param = Map.of("id", id);
        template.update(sql, param);
    }

    @Override
    public void deleteByReId(Long reId) {
        String sql = "DELETE FROM COMMENT_TB WHERE reId = :reId";
        Map<String, Object> param = Map.of("reId", reId);
        template.update(sql, param);
    }


    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> gatherAll() {
        return null;
    }
}
