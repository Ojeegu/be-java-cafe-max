package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final NamedParameterJdbcTemplate template;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("USER_TB")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public void join(User user) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(user);
        Number key = simpleJdbcInsert.executeAndReturnKey(param);
        user.setId(key.longValue());
    }

    @Override
    public void update(User updateUser) {
        String sql = "UPDATE USER_TB SET NAME = :NAME, PASSWORD = :PASSWORD, EMAIL = :EMAIL WHERE ID = :ID";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("NAME", updateUser.getName())
                .addValue("PASSWORD", updateUser.getPassword())
                .addValue("EMAIL", updateUser.getEmail())
                .addValue("ID", updateUser.getId());

        template.update(sql, param);
    }
    @Override
    public Optional<User> findById(long id) {
        String sql = "SELECT * FROM USER_TB WHERE ID = :ID";

        try {
            Map<String, Object> param = Map.of("ID", id);
            User user = template.queryForObject(sql, param, userRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        String sql = "SELECT * FROM USER_TB WHERE USERID = :USERID";

        try {
            Map<String, Object> param = Map.of("USERID", userId);
            User user = template.queryForObject(sql, param, userRowMapper());
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM USER_TB";
        return template.query(sql, userRowMapper());
    }

    public RowMapper<User> userRowMapper() {
        return BeanPropertyRowMapper.newInstance(User.class);
    }
}
