package com.example.accessingdatajpa.querydsl.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class GenericRepository {

	@Autowired
	EntityManager em;

	public <T> List<T> findAll(EntityPathBase<T> base, Predicate predicate) {
		return findAll(base, predicate, (String) null);
	}

	public <T> List<T> findAll(EntityPathBase<T> base, Predicate predicate, List<EntityPathBase<?>> toFetch) {
		JPAQuery<T> query = new JPAQuery<>(em);
		List<EntityPathBase<?>> rootsEx = new LinkedList<>();
		rootsEx.add(base);
		rootsEx.addAll(toFetch);
		var q = query.from(base);
		for (int i = 0; i < toFetch.size(); i++) {
			q = q.leftJoin(toFetch.get(i)).fetchAll();
		}
		EntityPathBase<T>[] paths = rootsEx.toArray(new EntityPathBase[toFetch.size()]);
		return q.where(predicate).select(paths).fetchAll().fetch().stream()
				.map(t -> t.get(0, base.getType())).collect(Collectors.toList());
	}

	public <T> List<T> findAll(EntityPathBase<T> base, Predicate predicate, String entityGraph) {
		JPAQuery<T> query = new JPAQuery<>(em);
		if (entityGraph != null) {
			query.setHint(QueryHints.HINT_LOADGRAPH, em.getEntityGraph(entityGraph));
		}

		return query.from(base).where(predicate).fetchAll().fetch();
	}
}
