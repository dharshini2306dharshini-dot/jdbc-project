package com.jdbc.service.impl;


import com.jdbc.dto.OwnerDTO;
import com.jdbc.exception.DuplicateOwnerException;
import com.jdbc.exception.OwnerNotFoundException;
import com.jdbc.repository.OwnerRepository;
import com.jdbc.repository.impl.OwnerRepositoryImpl;
import com.jdbc.service.OwnerService;
import com.jdbc.util.DBUtil;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnerServiceImpl implements OwnerService {
	private OwnerRepository ownerRepository;
	private static final String OWNER_NOT_FOUND="OWNER NOT FOUND BY ID:";
	private static final String OWNER_ALREADY_EXISTS="OWNER ALREADY EXISTS BY ID:";

	public OwnerServiceImpl(){
		this.ownerRepository=new OwnerRepositoryImpl();
	}
	@Override
	public void saveOwner(OwnerDTO ownerDTO) throws DuplicateOwnerException {
		OwnerDTO owner=ownerRepository.findOwner(ownerDTO.getId());
		if(Objects.nonNull(owner)){
			throw new DuplicateOwnerException(OWNER_ALREADY_EXISTS+ownerDTO.getId());
		}
		else{
			ownerRepository.saveOwner(ownerDTO);
		}

	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO=ownerRepository.findOwner(ownerId);
		if(Objects.isNull(ownerDTO)){
			throw new OwnerNotFoundException(OWNER_NOT_FOUND+ownerId);
		}else {
			return ownerDTO;
		}
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		OwnerDTO ownerDTO=ownerRepository.findOwner(ownerId);
		if(Objects.isNull(ownerDTO)){
			throw new OwnerNotFoundException(OWNER_NOT_FOUND+ownerId);
		}else {
			ownerRepository.updatePetDetails(ownerId, petName);
		}
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerRepository.findOwner(ownerId);
		if (Objects.isNull(ownerDTO)) {
			throw new OwnerNotFoundException(OWNER_NOT_FOUND + ownerId);
		} else {
			ownerRepository.deleteOwner(ownerId);
		}
	}

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAllOwners();
	}

	@Override
	public List<OwnerDTO> findOwner(String ownerEmailId, LocalDate petBirthDate) {
		return ownerRepository.findOwner(ownerEmailId,petBirthDate);
	}

}