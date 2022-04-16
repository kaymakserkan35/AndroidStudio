package com.betelgeuse.blockchain.data.cache;

import com.betelgeuse.blockchain.data.dto.TickerDTO;

import java.util.List;

public interface ITickerCache {
    public boolean createTicker(TickerDTO ticker);
    public TickerDTO readTicker(String id);
    public List<TickerDTO> readTickersAll();
    public  List<TickerDTO> readTickersByQuery();
    public boolean updateTicker(TickerDTO ticker);
    public  boolean deleteTicker(TickerDTO ticker);

}
