package com.example.fastCampusProjectBoardingApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),

})

@Entity
public class Article extends BaseEntity {//extends BaseEtitiy 하면 상속/BaseEtitiy 안에있는건 모두 Article 에 있음

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql은 identity방식으로 오토 인크리먼트가 만들어짐
    private Long id;


    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 10000) private String content;//본문 본문은 사이즈가 커서 인덱스 x 즉 @테이블 x

    @Setter private String hashtag; //얘만 null 나며지는 not null

    @ToString.Exclude
    @OrderBy("id") //id 기준으로 정렬
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); //이 아티클에 연동되어있는 comment는 중복을 허용하지 않고 다 여기에서 모아서 Collection 으로 보겟더



    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


