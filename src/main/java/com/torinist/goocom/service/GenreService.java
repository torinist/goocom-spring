package com.torinist.goocom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.torinist.goocom.entity.GenreEntity;
import com.torinist.goocom.repository.GenreRepository;
import com.torinist.goocom.resource.GenreResource;
import com.torinist.goocom.service.common.BaseServiceInterface;
import com.torinist.goocom.service.specification.GenreSpecifications;
import com.torinist.goocom.util.BeanUtils;

/**
 * ジャンルに関するロジックを実装するクラス
 * 
 * @author oki
 *
 */
@Service
public class GenreService implements BaseServiceInterface<GenreResource> {

	@Autowired
	GenreRepository genreRepository;

	/**
	 * すべてのジャンルを取得する.<br>
	 * ジャンルがなかった場合は空のリストを返す.
	 */
	@Override
	public List<GenreResource> findListAll() {
		List<GenreEntity> entities = genreRepository.findAll();
		List<GenreResource> resources = BeanUtils.newInstanceCopyList(entities, GenreResource.class);
		return resources;
	}

	/**
	 * resourceのIDでジャンルを取得する.
	 */
	@Override
	public GenreResource findOne(GenreResource resource) {
		GenreEntity genreEntity = genreRepository.findById(resource.getId()).get();
		return BeanUtils.newInstanceCopyProperties(genreEntity, GenreResource.class);
	}

	/**
	 * ジャンルをDBに登録する.
	 */
	@Override
	public GenreResource insertOne(GenreResource resource) {
		GenreEntity genreEntity = BeanUtils.newInstanceCopyProperties(resource, GenreEntity.class);
		genreEntity = insertGenre(genreEntity);
		return BeanUtils.newInstanceCopyProperties(genreEntity, GenreResource.class);
	}

	/**
	 * 指定された<ジャンル名>に部分一致するサークルのリストを返す.
	 */
	@Override
	public List<GenreResource> search(GenreResource resource) {
		GenreSpecifications genreSpecifications = new GenreSpecifications();
		List<GenreEntity> entities = genreRepository
				.findAll(Specification.where(genreSpecifications.nameContains(resource.getName())));
		return BeanUtils.newInstanceCopyList(entities, GenreResource.class);
	}

	@Override
	public int deleteOne(GenreResource resource) {
		genreRepository.deleteById(resource.getId());
		return 1;
	}

	/**
	 * ジャンルをDBに登録する.
	 * 
	 * @param entity GenreEntity
	 * @return 登録したジャンル
	 */
	GenreEntity insertGenre(GenreEntity entity) {
		return genreRepository.save(entity);
	}
}
