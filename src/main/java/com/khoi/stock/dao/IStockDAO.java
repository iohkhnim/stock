package com.khoi.stock.dao;

import com.khoi.basecrud.dao.IBaseDAO;
import com.khoi.stock.dto.Stock;

public interface IStockDAO extends IBaseDAO<Stock, Integer> {

  /**
   * <p>This method retrieves stock from database of given product ID</p>
   * @param product_id Product ID needs to be retrieved stock
   * @return Return stock number of given product ID
   */
  int getStockByProductId(int product_id);

  /**
   * <p>This method gets stock ID that has the largest amount of given product</p>
   * @param product_id product ID needs to be retrieved stock ID
   * @param amount Amount of bought product
   * @return Return stock ID or -1 if not enough stock
   */
  int getBestStock(int product_id, int amount);

  /**
   * <p>This method subtracts stock that has given stock ID</p>
   * @param id Stock ID needs to be subtracted
   * @param amount Number subtract
   * @return Return a boolean value according to result
   */
  int subtract(int id, int amount);

  /**
   * <p>This method retrieves supplier ID has given stock ID</p>
   *
   * @param stock_id stock ID needs to be retrived its supplier ID
   * @return Return supplier ID of given stocj ID
   */
  int getSupplierIdByStockId(int stock_id);

  Boolean addStock(int stock_id, int stock);
}
