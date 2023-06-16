package sk.stuba.fei.uim.oop.assignment3.helper.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHelperRepository extends JpaRepository<WantedProduct, Long> {
    List<WantedProduct> findAll();

    WantedProduct findHelperByProductId (Long productId);



}
