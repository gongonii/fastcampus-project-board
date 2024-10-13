package com.example.fastCampusProjectBoardingApp.repository;

import com.example.fastCampusProjectBoardingApp.domain.Article;
import com.example.fastCampusProjectBoardingApp.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,//모든 필드에 대한 기본검색기능
        QuerydslBinderCustomizer<QArticle> //
{

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);//알티클에대한 모든 필드들에 대한 검색이 열려있음 기본값이 false이므로 true로 해주어서 검색을 막음
        bindings.including(root.title,root.content,root.hashtag,root.createdAt,root.createdBy); //부분 검색
        bindings.bind(root.title).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.hashtag).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.createdAt).first((DateTimeExpression::eq));//
        bindings.bind(root.createdBy).first((StringExpression::containsIgnoreCase));
    }
}
