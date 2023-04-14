package com.example.lab8.controller;

import com.example.lab8.entity.Film;
import com.example.lab8.repository.FilmRepository;
import com.example.lab8.service.UserActionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    private final UserActionsService userActionsService;

    public FilmController(UserActionsService userActionsService) {
        this.userActionsService = userActionsService;
    }

    @GetMapping("/list")
    public ModelAndView getAllFilms() {
        log.info("/list -> connection");
        ModelAndView mav = new ModelAndView("list-films");
        mav.addObject("film", filmRepository.findAll());
        userActionsService.info("Пользователь " + getUserName() +  " зашел на страницу со списком фильмов");
        return mav;
    }

    @GetMapping("/addFilmForm")
    public ModelAndView addStudentForm(){
        ModelAndView mav = new ModelAndView("add-film-form");
        Film film = new Film();
        mav.addObject("film", film);
        userActionsService.info("Пользователь " + getUserName() +  " зашел в форму добавления фильмов");
        return mav;
    }


    @PostMapping("/saveFilm")
    public  String saveFilm(@ModelAttribute Film film) {
        filmRepository.save(film);
        userActionsService.info("Пользователь " + getUserName() +  " сохранил новый фильм");
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long filmId){
        ModelAndView mav = new ModelAndView("add-film-form");
        Optional<Film> optionalFilm = filmRepository.findById(filmId);
        Film film = new Film();
        if (optionalFilm.isPresent()){
            film = optionalFilm.get();
        }
        mav.addObject("film", film);
        return mav;
    }

    @GetMapping("/deleteFilm")
    public String deleteFilm(@RequestParam Long filmId) {
        filmRepository.deleteById(filmId);
        userActionsService.info("Пользователь " + getUserName() +  " удалил фильм");
        return "redirect:/list";
    }

    private String getUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername();
    }
}
