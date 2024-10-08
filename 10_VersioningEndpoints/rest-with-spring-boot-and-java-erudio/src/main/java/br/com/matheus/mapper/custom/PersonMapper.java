package br.com.matheus.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.matheus.data.vo.v2.PersonVOV2;
import br.com.matheus.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAddress(person.getAddress());
		vo.setBirthDay(new Date());
		vo.setFirsName(person.getFirsName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAddress(person.getAddress());
		//vo.setBirthDay(new Date());
		entity.setFirsName(person.getFirsName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		
		return entity;
	}
}
