package com.ghostdovahkiin.librapi.purchase.services;

import com.ghostdovahkiin.librapi.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPagePurchaseService {
    Page<Purchase> listPurchases(Pageable pageable);
}
