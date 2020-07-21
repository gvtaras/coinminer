package net.tgv.coinminer.scheduler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.tgv.coinminer.model.Price;
import net.tgv.coinminer.service.BitcoinPriceReader;
import net.tgv.coinminer.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "external.data.read.chron.enabled", havingValue = "true")
public class CoinminerScheduler {

    private static Logger LOGGER = LoggerFactory.getLogger(CoinminerScheduler.class);

    @Autowired
    private PriceService priceService;

    @Scheduled(cron = "${external.data.read.chron}")
    public void readExternalData() throws IOException {
        Double aDouble = BitcoinPriceReader.readPriceJackson();

        Date date = new Date();
        String now = new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(date);

        Price price = new Price();

        price.setSymbol("BTCUSD");
        price.setDate(now);
        price.setTimestamp(date.getTime());
        price.setId(UUID.randomUUID().toString());

        price.setClose(aDouble.floatValue());
        price.setOpen(aDouble.floatValue());
        price.setHigh(aDouble.floatValue());
        price.setLow(aDouble.floatValue());

        price.setVolume(-1);

        LOGGER.debug("price ... {}", price);

        priceService.savePrice(price);

    }
}
