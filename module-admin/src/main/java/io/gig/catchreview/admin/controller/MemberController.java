package io.gig.catchreview.admin.controller;

import io.gig.catchreview.core.domain.user.member.MemberService;
import io.gig.catchreview.core.domain.user.member.dto.MemberDetailDto;
import io.gig.catchreview.core.domain.user.member.dto.MemberSearchDto;
import io.gig.catchreview.core.domain.user.member.dto.MemberUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author : Jake
 * @date : 2021-09-02
 */
@Controller
@RequestMapping("members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String index(MemberSearchDto searchDto, Model model) {
        model.addAttribute("pages", memberService.getMemberPageList(searchDto));
        model.addAttribute("condition", searchDto);
        return "members/list";
    }

    @GetMapping("{id}/edit")
    public String editForm(@PathVariable(name = "id") Long id, Model model) {

        MemberDetailDto dto = memberService.getDetail(id);
        model.addAttribute("dto", dto);

        return "members/editor";
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity update(@Valid @RequestBody MemberUpdateForm updateForm,
                                 Errors errors,
                                 RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors.getAllErrors());
        }

        Long memberId = memberService.update(updateForm);
        return ResponseEntity.ok().body(memberId);
    }
}
