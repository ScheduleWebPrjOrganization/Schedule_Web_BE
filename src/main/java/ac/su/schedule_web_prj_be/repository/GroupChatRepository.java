package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.GroupChat;
import ac.su.schedule_web_prj_be.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface GroupChatRepository  extends JpaRepository<GroupChat, Long> {

    // 특정 유저의 채팅 조회
    List<GroupChat> findByMember(Member member);
}
