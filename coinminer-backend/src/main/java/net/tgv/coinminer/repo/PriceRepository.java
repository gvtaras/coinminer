package net.tgv.coinminer.repo;

import net.tgv.coinminer.model.Price;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PriceRepository extends ElasticsearchRepository<Price, String> {

}
