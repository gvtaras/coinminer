package net.tgv.coinminer.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.tgv.coinminer.model.Price;
import net.tgv.coinminer.repo.PriceRepository;
import net.tgv.coinminer.service.BitcoinPriceReader;
import net.tgv.coinminer.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MianController {

    @Autowired
    private PriceService priceService;

    @GetMapping(path = "/price")
    @ResponseBody
    public Double getPrice() throws IOException {

        return BitcoinPriceReader.readPriceJackson();
    }

    @GetMapping(path = "/price2")
    @ResponseBody
    public String getPriceJson() throws IOException {

        URL resource = this.getClass().getResource("/gemini_BTCUSD_1hr.csv");

        priceService.readFromFile(resource.getPath());

        return "Okay";
    }


}
