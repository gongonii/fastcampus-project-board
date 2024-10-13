package com.example.fastCampusProjectBoardingApp.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Data REST -api 테스트")
@Transactional //spring 꺼로 무조건 넣어주기 자카르타 금지
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {

    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticles_thenReturnsArticlesJsonResponse() throws Exception {
        // Given


        // When
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


        // Then
    }
    @DisplayName("[api] 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestArticle_thenReturnsArticleJsonResponse() throws Exception {
        // Given


        // When
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


        // Then
    }
    @DisplayName("[api] 게시글 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticle_thenReturnsArticleCommentJsonResponse() throws Exception {
        // Given


        // When
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


        // Then
    }
    @DisplayName("[api] 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestArticleComments_thenReturnsArticleCommentsJsonResponse() throws Exception {
        // Given


        // When
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


        // Then
    }
    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestArticleComment_thenReturnsArticleCommentJsonResponse() throws Exception {
        // Given


        // When
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));


        // Then
    }



}
