package be.ghostwritertje.services.investing;

import be.ghostwritertje.dao.repository.FundPurchaseDao;
import be.ghostwritertje.domain.Person;
import be.ghostwritertje.domain.investing.FundPurchase;
import be.ghostwritertje.services.DomainObjectCrudServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jorandeboever
 * Date: 08-Oct-16.
 */
@Service
public class FundPurchaseServiceImpl extends DomainObjectCrudServiceSupport<FundPurchase> implements FundPurchaseService {
    @Autowired
    private FundPurchaseDao dao;

    @Override
    protected CrudRepository<FundPurchase, Integer> getDao() {
        return this.dao;
    }

    @Override
    public List<FundPurchase> findByOwner(Person owner) {
        return this.dao.findByOwner(owner);
    }
}
