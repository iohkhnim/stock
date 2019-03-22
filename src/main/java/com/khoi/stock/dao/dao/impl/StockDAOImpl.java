package com.khoi.stock.dao.dao.impl;

import com.khoi.basecrud.dao.dao.impl.BaseDAOImpl;
import com.khoi.stock.dao.IStockDAO;
import com.khoi.stock.dto.Stock;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class StockDAOImpl extends BaseDAOImpl<Stock, Integer> implements IStockDAO {

  /**
   * <p>This method retrieves stock from database of given product ID</p>
   * @param product_id Product ID needs to be retrieved stock
   * @return Return stock number of given product ID
   */
  @Override
  public int getStockByProductId(int product_id) {
    String hql = "SELECT obj.stock FROM Stock obj WHERE obj.product_id = :prodid";
    Query query = entityManager.createQuery(hql);
    query.setParameter("prodid", product_id);
    return Integer.parseInt(query.setMaxResults(1).getSingleResult().toString());
  }

  /**
   * <p>This method gets stock ID that has the largest amount of given product</p>
   * @param product_id product ID needs to be retrieved stock ID
   * @param amount Amount of bought product
   * @return Return stock ID or -1 if not enough stock
   */
  @Override
  public int getBestStock(int product_id, int amount) {
    String hql = "SELECT obj.id FROM Stock obj WHERE obj.product_id = :prodid "
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

  /**
   * <p>This method subtracts stock that has given stock ID</p>
   * @param id Stock ID needs to be subtracted
   * @param amount Number subtract
   * @return Return a boolean value according to result
   */
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

  /**
   * <p>This method retrieves supplier ID has given stock ID</p>
   * @param stock_id stock ID needs to be retrived its supplier ID
   * @return Return supplier ID of given stocj ID
   */
  @Override
  public int getSupplierIdByStockId(int stock_id) {
    String hql = "SELECT obj.supplier_id FROM Stock obj WHERE obj.id = :stock_id";
    Query query = entityManager.createQuery(hql);
    query.setParameter("stock_id", stock_id);
    return Integer.parseInt(query.getResultList().get(0).toString());
  }
}
