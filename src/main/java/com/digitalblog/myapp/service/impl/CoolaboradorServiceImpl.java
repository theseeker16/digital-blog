package com.digitalblog.myapp.service.impl;

import com.digitalblog.myapp.service.CoolaboradorService;
import com.digitalblog.myapp.domain.Coolaborador;
import com.digitalblog.myapp.repository.CoolaboradorRepository;
import com.digitalblog.myapp.service.dto.CoolaboradorDTO;
import com.digitalblog.myapp.service.mapper.CoolaboradorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Coolaborador.
 */
@Service
@Transactional
public class CoolaboradorServiceImpl implements CoolaboradorService{

    private final Logger log = LoggerFactory.getLogger(CoolaboradorServiceImpl.class);
    
    private final CoolaboradorRepository coolaboradorRepository;

    private final CoolaboradorMapper coolaboradorMapper;

    public CoolaboradorServiceImpl(CoolaboradorRepository coolaboradorRepository, CoolaboradorMapper coolaboradorMapper) {
        this.coolaboradorRepository = coolaboradorRepository;
        this.coolaboradorMapper = coolaboradorMapper;
    }

    /**
     * Save a coolaborador.
     *
     * @param coolaboradorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CoolaboradorDTO save(CoolaboradorDTO coolaboradorDTO) {
        log.debug("Request to save Coolaborador : {}", coolaboradorDTO);
        Coolaborador coolaborador = coolaboradorMapper.coolaboradorDTOToCoolaborador(coolaboradorDTO);
        coolaborador = coolaboradorRepository.save(coolaborador);
        CoolaboradorDTO result = coolaboradorMapper.coolaboradorToCoolaboradorDTO(coolaborador);
        return result;
    }

    /**
     *  Get all the coolaboradors.
     *  
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CoolaboradorDTO> findAll() {
        log.debug("Request to get all Coolaboradors");
        List<CoolaboradorDTO> result = coolaboradorRepository.findAll().stream()
            .map(coolaboradorMapper::coolaboradorToCoolaboradorDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one coolaborador by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CoolaboradorDTO findOne(Long id) {
        log.debug("Request to get Coolaborador : {}", id);
        Coolaborador coolaborador = coolaboradorRepository.findOne(id);
        CoolaboradorDTO coolaboradorDTO = coolaboradorMapper.coolaboradorToCoolaboradorDTO(coolaborador);
        return coolaboradorDTO;
    }

    /**
     *  Delete the  coolaborador by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coolaborador : {}", id);
        coolaboradorRepository.delete(id);
    }
}
