package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void save(final Article article);
    List<Article> gatherAll();
    Optional<Article> findById(final long id);
}
