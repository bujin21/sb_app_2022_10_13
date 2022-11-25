package com.sbs.exam.sb_app_2022_10_13.service;

import com.sbs.exam.sb_app_2022_10_13.repository.MemberRepository;
import com.sbs.exam.sb_app_2022_10_13.vo.Member;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  private MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository){
    this.memberRepository = memberRepository;
  }

  public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {

    // 로그인 아이디 중복 체크
    Member oldMember = getMemberByLoginId(loginId);

    if( oldMember != null ) {
      return -1;
    }

    // 이름 + 이메일 중복체크
    Member oldNameAndEmail = getMemberByNameAndEmail(name, email);

    if( oldNameAndEmail != null ) {
      return -2;
    }

   memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
   return memberRepository.getLastInsertId();
  }

  private Member getMemberByNameAndEmail(String name, String email) {
    return memberRepository.getMemberByNameAndEmail(name, email);
  }

  private Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public Member getMemberById(int id) {
    return memberRepository.getMemberById(id);
  }
}