package com.khoi.stock.controller;

import com.khoi.stock.dto.Stock;
import com.khoi.stock.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("stock")
public class StockController {

  @Autowired
  private IStockService stockService;

  /**
   * <p>An API endpoint (/stock/create) with method POST creates a stock</p>
   * @param stock Stock information
   * @return Https status according to result
   */
  @PostMapping("create")
  public ResponseEntity<Void> create(@RequestBody Stock stock) {
    int id = stockService.create(stock);
    if (id > 0) {
      return new ResponseEntity<Void>(HttpStatus.CREATED);
    } else {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
  }

  /**
   * <p>An API endpoint (/stock/update) with method PUT updates a stock</p>
   * @param stock stock information
   * @return Https status according to result
   */
  @PutMapping("update")
  public ResponseEntity<Void> update(@RequestBody Stock stock) {
    Boolean flag = stockService.update(stock);
    if (flag.equals(true)) {
      return new ResponseEntity<Void>(HttpStatus.OK);
    } else {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
  }

}
