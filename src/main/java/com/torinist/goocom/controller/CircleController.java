package com.torinist.goocom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.torinist.goocom.controller.common.BaseController;
import com.torinist.goocom.resource.CircleResource;
import com.torinist.goocom.service.CircleService;

@Controller
@RequestMapping("/circle")
public class CircleController implements BaseController<CircleResource, Integer> {

	private static final Logger logger = LoggerFactory.getLogger(CircleController.class);

	@Autowired
	private CircleService circleService;

	@RequestMapping("/listall")
	@Override
	public List<CircleResource> getListAll() {
		logger.debug("CircleController#listAllを実行しました。");
		return circleService.findListAll();
	}

	@Override
	@GetMapping("/{id}")
	public CircleResource getOne(@PathVariable("id") Integer id) {
		CircleResource resource = new CircleResource();
		resource.setId(id);
		return circleService.findOne(resource);
	}

	@Override
	@PostMapping("/search")
	public List<CircleResource> search(CircleResource resource) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	@PostMapping("/add")
	public CircleResource addOne(CircleResource circleResource) {
		return circleService.insertOne(circleResource);
	}

	@Override
	@PostMapping("/delete/{id}")
	public int deleteOne(@PathVariable("id") Integer id) {
		CircleResource resource = new CircleResource();
		resource.setId(id);
		return circleService.deleteOne(resource);
	}
}
