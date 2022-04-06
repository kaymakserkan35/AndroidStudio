package com.betelgeuse.blockchainindicators.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SymbolStatusDTO {

        @SerializedName("base_currency")
        @Expose
        private String baseCurrency;
        @SerializedName("base_currency_scale")
        @Expose
        private Integer baseCurrencyScale;
        @SerializedName("counter_currency")
        @Expose
        private String counterCurrency;
        @SerializedName("counter_currency_scale")
        @Expose
        private Integer counterCurrencyScale;
        @SerializedName("min_price_increment")
        @Expose
        private Integer minPriceIncrement;
        @SerializedName("min_price_increment_scale")
        @Expose
        private Integer minPriceIncrementScale;
        @SerializedName("min_order_size")
        @Expose
        private Integer minOrderSize;
        @SerializedName("min_order_size_scale")
        @Expose
        private Integer minOrderSizeScale;
        @SerializedName("max_order_size")
        @Expose
        private Integer maxOrderSize;
        @SerializedName("max_order_size_scale")
        @Expose
        private Integer maxOrderSizeScale;
        @SerializedName("lot_size")
        @Expose
        private Integer lotSize;
        @SerializedName("lot_size_scale")
        @Expose
        private Integer lotSizeScale;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("auction_price")
        @Expose
        private Integer auctionPrice;
        @SerializedName("auction_size")
        @Expose
        private Integer auctionSize;
        @SerializedName("auction_time")
        @Expose
        private Integer auctionTime;
        @SerializedName("imbalance")
        @Expose
        private Integer imbalance;

        public String getBaseCurrency() {
            return baseCurrency;
        }

        public void setBaseCurrency(String baseCurrency) {
            this.baseCurrency = baseCurrency;
        }

        public Integer getBaseCurrencyScale() {
            return baseCurrencyScale;
        }

        public void setBaseCurrencyScale(Integer baseCurrencyScale) {
            this.baseCurrencyScale = baseCurrencyScale;
        }

        public String getCounterCurrency() {
            return counterCurrency;
        }

        public void setCounterCurrency(String counterCurrency) {
            this.counterCurrency = counterCurrency;
        }

        public Integer getCounterCurrencyScale() {
            return counterCurrencyScale;
        }

        public void setCounterCurrencyScale(Integer counterCurrencyScale) {
            this.counterCurrencyScale = counterCurrencyScale;
        }

        public Integer getMinPriceIncrement() {
            return minPriceIncrement;
        }

        public void setMinPriceIncrement(Integer minPriceIncrement) {
            this.minPriceIncrement = minPriceIncrement;
        }

        public Integer getMinPriceIncrementScale() {
            return minPriceIncrementScale;
        }

        public void setMinPriceIncrementScale(Integer minPriceIncrementScale) {
            this.minPriceIncrementScale = minPriceIncrementScale;
        }

        public Integer getMinOrderSize() {
            return minOrderSize;
        }

        public void setMinOrderSize(Integer minOrderSize) {
            this.minOrderSize = minOrderSize;
        }

        public Integer getMinOrderSizeScale() {
            return minOrderSizeScale;
        }

        public void setMinOrderSizeScale(Integer minOrderSizeScale) {
            this.minOrderSizeScale = minOrderSizeScale;
        }

        public Integer getMaxOrderSize() {
            return maxOrderSize;
        }

        public void setMaxOrderSize(Integer maxOrderSize) {
            this.maxOrderSize = maxOrderSize;
        }

        public Integer getMaxOrderSizeScale() {
            return maxOrderSizeScale;
        }

        public void setMaxOrderSizeScale(Integer maxOrderSizeScale) {
            this.maxOrderSizeScale = maxOrderSizeScale;
        }

        public Integer getLotSize() {
            return lotSize;
        }

        public void setLotSize(Integer lotSize) {
            this.lotSize = lotSize;
        }

        public Integer getLotSizeScale() {
            return lotSizeScale;
        }

        public void setLotSizeScale(Integer lotSizeScale) {
            this.lotSizeScale = lotSizeScale;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAuctionPrice() {
            return auctionPrice;
        }

        public void setAuctionPrice(Integer auctionPrice) {
            this.auctionPrice = auctionPrice;
        }

        public Integer getAuctionSize() {
            return auctionSize;
        }

        public void setAuctionSize(Integer auctionSize) {
            this.auctionSize = auctionSize;
        }

        public Integer getAuctionTime() {
            return auctionTime;
        }

        public void setAuctionTime(Integer auctionTime) {
            this.auctionTime = auctionTime;
        }

        public Integer getImbalance() {
            return imbalance;
        }

        public void setImbalance(Integer imbalance) {
            this.imbalance = imbalance;
        }

    }

