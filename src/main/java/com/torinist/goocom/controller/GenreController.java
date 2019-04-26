package com.torinist.goocom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.torinist.goocom.controller.common.BaseController;
import com.torinist.goocom.resource.GenreResource;
import com.torinist.goocom.service.GenreService;

@RestController
@RequestMapping("/genre")
public class GenreController implements BaseController<GenreResource, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

	@Autowired
	private GenreService genreService;

	@Override
	@GetMapping("/listall")
	public List<GenreResource> getListAll() {
		logger.debug("GenreController#listAllを実行しました。");
		return genreService.findListAll();
	}

	@Override
	@GetMapping("/{id}")
	public GenreResource getOne(@PathVariable("id") Integer id) {
		GenreResource resource = new GenreResource();
		resource.setId(id);
		return genreService.findOne(resource);
	}

	@Override
	@PostMapping("/search")
	public List<GenreResource> search(GenreResource resource) {
		return genreService.search(resource);
	}

	@Override
	@PostMapping("/add")
	public GenreResource addOne(GenreResource genreResource) {
		return genreService.insertOne(genreResource);
	}

	@Override
	@PostMapping("/delete/{id}")
	public int deleteOne(@PathVariable("id") Integer id) {
		GenreResource resource = new GenreResource();
		resource.setId(id);
		return genreService.deleteOne(resource);
	}
}
