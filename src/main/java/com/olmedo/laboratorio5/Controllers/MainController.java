package com.olmedo.laboratorio5.Controllers;

import java.util.List;


import com.olmedo.laboratorio5.Dao.EstudianteDAO;
import com.olmedo.laboratorio5.Domain.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class MainController {
    @Autowired
    private EstudianteDAO estudianteDAO;

    @RequestMapping("/listado")
    public ModelAndView initMain() {

        ModelAndView mav = new ModelAndView();
        List<Estudiante> estudiantes = null;
        try{
           estudiantes = estudianteDAO.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("estudiantes", estudiantes);
        mav.setViewName("listado");
        return mav;
    }
    @RequestMapping("/inicio")
    public ModelAndView inicio(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("estudiante",new Estudiante());
        mav.setViewName("index");
        return mav;
    }
    @RequestMapping("/form")
    public ModelAndView form(@Valid @ModelAttribute Estudiante estudiante, BindingResult result){
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()){
            mav.setViewName("index");
            return mav;
        }else{
            try {
                estudianteDAO.insertEstudiante(estudiante);
                Estudiante es= new Estudiante();
                mav.addObject("estudiante", es);
                mav.setViewName("index");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mav;
    }

}