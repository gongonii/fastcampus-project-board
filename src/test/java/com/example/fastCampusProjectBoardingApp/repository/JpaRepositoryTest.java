package com.example.fastCampusProjectBoardingApp.repository;

import com.example.fastCampusProjectBoardingApp.config.JpaConfig;
import com.example.fastCampusProjectBoardingApp.domain.Article;
import com.example.fastCampusProjectBoardingApp.domain.ArticleComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//테스트는 아무리 돌려도 디비에 영향을 주지 않음 즉 롤백함 ~
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest //트랜잭션이 걸려있다. 트렌잭션이란? 각각의 작업 단위를 의미 ,
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(123);
    }
    //복제 커멘드 d
    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long previousCount = articleRepository.count(); //현재 알티클 레포지토리에 몇개있니?


        // When
        Article saveArticle = articleRepository.save(Article.of("new Article", "new content", "#spring"));//새로운걸 넣었어 !
        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1); // 전보다 1 많아진게 지금이랑 같니? 같아야해 !! 왜녀면 assert that 이기때문이야
    }
    @DisplayName("update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow(); //영속성인걸 가져와야함 그랙서 id 가져오기 왜냐면 기존의 것이 있어야 수정을 할 수 있기 때문
        String updatedHashtag= "#springboot"; //해시테그 가공
        article.setHashtag(updatedHashtag);
        // When
        Article saveArticle = articleRepository.saveAndFlush(article); //영속성에서 수정을 가한 아티클을 가져옴, saveAndFlush 업데이트 하지만 롤백 실제로 반영 x

        //Then
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag); // 해시태그 필드가 업데이트 되었는가?

    }
    @DisplayName("delete테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();


        // When
        articleRepository.delete(article); //영속성에서 수정을 가한 아티클을 가져옴, saveAndFlush 업데이트 하지만 롤백 실제로 반영 x

        //Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousCommentCount - deletedCommentsSize);
    }


}