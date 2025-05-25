package com.ues.estudiantes.controller;

import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.model.Estudiante;
import com.ues.estudiantes.service.ObservacionService;
import com.ues.estudiantes.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/observaciones")
public class ObservacionWebController {

    private final ObservacionService observacionService;
    private final EstudianteService estudianteService;

    public ObservacionWebController(ObservacionService observacionService, EstudianteService estudianteService) {
        this.observacionService = observacionService;
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("observaciones", observacionService.obtenerTodas());
        return "observaciones/lista";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("observacion", new Observacion());
        model.addAttribute("estudiantes", estudianteService.obtenerTodos());
        return "observaciones/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Observacion observacion) {
        observacionService.guardar(observacion);
        return "redirect:/observaciones";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Observacion obs = observacionService.obtenerPorId(id).orElse(new Observacion());
        model.addAttribute("observacion", obs);
        model.addAttribute("estudiantes", estudianteService.obtenerTodos());
        return "observaciones/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        observacionService.eliminar(id);
        return "redirect:/observaciones";
    }

    @GetMapping("/buscar")
    public String buscar(@RequestParam("q") String q, Model model) {
        model.addAttribute("observaciones", observacionService.buscar(q));
        return "observaciones/lista"; // la misma vista que lista las observaciones
}

}