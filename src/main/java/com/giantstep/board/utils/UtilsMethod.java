package com.giantstep.board.utils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UtilsMethod {

    public String showMessageAndRedirectUri(@RequestParam("message") String message,
                                            @RequestParam("redirectUri") String redirectUri,
                                            Model model
                                            ) {

        model.addAttribute("message", message);
        model.addAttribute("redirectUri", redirectUri);

        return "utils/showMessageAndRedirectUri";
    }

}
