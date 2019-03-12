package com.khoi.stock.controller;

import com.khoi.stock.dao.IStockDAO;
import com.khoi.stock.dto.Stock;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("stock")
public class StockController {

  @Autowired
  private IStockDAO stockDAO;

  @GetMapping("findAll")
  public ResponseEntity<List<Stock>> findAll() {
    return new ResponseEntity<List<Stock>>(stockDAO.findAll(), HttpStatus.OK);
  }

  @GetMapping("getStock/{product_id}")
  @ResponseBody
  public int getStock(
      @PathVariable("product_id") int product_id) {
    int i = stockDAO.getStock(product_id);
    return i;
  }
}
