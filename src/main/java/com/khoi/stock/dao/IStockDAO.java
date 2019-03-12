package com.khoi.stock.dao;

import com.khoi.basecrud.dao.IBaseDAO;
import com.khoi.stock.dto.Stock;

public interface IStockDAO extends IBaseDAO<Stock, Integer> {

  int getStock(int product_id);
}
