/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author dnlbe
 */
public interface Repository<T> {
    
    int create(T object) throws Exception;
    void update(T object) throws Exception;
    void delete(int id) throws Exception;
    Optional<T> select(int id) throws Exception;
    List<T> selectAll() throws Exception;
}
