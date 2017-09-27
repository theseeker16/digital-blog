package com.digitalblog.myapp.service.impl;

import com.digitalblog.myapp.service.ImagenPorPublicacionService;
import com.digitalblog.myapp.domain.ImagenPorPublicacion;
import com.digitalblog.myapp.repository.ImagenPorPublicacionRepository;
import com.digitalblog.myapp.service.dto.ImagenPorPublicacionDTO;
import com.digitalblog.myapp.service.mapper.ImagenPorPublicacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ImagenPorPublicacion.
 */
@Service
@Transactional
public class ImagenPorPublicacionServiceImpl implements ImagenPorPublicacionService{

    private final Logger log = LoggerFactory.getLogger(ImagenPorPublicacionServiceImpl.class);

    private final ImagenPorPublicacionRepository imagenPorPublicacionRepository;

    private final ImagenPorPublicacionMapper imagenPorPublicacionMapper;

    public ImagenPorPublicacionServiceImpl(ImagenPorPublicacionRepository imagenPorPublicacionRepository, ImagenPorPublicacionMapper imagenPorPublicacionMapper) {
        this.imagenPorPublicacionRepository = imagenPorPublicacionRepository;
        this.imagenPorPublicacionMapper = imagenPorPublicacionMapper;
    }

    /**
     * Save a imagenPorPublicacion.
     *
     * @param imagenPorPublicacionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ImagenPorPublicacionDTO save(ImagenPorPublicacionDTO imagenPorPublicacionDTO) {
        log.debug("Request to save ImagenPorPublicacion : {}", imagenPorPublicacionDTO);
        ImagenPorPublicacion imagenPorPublicacion = imagenPorPublicacionMapper.toEntity(imagenPorPublicacionDTO);
        imagenPorPublicacion = imagenPorPublicacionRepository.save(imagenPorPublicacion);
        return imagenPorPublicacionMapper.toDto(imagenPorPublicacion);
    }

    /**
     *  Get all the imagenPorPublicacions.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ImagenPorPublicacionDTO> findAll() {
        log.debug("Request to get all ImagenPorPublicacions");
        return imagenPorPublicacionRepository.findAll().stream()
            .map(imagenPorPublicacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one imagenPorPublicacion by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ImagenPorPublicacionDTO findOne(Long id) {
        log.debug("Request to get ImagenPorPublicacion : {}", id);
        ImagenPorPublicacion imagenPorPublicacion = imagenPorPublicacionRepository.findOne(id);
        return imagenPorPublicacionMapper.toDto(imagenPorPublicacion);
    }

    /**
     *  Delete the  imagenPorPublicacion by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImagenPorPublicacion : {}", id);
        imagenPorPublicacionRepository.delete(id);
    }
}
