package com.example.fastCampusProjectBoardingApp.repository;

import com.example.fastCampusProjectBoardingApp.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
