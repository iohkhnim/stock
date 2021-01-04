package com.khoi.stock.dao.dao.impl;

import com.khoi.basecrud.dao.dao.impl.BaseDAOImpl;
import com.khoi.stock.dao.IStockDAO;
import com.khoi.stock.dto.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Transactional
@Repository
public class StockDAOImpl extends BaseDAOImpl<Stock, Integer> implements IStockDAO {

  /** {@inheritDoc} */
  @Override
  public int getStockByProductId(int product_id) {
    String hql = "SELECT SUM(obj.stock) FROM Stock obj WHERE obj.product_id = :prodid";
    Query query = entityManager.createQuery(hql);
    query.setParameter("prodid", product_id);
    return Integer.parseInt(query.setMaxResults(1).getSingleResult().toString());
  }

  /** {@inheritDoc} */
  @Override
  public int getBestStock(int product_id, int amount) {
    String hql =
        "SELECT obj.id FROM Stock obj WHERE obj.product_id = :prodid "
            + "AND obj.stock >= :amount ORDER BY obj.stock DESC";
    Query query = entityManager.createQuery(hql);
    query.setParameter("amount", amount);
    query.setParameter("prodid", product_id);
    try {
      return Integer.parseInt(query.setMaxResults(1).getSingleResult().toString());
    } catch (Exception ex) {
      return -1;
    }
  }

  /** {@inheritDoc} */
  @Override
  public int subtract(int id, int amount) {
    try {
      Stock stock = findByid(id);
      stock.setStock(stock.getStock() - amount);
      update(stock);
      return id;
    } catch (Exception ex) {
      System.out.println(ex);
      return -1;
    }
  }

  /** {@inheritDoc} */
  @Override
  public int getSupplierIdByStockId(int stock_id) {
    String hql = "SELECT obj.supplier_id FROM Stock obj WHERE obj.id = :stock_id";
    Query query = entityManager.createQuery(hql);
    query.setParameter("stock_id", stock_id);
    return Integer.parseInt(query.getResultList().get(0).toString());
  }

  @Override
  public Boolean addStock(int stock_id, int stock) {
    Stock stock1 = findByid(stock_id);
    stock1.setStock(stock1.getStock() + stock);
    return update(stock1);
    /*String hql =
        "UPDATE Stock obj SET obj.stock = obj.stock + :stock WHERE obj.product_id = :product_id";
    Query query = entityManager.createQuery(hql);
    query.setParameter("stock", stock);
    query.setParameter("product_id", product_id);
    return query.executeUpdate() > 0 ? true : false;*/
  }
}
