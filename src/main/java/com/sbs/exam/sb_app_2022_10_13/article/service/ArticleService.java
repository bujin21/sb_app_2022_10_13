package com.sbs.exam.sb_app_2022_10_13.article.service;

import com.sbs.exam.sb_app_2022_10_13.article.repository.ArticleRepository;
import com.sbs.exam.sb_app_2022_10_13.article.vo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
  private ArticleRepository articleRepository;
  public ArticleService(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;

  }


  public Article getArticle(int id) {
    return articleRepository.getArticle(id);
  }


  public int writeArticle(String title, String body) {
    articleRepository.writeArticle(title, body);
    return articleRepository.getLastInsertId();
  }

  public void deleteArticle(int id) {
    articleRepository.deleteArticle(id);
  }

  public List<Article> getArticles() {
    return articleRepository.getArticles();
  }

  public void modifyArticle(int id, String title, String body) {
    articleRepository.modifyArticle(id, title, body);
  }
}