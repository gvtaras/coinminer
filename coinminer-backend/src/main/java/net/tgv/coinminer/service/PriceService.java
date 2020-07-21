package net.tgv.coinminer.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.tgv.coinminer.model.Price;
import net.tgv.coinminer.repo.PriceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private static Logger LOGGER = LoggerFactory.getLogger(PriceService.class);

    @Autowired
    private PriceRepository repository;

    public void savePrice(Price price) {
        repository.save(price);
    }

    public void readFromFile(String path) throws IOException {

        boolean isFirstLine = true;
        int counter = 0;

        List<Price> prices = new LinkedList<>();

        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    prices.add(parsePrice(line));
                    counter ++;
                    if (counter >= 100) {
                        flush(prices);
                        counter = 0;
                        prices.clear();
                    }
                }
                // process the line
            }
        } finally {
            flush(prices);
            br.close();
        }
    }

    private Price parsePrice(String line) {
        String[] split = line.split(",");
        Price price = new Price();
        price.setId(UUID.randomUUID().toString());
        price.setTimestamp(Long.parseLong(split[0]));
        price.setDate(split[1]);
        price.setSymbol(split[2]);
        price.setOpen(Float.parseFloat(split[3]));
        price.setHigh(Float.parseFloat(split[4]));
        price.setLow(Float.parseFloat(split[5]));
        price.setClose(Float.parseFloat(split[6]));
        price.setVolume(Float.parseFloat(split[7]));

        return price;
    }

    private void flush(List<Price> prices) {
        LOGGER.info("Flush {} records", prices.size());
        repository.saveAll(prices);
    }

}
