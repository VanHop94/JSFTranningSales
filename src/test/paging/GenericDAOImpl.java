package test.paging;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;

@SuppressWarnings("unchecked")
public class GenericDAOImpl implements GenericDAO {

	@In
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Object findById(Class entityClass, long id) {
		return getEntityManager().find(entityClass, id);
	}

	public List findAll(Class entityClass) {
		return getEntityManager().createQuery("select e from " + entityClass.getName() + " e ").getResultList();
	}

	public List findAll(Class entityClass, String orderByAttribute, SORT_ORDER sortOrder) {
		String sortOrderString = "desc";
		if (sortOrder == SORT_ORDER.ASC) {
			sortOrderString = "asc";
		}
		return getEntityManager().createQuery(
				"select e from " + entityClass.getName() + " e order by e." + orderByAttribute + " " + sortOrderString)
				.getResultList();
	}

	public List findAll(Class entityClass, int maxResult) {
		Query q = getEntityManager().createQuery("select e from " + entityClass.getName() + " e ");
		if (maxResult > 0) {
			q.setMaxResults(maxResult);
		}
		return q.getResultList();
	}

	public List findAll(Class entityClass, int firstRow, int maxResult) {
		Query q = getEntityManager().createQuery("select e from " + entityClass.getName() + " e ");
		if (firstRow > 0) {
			q.setFirstResult(firstRow);
		}
		if (maxResult > 0) {
			q.setMaxResults(maxResult);
		}
		return q.getResultList();
	}

	public List findAll(Class entityClass, int maxResult, String orderByAttribute, SORT_ORDER sortOrder) {
		String sortOrderString = "desc";
		if (sortOrder == SORT_ORDER.ASC) {
			sortOrderString = "asc";
		}
		Query q = getEntityManager().createQuery(
				"select e from " + entityClass.getName() + " e order by e." + orderByAttribute + " " + sortOrderString);
		if (maxResult > 0) {
			q.setMaxResults(maxResult);
		}
		return q.getResultList();
	}

	public void persist(Object entity, boolean flushImmediate) {
		getEntityManager().persist(entity);
		if (flushImmediate)
			getEntityManager().flush();
	}

	public void persist(Object entity) {
		this.persist(entity, false);
	}

	public Object merge(Object entity, boolean flushImmediate) {
		Object obj = getEntityManager().merge(entity);
		if (flushImmediate)
			getEntityManager().flush();
		return obj;
	}

	public Object merge(Object entity) {
		return this.merge(entity, false);
	}

	public void remove(Object entity, boolean flushImmediate) {
		getEntityManager().remove(getEntityManager().merge(entity));
		if (flushImmediate)
			getEntityManager().flush();
	}

	public void remove(Object entity) {
		this.remove(entity, false);
	}

	public Set<String> exludeNames() {
		return new HashSet<String>();
	}

	public Long getCount(Class entityClass) {
		Query q = getEntityManager().createQuery("select count(e) from " + entityClass.getName() + " e ");
		return (Long) q.getSingleResult();
	}

	public Long getCount(Class entityClass, HashMap<String, Object> filterMap) {
		StringBuffer query = new StringBuffer();
		query.append("select count(e) from " + entityClass.getName() + " e");
		// where clause
		query.append(this.getWhereClause(filterMap));
		Query q = getEntityManager().createQuery(query.toString());
		// parameter
		q = this.getParameteredQuery(q, filterMap);
		return (Long) q.getSingleResult();
	}

	public List findByFilter(Class entityClass, int firstRow, int maxResult, String orderByAttribute,
			SORT_ORDER sortOrder, HashMap<String, Object> filterMap) {
		StringBuffer query = new StringBuffer();
		query.append("SELECT e FROM " + entityClass.getName() + " e");
		// sort order
		String sortOrderString = "desc";
		if (sortOrder == SORT_ORDER.ASC) {
			sortOrderString = "asc";
		}
		// where clause
		query.append(this.getWhereClause(filterMap));
		// order by
		if (orderByAttribute != null) {
			query.append(" ORDER BY e." + orderByAttribute + " " + sortOrderString);
		}
		Query q = getEntityManager().createQuery(query.toString());
		// parameter
		q = this.getParameteredQuery(q, filterMap);
		if (firstRow > 0) {
			q.setFirstResult(firstRow);
		}
		if (maxResult > 0) {
			q.setMaxResults(maxResult);
		}
		return q.getResultList();
	}

	private String getWhereClause(HashMap<String, Object> filterMap) {
		StringBuffer query = new StringBuffer();
		if (filterMap != null && !filterMap.isEmpty()) {
			query.append(" WHERE");
			boolean first = true;
			for (String column : filterMap.keySet()) {
				if (first) {
					first = false;
				} else {
					query.append(" AND");
				}
				if (filterMap.get(column) instanceof String) {
					query.append(" UPPER(e." + column + ") LIKE :" + column);
				} else {
					query.append(" e." + column + " = :" + column);
				}
			}
		}
		return query.toString();
	}

	private Query getParameteredQuery(Query q, HashMap<String, Object> filterMap) {
		if (filterMap != null && !filterMap.isEmpty()) {
			for (String column : filterMap.keySet()) {
				if (filterMap.get(column) instanceof String) {
					String value = (String) filterMap.get(column);
					q.setParameter(column, value.toUpperCase() + "%");
				} else {
					q.setParameter(column, filterMap.get(column));
				}
			}
		}
		return q;
	}

}