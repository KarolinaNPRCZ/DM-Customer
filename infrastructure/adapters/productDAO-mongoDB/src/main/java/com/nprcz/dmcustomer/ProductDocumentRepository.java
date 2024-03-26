package com.nprcz.dmcustomer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> getProductDocumentByProductSKUId(Integer productSKUId);
    List<ProductDocument> getProductDocumentsByProductNameContainsIgnoreCase(String productDocumentName);

    void deleteByProductSKUId(Integer id);


}
