package com.example.fastCampusProjectBoardingApp.repository;

import com.example.fastCampusProjectBoardingApp.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
