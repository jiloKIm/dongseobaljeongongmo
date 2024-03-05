package com.example.demo.Controller;

import com.example.demo.Entity.Building;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.demo.Service.BuildService;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller // @RestController를 제거하고 @Controller만 사용
@RequestMapping("/build")
public class BuildController {
    private final BuildService buildService;


    @GetMapping("/save")
    public String save() {

        return "form"; // HTML 템플릿 파일 이름을 반환
    }



    @PostMapping("/save")
    public String save(@RequestParam String name, @RequestParam String electricKwh, @RequestParam String heatingCoolingArea,
                       @RequestParam String gasKwh, @RequestParam String floorArea  ) {
        Building build= buildService.save(name, electricKwh, heatingCoolingArea, gasKwh, floorArea);
        log.info(String.valueOf(build.getId()));

        return "redirect:/build/result/" + build.getId(); // 리다이렉션 URL 반환
    }

    @GetMapping("/result/{build_id}")
    public String view(@PathVariable("build_id") Long buildid, Model model) {
        BuildService.ClassificationResult classs = buildService.view(buildid);
        log.info( classs.getClassName());
        model.addAttribute("className", classs.getClassName());
        model.addAttribute("solution", classs.getSolution());
        model.addAttribute("sum",classs.getSum());

        return "solution"; // HTML 템플릿 파일 이름을 반환
    }
}
