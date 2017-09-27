package com.digitalblog.myapp.repository;

import com.digitalblog.myapp.domain.PublicacionReportada;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PublicacionReportada entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicacionReportadaRepository extends JpaRepository<PublicacionReportada, Long> {

}
