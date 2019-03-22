package com.khoi.stock.dao;

import com.khoi.basecrud.dao.IBaseDAO;
import com.khoi.stock.dto.Stock;

public interface IStockDAO extends IBaseDAO<Stock, Integer> {

  int getStockByProductId(int product_id);

  int getBestStock(int product_id, int amount);

  int subtract(int id, int amount);

  int getSupplierIdByStockId(int stock_id);
}
