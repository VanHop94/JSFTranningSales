package test.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

@SuppressWarnings("unchecked")
public interface GenericDAO {

    public enum SORT_ORDER {
        ASC, DESC
    };

    public Object findById(Class entityClass, long id);

    public List findAll(Class entityClass);

    public List findAll(Class entityClass, String orderByAttribute, SORT_ORDER sortOrder);

    public List findAll(Class entityClass, int maxResult);

    public List findAll(Class entityClass, int firstRow, int maxResult);

    public List findAll(Class entityClass, int maxResult, String orderByAttribute, SORT_ORDER sortOrder);

    public void persist(Object entity, boolean flushImmediate);

    public void persist(Object entity);

    public Object merge(Object entity, boolean flushImmediate);

    public Object merge(Object entity);

    public void remove(Object entity, boolean flushImmediate);

    public void remove(Object entity);

    public Set<String> exludeNames();

    public Long getCount(Class entityClass);
    
    public Long getCount(Class entityClass, HashMap<String,Object> filterMap);

    public List findByFilter(Class entityClass, int firstRow, int maxResult, String orderByAttribute, SORT_ORDER sortOrder,
            HashMap<String, Object> filterMap);

}