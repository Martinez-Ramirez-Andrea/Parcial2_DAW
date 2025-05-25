package com.ues.estudiantes.repository;

import com.ues.estudiantes.model.Observacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservacionRepository extends JpaRepository<Observacion, Long> {

    // 🔍 Este método permite buscar por descripción (ignora mayúsculas/minúsculas)
    List<Observacion> findByDescripcionContainingIgnoreCase(String descripcion);
}
