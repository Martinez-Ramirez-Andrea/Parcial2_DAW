package com.ues.estudiantes.service;

import com.ues.estudiantes.model.Observacion;
import com.ues.estudiantes.repository.ObservacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObservacionService {

    private final ObservacionRepository repo;

    public ObservacionService(ObservacionRepository repo) {
        this.repo = repo;
    }

    public List<Observacion> obtenerTodas() {
        return repo.findAll();
    }

    public Optional<Observacion> obtenerPorId(Long id) {
        return repo.findById(id);
    }

    public Observacion guardar(Observacion obs) {
        return repo.save(obs);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    // 🔍 MÉTODO DE BÚSQUEDA
    public List<Observacion> buscar(String texto) {
        return repo.findByDescripcionContainingIgnoreCase(texto);
    }
}
