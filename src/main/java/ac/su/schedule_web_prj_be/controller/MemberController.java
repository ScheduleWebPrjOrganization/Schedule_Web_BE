package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.constant.UserTypeEnum;
import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.dto.LoginRequestDTO;
import ac.su.schedule_web_prj_be.dto.RegisterRequestDTO;
import ac.su.schedule_web_prj_be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        Optional<Member> optionalMember = memberService.findName(request.getName());

        if (!optionalMember.isPresent()) {
            return ResponseEntity.badRequest().body("회원이 존재하지 않습니다");
        }

        Member member = optionalMember.get();

        if (!passwordEncoder.matches(request.getPassword(), member.getPwd())) {
            return ResponseEntity.badRequest().body("비밀번호 불일치");
        }

        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestDTO request) {
        Optional<Member> existingMemberById = memberService.findName(request.getName());
        if (existingMemberById.isPresent()) {
            return ResponseEntity.badRequest().body("아이디가 이미 존재합니다");
        }

        Optional<Member> existingMemberByEmail = memberService.findByEmail(request.getEmail());
        if (existingMemberByEmail.isPresent()) {
            return ResponseEntity.badRequest().body("이메일이 이미 존재합니다");
        }

        if (!isValidPassword(request.getPwd())) {
            return ResponseEntity.badRequest().body("비밀번호는 6자 이상이어야 하며, 최소 숫자 하나와 알파벳 하나가 포함되어야 합니다");
        }

        if (!request.getPwd().equals(request.getVerifyingPwd())) {
            return ResponseEntity.badRequest().body("비밀번호와 확인 비밀번호가 일치하지 않습니다");
        }

        LocalDateTime createdAt = request.getCreatedAt();
        if (createdAt == null) {
            return ResponseEntity.badRequest().body("잘못된 날짜 형식입니다");
        }

        Member newMember = new Member();
        newMember.setName(request.getName());
        newMember.setPwd(passwordEncoder.encode(request.getPwd()));
        newMember.setEmail(request.getEmail());
        newMember.setLevel(UserTypeEnum.User.toString());
        newMember.setCreatedAt(createdAt); // createdAt 필드 설정
        memberService.save(newMember);

        return ResponseEntity.ok("회원가입 성공");
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$";
        return Pattern.matches(pattern, password);
    }
}
