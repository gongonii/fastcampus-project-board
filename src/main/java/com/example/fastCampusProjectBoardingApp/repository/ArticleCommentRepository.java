package com.example.fastCampusProjectBoardingApp.repository;

import com.example.fastCampusProjectBoardingApp.domain.Article;
import com.example.fastCampusProjectBoardingApp.domain.ArticleComment;
import com.example.fastCampusProjectBoardingApp.domain.QArticle;
import com.example.fastCampusProjectBoardingApp.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long> ,
        QuerydslPredicateExecutor<ArticleComment>,//모든 필드에 대한 기본검색기능
        QuerydslBinderCustomizer<QArticleComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);//알티클에대한 모든 필드들에 대한 검색이 열려있음 기본값이 false이므로 true로 해주어서 검색을 막음
        bindings.including(root.content,root.createdAt,root.createdBy); //부분 검색
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase));//
        bindings.bind(root.createdAt).first((DateTimeExpression::eq));//
        bindings.bind(root.createdBy).first((StringExpression::containsIgnoreCase));
    }
}
