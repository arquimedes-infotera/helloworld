/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infotera.utils;

import br.com.infotera.exception.HelloworldException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author arquimedes
 */
public abstract class DAO<T> {

    @PersistenceContext(unitName = "helloworldPU")
    protected EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    protected Class<T> getClassOfT() {
        final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
        return clazz;
    }

    public T findById(Integer id) throws HelloworldException {
        try {
            return getEntityManager().find(getClassOfT(), id);
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("buscar registro", ex);
        }
    }

    public T singleByNamed(String jpql, Object... parameters) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNamedQuery(jpql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    qr.setParameter("p" + (i + 1), parameters[i]);
                }
            }
            qr.setMaxResults(1);
            return (T) qr.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("buscar registro", ex);
        }
    }

    public List listByNamed(String jpql, Object... parameters) throws HelloworldException {
        return listByNamed(null, jpql, parameters);
    }

    public List listByNamed(Integer qtRegistro, String jpql, Object... parameters) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNamedQuery(jpql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    qr.setParameter("p" + (i + 1), parameters[i]);
                }
            }
            if (qtRegistro != null) {
                qr.setMaxResults(qtRegistro);
            }
            return qr.getResultList();
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public T singleBySelect(String jpql, Object... parameters) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createQuery(jpql);
            ArrayList<String> listParameter = checkParameter(jpql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (listParameter.indexOf("" + (i + 1)) > -1) {
                        qr.setParameter("p" + (i + 1), parameters[i]);
                    }
                }
            }
            qr.setMaxResults(1);
            T result = (T) qr.getSingleResult();
            return result;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("buscar registro", ex);
        }
    }

    public List listBySelectQtRegistro(String jpql, Integer qtRegistro) throws HelloworldException {
        return listBySelect(qtRegistro, jpql);
    }

    public List listBySelect(String jpql, Object... parameters) throws HelloworldException {
        return listBySelect(jpql, parameters);
    }

    public List listBySelect(Integer qtRegistro, String jpql, Object... parameters) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createQuery(jpql);
            ArrayList<String> listParameter = checkParameter(jpql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (listParameter.indexOf("" + (i + 1)) > -1) {
                        qr.setParameter("p" + (i + 1), parameters[i]);
                    }
                }
            }
            if (qtRegistro != null) {
                qr.setMaxResults(qtRegistro);
            }
            List<Object> result = (List<Object>) qr.getResultList();
            return result;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public <T> List<T> listByNativeQuery(String jpql, Object... parameters) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNativeQuery(jpql);
            ArrayList<String> listParameter = checkParameter(jpql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (listParameter.indexOf("" + (i + 1)) > -1) {
                        qr.setParameter("p" + (i + 1), parameters[i]);
                    }
                }
            }
            List result = qr.getResultList();
            return Collections.checkedList(result, List.class);
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public List<Integer> listIntegerByNativeQuery(String jpql) throws HelloworldException {
        return listIntegerByNativeQuery(null, jpql);
    }

    public List<Integer> listIntegerByNativeQuery(Integer qtRegistro, String jpql) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNativeQuery(jpql);
            if (qtRegistro != null) {
                qr.setMaxResults(qtRegistro);
            }
            List<Integer> result = qr.getResultList();
            return result;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public List<Integer> listIntegerByNamedQuery(String jpql) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNativeQuery(jpql);
            List<Integer> result = qr.getResultList();
            return result;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public List<Integer> listIntegerBySelect(String jpql) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createQuery(jpql);
            List<Integer> result = qr.getResultList();
            return result;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public List<String> listStringByNativeQuery(String jpql) throws HelloworldException {
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNativeQuery(jpql);
            List<String> result = qr.getResultList();
            return result;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public Integer intBySelect(String query, Object... parameters) throws HelloworldException {
        Number result = (Number) this.singleBySelect(query, parameters);
        if (result == null) {
            return 0;
        } else {
            return result.intValue();
        }
    }

    public Double doubleBySelect(String query, Object... parameters) throws HelloworldException {
        Number result = (Number) this.singleBySelect(query, parameters);
        if (result == null) {
            return 0.0;
        } else {
            return result.doubleValue();
        }
    }

    public Date dateBySelect(String query, Object... parameters) throws HelloworldException {
        Date result = (Date) this.singleBySelect(query, parameters);
        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    public Date dateByNamed(String query, Object... parameters) throws HelloworldException {
        Date result = (Date) this.singleByNamed(query, parameters);
        if (result == null) {
            return null;
        } else {
            return result;
        }
    }

    public int intByNamed(String query, Object... parameters) throws HelloworldException {
        Number result = (Number) this.singleByNamed(query, parameters);
        if (result == null) {
            return 0;
        } else {
            return result.intValue();
        }
    }

    public Integer countBySelect(String dsQuery) throws HelloworldException {
        Long result = null;
        result = (Long) singleBySelect(dsQuery);
        return ((result != null) ? result.intValue() : 0);
    }

    public List listPaginacaoBySelect(String jpql, Integer paginacaoInicial, Integer paginacaoFinal) throws HelloworldException {
        List result;
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createQuery(jpql);
            checkParameter(jpql);
            if (paginacaoInicial != null) {
                qr.setFirstResult(paginacaoInicial);
                qr.setMaxResults(paginacaoFinal);
            }
            result = qr.getResultList();
            return result;
        } catch (NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public List listPaginacaoByNativeSelect(String jpql, Integer paginacaoInicial, Integer paginacaoFinal) throws HelloworldException {
        List result;
        try {
            EntityManager em = getEntityManager();
            javax.persistence.Query qr = em.createNativeQuery(jpql);
            checkParameter(jpql);
            if (paginacaoInicial != null) {
                qr.setFirstResult(paginacaoInicial);
                qr.setMaxResults(paginacaoFinal);
            }
            result = qr.getResultList();
            return result;
        } catch (javax.persistence.NoResultException ex) {
            return null;
        } catch (Exception ex) {
            throw new HelloworldException("listar registro", ex);
        }
    }

    public void save(Object entidade) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            if (entidade instanceof List) {
                for (Object o : (List) entidade) {
                    em.persist(o);
                }
            } else {
                em.persist(entidade);
            }
        } catch (OptimisticLockException ex) {
            throw new HelloworldException("erro ao salvar registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao salvar registro", ex);
        }
    }

    public boolean update(Object entidade) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            if (entidade instanceof List) {
                for (Object o : (List) entidade) {
                    em.merge(o);
                }
            } else {
                em.merge(entidade);
            }
        } catch (OptimisticLockException ex) {
            ex.printStackTrace();
            throw new HelloworldException("erro ao atualizar registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao atualizar registro", ex);
        }
        return true;

    }

    public void update(Class classe, String alias, String set, String where) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            String query = "UPDATE " + classe.getSimpleName() + " " + alias
                    + " SET " + set + " "
                    + " WHERE " + where;
            em.createQuery(query).executeUpdate();
        } catch (OptimisticLockException ex) {
            throw new HelloworldException("erro ao atualizar registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao atualizar registro", ex);
        }
    }

    public void update(Class classe, String alias, String set, String where, Object... parameters) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            String query = "UPDATE " + classe.getSimpleName() + " " + alias
                    + " SET " + set + " "
                    + " WHERE " + where;
            em.createQuery(query);
            javax.persistence.Query qr = em.createQuery(query);
            ArrayList<String> listParameter = checkParameter(query);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    if (listParameter.contains("" + (i + 1))) {
                        qr.setParameter("p" + (i + 1), parameters[i]);
                    }
                }
            }
            qr.executeUpdate();
        } catch (OptimisticLockException ex) {
            throw new HelloworldException("erro ao atualizar registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao atualizar registro", ex);
        }
    }

    public boolean remove(Object entidade) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            if (entidade instanceof List) {
                for (Object o : (List) entidade) {
                    em.remove(em.contains(o) ? o : em.merge(o));
                }
            } else {
                em.remove(em.contains(entidade) ? entidade : em.merge(entidade));
            }

        } catch (OptimisticLockException ex) {
            throw new HelloworldException("erro ao remover registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao remover registro", ex);
        }
        return true;

    }

    public void remove(Class classe, String alias, String sWhere) throws HelloworldException {
        EntityManager em = getEntityManager();
        try {
            String query = "DELETE " + classe.getSimpleName() + " " + alias + " WHERE  " + sWhere;
            em.createQuery(query).executeUpdate();
        } catch (OptimisticLockException ex) {
            throw new HelloworldException("erro ao remover registro");
        } catch (Exception ex) {
            throw new HelloworldException("erro ao remover registro", ex);
        }
    }
