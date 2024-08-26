package com.apiWeb.courseApi.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.apiWeb.courseApi.Controllers.BookController;
import com.apiWeb.courseApi.data.vo.v1.BookVO;
import com.apiWeb.courseApi.exceptions.RequireObjecIsNulltException;
import com.apiWeb.courseApi.exceptions.ResourceNotFoundException;
import com.apiWeb.courseApi.mapper.DozerMapper;
import com.apiWeb.courseApi.model.Book;
import com.apiWeb.courseApi.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());
	@Autowired
	BookRepository repository;
	public List<BookVO> findAll() {
		logger.info("Finding all book! ");

		var books = DozerMapper.parseListObject(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {

				e.printStackTrace();
			}
		});
		return books;
	}

	public BookVO findById(Long id) {

		logger.info("Finding one book! ");

		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		var vo = DozerMapper.parseObject(entity, BookVO.class);
		try {
			vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	public BookVO create(BookVO book) throws Exception {

		if (book == null)
			throw new RequireObjecIsNulltException();

		logger.info("Create one book ");
		var entity = DozerMapper.parseObject(book, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}


	public BookVO update(BookVO book) throws Exception {
		if (book == null)
			throw new RequireObjecIsNulltException();
		logger.info("Update one book ");
		var entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());

		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);

		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void delete(Long id) {
		logger.info("Deleting one book ");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

}
