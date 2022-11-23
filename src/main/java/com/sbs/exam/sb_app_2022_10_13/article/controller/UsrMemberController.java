package com.sbs.exam.sb_app_2022_10_13.article.controller;

import com.sbs.exam.sb_app_2022_10_13.article.service.ArticleService;
import com.sbs.exam.sb_app_2022_10_13.article.service.MemberService;
import com.sbs.exam.sb_app_2022_10_13.article.vo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UsrMemberController {

  private MemberService memberService;

  public UsrMemberController(MemberService memberService){
    this.memberService = memberService;
  }

  @RequestMapping("/usr/member/doJoin")
  @ResponseBody
  public String doJoin(String loginId, String loginPw, String name, String nickname,
                        String cellphoneNo, String email) {
    memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

    return "성공";
  }

}