//
//    public void executeNativeQuery(String jpql) throws HelloworldException {
//        EntityManager em = getEntityManager();
//        try {
//            javax.persistence.Query qr = em.createNativeQuery(jpql);
//            qr.executeUpdate();
//        } catch (OptimisticLockException ex) {
//            throw new HelloworldException(ExceptionEnum.reservaAtualizadaPorOutroUsuarioEfetuarNovamente);
//        } catch (Exception ex) {
//            throw new HelloworldException(ExceptionEnum.processarRegistro, ex);
//        }
//    }

    private ArrayList<String> checkParameter(String jpql) {
        String[] list = jpql.split(":p");
        ArrayList<String> listParameter = new ArrayList<String>();
        for (int i = 0; i < list.length; i++) {
            String num = list[i].replaceAll("\\)", " ");
            int fim = num.indexOf(" ");
            if (fim == -1) {
                fim = num.length();
            }
            num = num.substring(0, fim).trim();
            listParameter.add(num);
        }
        return listParameter;
    }

    protected void flushClear() {
        EntityManager em = getEntityManager();
        em.flush();
        em.clear();
    }

    protected void flush() {
        EntityManager em = getEntityManager();
        em.flush();
    }

    protected void clear() {
        EntityManager em = getEntityManager();
        em.clear();
    }

    public static String ajustaLike(String s) {
        if (s != null) {
            s = s.trim().replace("'", "");
            return s;
        }
        return null;
    }

    public static String selectItemReturn(String id, String label) {
        return " new javax.faces.model.SelectItem( " + id + "," + label + " ) ";
    }

    public static String selectItemReturn(String id, String label, String checked) {
        return " new javax.faces.model.SelectItem( " + id + ", " + label + ", " + checked + " ) ";
    }

    public static String selectItemReturn(String id, String label, String checked, String descricao) {
        return " new javax.faces.model.SelectItem( " + id + ", " + label + ", " + descricao + ", " + checked + " ) ";
    }

    public String between(Date dtIni, Date dtFim, boolean stHoraFixa) {
        if (stHoraFixa) {
            return " BETWEEN '" + Utils.formatData(dtIni, "yyyy-MM-dd") + " 00:00' AND '" + Utils.formatData(dtFim, "yyyy-MM-dd") + " 23:59' ";
        } else {
            return " BETWEEN '" + Utils.formatData(dtIni, "yyyy-MM-dd HH:mm:ss") + "' AND '" + Utils.formatData(dtFim, "yyyy-MM-dd HH:mm:ss") + "' ";
        }
    }
}
