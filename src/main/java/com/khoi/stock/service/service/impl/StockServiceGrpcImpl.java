package com.khoi.stock.service.service.impl;

import com.google.rpc.Code;
import com.google.rpc.Status;
import com.khoi.stock.dao.IStockDAO;
import com.khoi.stockproto.*;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class StockServiceGrpcImpl extends StockServiceGrpc.StockServiceImplBase {

  @Autowired private IStockDAO stockDAO;

  /**
   * This method gets stock of given product Product ID in request and return it to gRPC client
   *
   * @param request Contains product ID needs to be get stock number
   * @param responseObserver Contains stock number of given product ID and send back to gRPC client
   */
  @Override
  public void getStock(GetStockRequest request, StreamObserver<GetStockResponse> responseObserver) {
    try {
      responseObserver.onNext(
          GetStockResponse.newBuilder()
              .setStock(stockDAO.getStockByProductId(request.getProductId()))
              .build());
      responseObserver.onCompleted();
    } catch (Exception ex) {
      Status status =
          Status.newBuilder().setCode(Code.NOT_FOUND_VALUE).setMessage("No such item").build();
      responseObserver.onError(StatusProto.toStatusRuntimeException(status));
    }
  }

  /**
   * This method gets stock ID of a product that has largest amount
   *
   * @param request Contains product ID and amount of product
   * @param responseObserver Contains stock ID that satisfies the condition or -1 if not enough
   *     stock to sell and send back to gRPC client
   */
  @Override
  public void getBestStock(
      GetBestStockRequest request, StreamObserver<GetBestStockResponse> responseObserver) {
    responseObserver.onNext(
        GetBestStockResponse.newBuilder()
            .setStockId(stockDAO.getBestStock(request.getProductId(), request.getAmount()))
            .build());
    responseObserver.onCompleted();
  }

  /**
   * This method subtracts stock which has given stock ID
   *
   * @param request Contains stock ID needs to be subtracted and amount subtract
   * @param responseObserver Contains stock ID if subtracts is successfully subtracted or -1 if
   *     there's error and send back to gRPC client
   */
  @Override
  public void subtract(SubtractRequest request, StreamObserver<SubtractResponse> responseObserver) {
    responseObserver.onNext(
        SubtractResponse.newBuilder()
            .setStockId(stockDAO.subtract(request.getStockId(), request.getAmount()))
            .build());
    responseObserver.onCompleted();
  }

  /**
   * This method retrieves supplier ID that has givent stock ID and return it to gRPC client
   *
   * @param request Contains stock ID needs to be retrieved its supplier ID
   * @param streamObserver Contains supplier ID of given stock ID and send it back to gRPC client
   */
  @Override
  public void getSupplierIdByStockId(
      GetSupplierIdByStockIdRequest request,
      StreamObserver<GetSupplierIdByStockIdResponse> streamObserver) {
    streamObserver.onNext(
        GetSupplierIdByStockIdResponse.newBuilder()
            .setSupplierId(stockDAO.getSupplierIdByStockId(request.getStockId()))
            .build());
    streamObserver.onCompleted();
  }

  @Override
  public void addStock(
      AddStockRequest request, StreamObserver<AddStockResponse> responseStreamObserver) {
    responseStreamObserver.onNext(
        AddStockResponse.newBuilder()
            .setResponse(stockDAO.addStock(request.getStockId(), request.getStock()))
            .build());
    responseStreamObserver.onCompleted();
  }
}
