package com.sbs.exam.sb_app_2022_10_13.article.service;

import com.sbs.exam.sb_app_2022_10_13.article.repository.ArticleRepository;
import com.sbs.exam.sb_app_2022_10_13.article.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private MemberRepository memberRepository;

  public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
//    memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
  }
}
