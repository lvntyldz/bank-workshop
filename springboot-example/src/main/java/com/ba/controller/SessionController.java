package com.ba.controller;

import com.ba.dto.ColorDTO;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping(value = "/list")
    public String getFromSession(HttpServletRequest request) {
        List<ColorDTO> favoriteColors = getFavColors(request.getSession());
        return String.format("SessionId :  %s  \n  favoriteColors: %s ", request.getSession().getId(), favoriteColors);
    }

    @PostMapping(value = "/save")
    public String saveToSession(@RequestBody ColorDTO dto, HttpServletRequest request) {
        List<ColorDTO> favoriteColors = getFavColors(request.getSession());

        if (dto != null && dto.getColorName() != null) {
            favoriteColors.add(dto);
            request.getSession(true).setAttribute("favoriteColors", favoriteColors);
        }

        return "session updated";
    }

    @GetMapping(value = "/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "session cleared";
    }

    private List<ColorDTO> getFavColors(HttpSession session) {
        List<ColorDTO> favoriteColors = (List<ColorDTO>) session.getAttribute("favoriteColors");

        if (favoriteColors != null) {
            return favoriteColors;
        }
        return new ArrayList<>();
    }
}
