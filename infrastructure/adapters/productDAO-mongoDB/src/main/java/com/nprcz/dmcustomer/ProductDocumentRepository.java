package com.nprcz.dmcustomer;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {
    Optional<ProductDocument> getProductDocumentByProductSKUId(Integer productSKUId);
}
