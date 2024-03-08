package com.nprcz.dmcustomer;


import org.springframework.data.mongodb.repository.MongoRepository;

interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {
}
