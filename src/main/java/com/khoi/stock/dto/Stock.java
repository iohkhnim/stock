package com.khoi.stock.dto;

import com.khoi.basecrud.dto.baseDTO;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock extends baseDTO implements Serializable {

  @Column(name = "product_id")
  private int product_id;

  @Column(name ="supplier_id")
  private int supplier_id;

  @Column(name = "stock")
  private int stock;

  public int getProduct_id() {
    return product_id;
  }

  public void setProduct_id(int product_id) {
    this.product_id = product_id;
  }

  public int getSupplier_id() {
    return supplier_id;
  }

  public void setSupplier_id(int supplier_id) {
    this.supplier_id = supplier_id;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
