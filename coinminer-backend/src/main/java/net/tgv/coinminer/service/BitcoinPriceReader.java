package net.tgv.coinminer.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class BitcoinPriceReader {

    public static Double readPriceJackson() throws IOException {

        URL sURL = new URL("https://blockchain.info/ticker"); //just a string

        ObjectMapper mapper = new ObjectMapper(); // just need one
//         Got a Java class that data maps to nicely? If so:
//        FacebookGraph graph = mapper.readValue(url, FaceBookGraph.class);
        // Or: if no class (and don't need one), just map to Map.class:
        Map<String,Object> map = mapper.readValue(sURL, Map.class);

        System.out.println(map);

        Map<String, Object> usd = (Map<String, Object>) map.get("USD");


        Double buy = (Double) usd.get("buy");
//        Double sell = (Double) eur.get("sell");

        return buy;

    }


}
