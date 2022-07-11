package id.co.nds.catalogue.services;

import java.sql.Timestamp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.catalogue.entities.ProductEntity;
import id.co.nds.catalogue.entities.SaleEntity;
import id.co.nds.catalogue.exception.ClientException;
import id.co.nds.catalogue.exception.NotFoundException;
import id.co.nds.catalogue.models.SaleModel;
import id.co.nds.catalogue.repos.ProductRepo;
import id.co.nds.catalogue.repos.SaleRepo;
import id.co.nds.catalogue.validators.ProductValidator;
 
@Service
public class SaleService {
    @Autowired
    public SaleRepo saleRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductServices productService;
    
    public ProductValidator productValidator = new ProductValidator();

 @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class }) 
 public SaleEntity doSale(SaleModel saleModel) throws ClientException, NotFoundException {

        //validation
        productValidator.nullCheckProductId(saleModel.getProductId());
        if (!productRepo.existsById(saleModel.getProductId())) {
            throw new NotFoundException("Cannot find product with id: " + saleModel.getProductId());
        }

        ProductEntity product = productService.findById(saleModel.getProductId());
        if (product.getQuantity() < saleModel.getQuantity()) {
            throw new ClientException("product quantity is not enough");
        }
        product.setQuantity(product.getQuantity() - saleModel.getQuantity());

        productRepo.save(product);

        if(saleModel.getPrice() ==  null || saleModel.getQuantity() == null) {
            throw new ClientException("price or quantity cannot be null");
        }

        SaleEntity sale = new SaleEntity();

        sale.setProductId(saleModel.getProductId());
        sale.setPrice(saleModel.getPrice().doubleValue());
        sale.setQuantity(saleModel.getQuantity());
        sale.setTotalPrice(saleModel.getPrice().doubleValue() * saleModel.getQuantity());
        sale.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        return saleRepo.save(sale);
    }
}
