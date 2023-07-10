/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;


/**
 *
 * @author dnlbe
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }
   
    public static Repository getShoeRepository() throws Exception {
        return new ShoeRepository();
    }
   
    public static Repository getReceiptRepository() throws Exception {
        return new ReceiptRepository();
    }
    
    public static Repository getSellerRepository() throws Exception {
        return new SellerRepository();
    }
    
    public static Repository getCustomerRepository() throws Exception {
        return new CustomerRepository();
    }
    
    public static ArticleRepository getRepository() throws Exception {
        return new ArticleRepository();
    }
    
}
