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

  @Override
  public int getStock(int product_id) {
    String hql = "SELECT obj.stock FROM Stock obj WHERE obj.product_id = :prodid";
    Query query = entityManager.createQuery(hql);
    query.setParameter("prodid", product_id);
    return Integer.parseInt(query.setMaxResults(1).getSingleResult().toString());
  }
}
