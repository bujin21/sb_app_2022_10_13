package com.sbs.exam.sb_app_2022_10_13.controller;

import com.sbs.exam.sb_app_2022_10_13.service.ReplyService;
import com.sbs.exam.sb_app_2022_10_13.util.Ut;
import com.sbs.exam.sb_app_2022_10_13.vo.Reply;
import com.sbs.exam.sb_app_2022_10_13.vo.ResultData;
import com.sbs.exam.sb_app_2022_10_13.vo.Rq;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrReplyController {
  private ReplyService replyService;
  private Rq rq;

  public UsrReplyController(ReplyService replyService, Rq rq) {
    this.replyService = replyService;
    this.rq = rq;
  }

  @RequestMapping("/usr/reply/doWrite")
  @ResponseBody
  public String doWrite(String relTypeCode, int relId, String body, String replaceUri) {
    if (Ut.empty(relTypeCode)) {
      return rq.jsHistoryBack("relTypeCode(을)를 입력해주세요.");
    }

    if (Ut.empty(relId)) {
      return rq.jsHistoryBack("relId(을)를 입력해주세요.");
    }

    ResultData<Integer> writeArticleRd = replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId, body);
    int id = writeArticleRd.getData1();

    if(Ut.empty(replaceUri)){
      switch (relTypeCode) {
        case "article":
          replaceUri = Ut.f("../article/detail?id=%d", relId);
          break;
      }
    }

    return rq.jsReplace(writeArticleRd.getMsg(), replaceUri);
  }

  @RequestMapping("/usr/reply/doDelete")
  @ResponseBody
  public String doDelete(int id, String replaceUri) {
    if (Ut.empty(id)) {
      return rq.jsHistoryBack("id(을)를 입력해주세요.");
    }

    Reply reply = replyService.getForPrintReply(rq.getLoginedMember(), id);

    if(reply == null) {
      return rq.jsHistoryBack(Ut.f("%d번 댓글은 존재하지 않습니다.", id));
    }

    if(reply.isExtra__actorCanDelete() == false) {
      return rq.jsHistoryBack(Ut.f("%d번 댓글을 삭제할 권한이 없습니다.", id));
    }

    ResultData deleteReplyRd = replyService.deleteReply(id);

    if (Ut.empty(replaceUri)) {
      switch (reply.getRelTypeCode()) {
        case "article":
          replaceUri = Ut.f("../article/detail?id=%d", reply.getRelId());
          break;
      }
    }

    return rq.jsReplace(deleteReplyRd.getMsg(), replaceUri);
  }
}