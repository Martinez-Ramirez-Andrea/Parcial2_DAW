package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes")
public class EstudianteWebController {

    private final EstudianteService service;

    public EstudianteWebController(EstudianteService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("estudiantes", service.obtenerTodos());
        return "estudiantes/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        return "estudiantes/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Estudiante estudiante) {
        service.guardar(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Estudiante est = service.obtenerPorId(id).orElse(new Estudiante());
        model.addAttribute("estudiante", est);
        return "estudiantes/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return "redirect:/estudiantes";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("q") String q, Model model) {
        model.addAttribute("estudiantes", service.buscar(q));
        return "estudiantes/lista";
    }
}
