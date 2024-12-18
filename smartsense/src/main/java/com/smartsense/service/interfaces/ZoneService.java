package com.smartsense.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartsense.dto.zone.ZoneDTO;
import com.smartsense.dto.zone.UpdateZoneDTO;

/**
 * Service interface for Zone entity.
 * Defines methods for CRUD operations and additional business logic.
 */
public interface ZoneService {

   ZoneDTO getZoneById(String id);

   ZoneDTO getZoneById(String id, String... with);

   Page<ZoneDTO> getAllCategories(Pageable pageable, String title, String artist, Integer year);

   Page<ZoneDTO> getAllCategories(Pageable pageable, String title, String artist, Integer year, String... with);

   ZoneDTO addZone(ZoneDTO Zone);

   ZoneDTO updateZone(String ZoneId, UpdateZoneDTO Zone);

   void deleteZoneById(String ZoneId);
}