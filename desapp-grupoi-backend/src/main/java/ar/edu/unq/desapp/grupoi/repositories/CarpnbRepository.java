package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public abstract class CarpnbRepository<B, K extends Serializable> {

  private Class<B> beanClass;
  protected EntityManager entityManager;

  /**
   * Constructor por default. Inicializa la clase del bean.
   */
  public CarpnbRepository() {
    beanClass = (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }


  public B load(K id) {
    B entity = this.entityManager.find(beanClass, id);
    return Optional.ofNullable(entity).orElseThrow(() -> new NoResultException());
  }

  @Transactional
  public void save(B bean) {
    this.entityManager.persist(bean);
  }

  @Transactional
  public void update(B bean) {
    this.entityManager.merge(bean);
  }

  @Transactional
  public void delete(B bean) {
    this.entityManager.remove(bean);
  }

  public List<B> all() {
    Query query = this.entityManager.createQuery(selectAllQuery());
    return (List<B>) query.getResultList();
  }

  private String selectAllQuery() {
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT b FROM ")
        .append(beanClass.getName())
        .append(" b");
    return queryBuilder.toString();
  }
}
