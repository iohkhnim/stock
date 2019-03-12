package com.khoi.stock.service.service.impl;

import com.khoi.stock.dao.IStockDAO;
import com.khoi.stockproto.GetStockRequest;
import com.khoi.stockproto.GetStockResponse;
import com.khoi.stockproto.StockServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class StockServiceGrpcImpl extends StockServiceGrpc.StockServiceImplBase {

  @Autowired
  private IStockDAO stockDAO;

  @Override
  public void getStock(GetStockRequest request, StreamObserver<GetStockResponse> responseObserver) {
    try {
      responseObserver.onNext(
          GetStockResponse.newBuilder().setStock(stockDAO.getStock(request.getProductId()))
              .build());
      responseObserver.onCompleted();
    } catch (Exception ex) {
      System.out.println(ex);
    }
  }
}
