package be.ghostwritertje.services.investing;

import be.ghostwritertje.domain.investing.FinancialInstrument;
import be.ghostwritertje.domain.investing.HistoricPrice;
import be.ghostwritertje.repository.HistoricPriceDao;
import be.ghostwritertje.services.DomainObjectCrudServiceSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Jorandeboever
 * Date: 15-Oct-16.
 */
@Service
public class HistoricPriceServiceImpl extends DomainObjectCrudServiceSupport<HistoricPrice> implements HistoricPriceService {
    @Autowired
    private HistoricPriceDao historicPriceDao;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private FinancialInstrumentService financialInstrumentService;

    @Override
    protected CrudRepository<HistoricPrice, Integer> getDao() {
        return this.historicPriceDao;
    }

    public void initHistoricPricesForStock(FinancialInstrument financialInstrument) {
        this.save(this.financeService.createHistoricPrices(financialInstrument));
    }

    public void initMissingHistoricPrices(){
        this.financialInstrumentService.findFinancialInstrumentsWithoutHistory().forEach(this::initHistoricPricesForStock);
    }

}
