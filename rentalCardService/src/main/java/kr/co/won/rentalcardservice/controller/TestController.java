package kr.co.won.rentalcardservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/testing")
public class TestController {



    @Operation(summary = "회원 탈퇴 요청", description = "회원 정보가 삭제됩니다.", tags = {"Member Controller"})
    @GetMapping
    public String testing() {
        return "hello world";
    }
}
