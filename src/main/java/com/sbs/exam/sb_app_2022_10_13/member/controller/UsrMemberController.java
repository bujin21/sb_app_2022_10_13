package com.sbs.exam.sb_app_2022_10_13.member.controller;

import com.sbs.exam.sb_app_2022_10_13.member.service.MemberService;
import com.sbs.exam.sb_app_2022_10_13.article.vo.Member;
import com.sbs.exam.sb_app_2022_10_13.util.Ut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrMemberController {

  private MemberService memberService;

  public UsrMemberController(MemberService memberService){
    this.memberService = memberService;
  }

  @RequestMapping("/usr/member/doJoin")
  @ResponseBody
  public Object doJoin(String loginId, String loginPw, String name, String nickname,
                       String cellphoneNo, String email) {
    int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);

    Member member = memberService.getMemberById(id);


    if( Ut.emty(loginId)) {
      return "loginId(을)를 입력 해주세요.";
    }

    if( Ut.emty(loginPw)) {
      return "loginPw(을)를 입력 해주세요.";
    }

    if(  Ut.emty(name)) {
      return "name(을)를 입력 해주세요.";
    }

    if(  Ut.emty(nickname)) {
      return "nickname(을)를 입력 해주세요.";
    }

    if(  Ut.emty(cellphoneNo)) {
      return "cellphoneNo(을)를 입력 해주세요.";
    }

    if(  Ut.emty(email)) {
      return "email(을)를 입력 해주세요.";
    }

    if ( id == -1 ) {
      return "해당 로그인아이디는 이미 사용중입니다.";
    }

    return member;
  }

}
