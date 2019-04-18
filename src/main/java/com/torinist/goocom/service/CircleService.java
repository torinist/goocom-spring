package com.torinist.goocom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.torinist.goocom.entity.CircleEntity;
import com.torinist.goocom.repository.CircleRepository;
import com.torinist.goocom.resource.CircleResource;
import com.torinist.goocom.service.common.BaseServiceInterface;
import com.torinist.goocom.util.BeanUtils;

/**
 * サークルに関するロジックを実装するクラス
 * 
 * @author oki
 *
 */
@Service
public class CircleService implements BaseServiceInterface<CircleResource> {

	@Autowired
	CircleRepository circleRepository;

	/**
	 * すべてのサークルを取得する.<br>
	 * サークルがなかった場合は空のリストを返す.
	 */
	@Override
	public List<CircleResource> findListAll() {
		List<CircleEntity> entities = circleRepository.findAll();
		List<CircleResource> resources = BeanUtils.newInstanceCopyList(entities, CircleResource.class);
		return resources;
	}

	/**
	 * resourceのIDに紐づくサークルを取得する.
	 */
	@Override
	public CircleResource findOne(CircleResource resource) {
		CircleEntity circleEntity = circleRepository.findById(resource.getId()).get();
		return BeanUtils.newInstanceCopyProperties(circleEntity, CircleResource.class);
	}

	/**
	 * サークルをDBに登録する.
	 */
	@Override
	public CircleResource insertOne(CircleResource resource) {
		CircleEntity circleEntity = BeanUtils.newInstanceCopyProperties(resource, CircleEntity.class);
		circleEntity = insertCircle(circleEntity);
		return BeanUtils.newInstanceCopyProperties(circleEntity, CircleResource.class);
	}

	/**
	 * 指定された<名前>に部分一致するサークルのリストを返す.
	 */
	@Override
	public List<CircleResource> search(CircleResource resource) {
		if (resource == null) {
			return new ArrayList<CircleResource>();
		}
		List<CircleEntity> entities = circleRepository.findByNameContainingOrderByIdAsc(resource.getName());
		return BeanUtils.newInstanceCopyList(entities, CircleResource.class);
	}

	@Override
	public int deleteOne(CircleResource resource) {
		circleRepository.deleteById(resource.getId());
		return 1;
	}

	/**
	 * サークルをDBに登録する.
	 * 
	 * @param entity CircleEntity
	 * @return 登録したサークル
	 */
	CircleEntity insertCircle(CircleEntity entity) {
		return circleRepository.save(entity);
	}
}
