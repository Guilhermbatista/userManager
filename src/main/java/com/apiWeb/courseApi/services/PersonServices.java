package com.apiWeb.courseApi.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.apiWeb.courseApi.Controllers.PersonController;
import com.apiWeb.courseApi.data.vo.v1.PersonVO;
import com.apiWeb.courseApi.data.vo.v2.PersonVOV2;
import com.apiWeb.courseApi.exceptions.RequireObjecIsNulltException;
import com.apiWeb.courseApi.exceptions.ResourceNotFoundException;
import com.apiWeb.courseApi.mapper.DozerMapper;
import com.apiWeb.courseApi.mapper.custom.PersonMapper;
import com.apiWeb.courseApi.model.Person;
import com.apiWeb.courseApi.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	@Autowired
	PersonRepository repository;

	@Autowired
	PersonMapper personMapper;

	public List<PersonVO> findAll() {
		logger.info("Finding all people! ");

		var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		return persons;
	}

	public PersonVO findById(Long id) {

		logger.info("Finding one person! ");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		try {
			vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	public PersonVO create(PersonVO person) throws Exception {

		if (person == null)
			throw new RequireObjecIsNulltException();

		logger.info("Create one person ");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Create one person with V2 ");
		var entity = personMapper.convertVoToEntity(person);
		var vo = personMapper.convertEntityToVo(repository.save(entity));
		return vo;
	}

	public PersonVO update(PersonVO person) throws Exception {
		if (person == null)
			throw new RequireObjecIsNulltException();
		logger.info("Update one person ");
		var entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one person ");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
