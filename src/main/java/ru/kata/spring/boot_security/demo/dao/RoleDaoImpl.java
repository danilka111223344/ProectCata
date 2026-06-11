package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role findByName(String name) {
        try {
            TypedQuery<Role> query = entityManager.createQuery(
                    "FROM Role WHERE name = :name", Role.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }
}
