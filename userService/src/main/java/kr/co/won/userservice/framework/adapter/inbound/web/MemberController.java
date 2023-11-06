package kr.co.won.userservice.framework.adapter.inbound.web;

import kr.co.won.userservice.application.usecase.InQueryMemberUseCase;
import kr.co.won.userservice.application.usecase.RegisteredMemberUseCase;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberInfoDto;
import kr.co.won.userservice.framework.adapter.inbound.web.vo.MemberOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/members")
public class MemberController {

    private final RegisteredMemberUseCase registeredMemberUseCase;
    private final InQueryMemberUseCase inQueryMemberUseCase;

    public MemberController(RegisteredMemberUseCase registeredMemberUseCase, InQueryMemberUseCase inQueryMemberUseCase) {
        this.registeredMemberUseCase = registeredMemberUseCase;
        this.inQueryMemberUseCase = inQueryMemberUseCase;
    }

    @PostMapping
    public ResponseEntity<MemberOutputDto> registeredMemberResponse(@RequestBody MemberInfoDto memberInfoDto) {
        MemberOutputDto registeredMember = registeredMemberUseCase.registeredMember(memberInfoDto);
        URI uri = URI.create("/api/members/" + registeredMember.getId());
        return ResponseEntity.created(uri).body(registeredMember);
    }

    @GetMapping(path = "/{memberId}")
    public ResponseEntity<MemberOutputDto> findMemberResponse(@PathVariable(name = "memberId") long memberId) {
        MemberOutputDto findMember = inQueryMemberUseCase.findMember(memberId).orElse(null);
        if (findMember == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(findMember);
    }
}
