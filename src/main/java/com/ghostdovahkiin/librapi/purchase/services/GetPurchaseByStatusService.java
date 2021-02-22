package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchase.Status;

import java.util.List;

@FunctionalInterface
public interface GetPurchaseByStatusService {
    List<Purchase> listByStatus(Status status);
}
