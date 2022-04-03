package io.gig.catchreview.api.controller.v1;

import io.gig.catchreview.api.utils.ApiResponse;
import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.domain.user.member.dto.MemberDto;
import io.gig.catchreview.core.domain.user.member.dto.SignUpForm;
import io.gig.catchreview.core.domain.user.member.dto.SignUpRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author : JAKE
 * @date : 2022/04/03
 */
@RestController
@Api(value = "SecureController V1")
@RequestMapping("/api/v1/secure")
@RequiredArgsConstructor
public class SecureControllerV1 {


    private final MemberService memberService;

    @ApiOperation(
            value = "회원가입 API"
    )
    @PostMapping("sign-up")
    public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody SignUpForm form, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ApiResponse.ERROR(HttpStatus.BAD_REQUEST, errors.getAllErrors()));
        }

        MemberDto newMember = memberService.signUp(form);

        return new ResponseEntity<>(ApiResponse.OK(newMember), HttpStatus.OK);
    }

}
