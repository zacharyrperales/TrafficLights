package com.example.trafficlights;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class WebMvcConfig {
    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("twilio", new Twilio());
        model.addAttribute("trafficLight", new TrafficLight());
        return "index";
    }

    @PostMapping("/")
    public String postIndex(@Valid Twilio twilio, @Valid TrafficLight trafficLight, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        trafficLight.setTrafficLightColor(twilio.getBody());
        twilio.sendMessage();
        return "save";
    }
}

