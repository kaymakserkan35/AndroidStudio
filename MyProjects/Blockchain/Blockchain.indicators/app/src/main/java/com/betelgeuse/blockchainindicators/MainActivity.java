package com.betelgeuse.blockchainindicators;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.betelgeuse.blockchainindicators.data.CallBackClasses.TickerListListener;
import com.betelgeuse.blockchainindicators.data.TickerDB;
import com.betelgeuse.blockchainindicators.data.dto.TickerDTO;
import com.betelgeuse.blockchainindicators.indicators.BollingerBand;
import com.betelgeuse.blockchainindicators.indicators.Data;
import com.betelgeuse.blockchainindicators.indicators.Period;
import com.betelgeuse.blockchainindicators.indicators.RelativeStrengthIndex;
import com.betelgeuse.blockchainindicators.indicators.SimpleMovingAverage;
import com.betelgeuse.blockchainindicators.indicators.SmoothingMethod;
import com.betelgeuse.blockchainindicators.indicators.Sort;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private LineChart lineChart;
    private Button    myButton;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = findViewById(R.id.lineChart);
        myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(v -> {

            TickerDB db = new TickerDB(MainActivity.this, FirebaseFirestore.getInstance());
            db.readTickerFromDateToNow("BTC", "2021-07-19", (tickerList -> {
                for (TickerDTO ticker: tickerList) {
                    db.reverseTicker(ticker);
                }
                List<Data> dataList = Data.convertTickerListToDataList(tickerList);
                BollingerBandsTESTİNG(dataList);
                multiLineChart(lineChart, dataList, Period.twentyDay);
            }));


        });
    }

    // combinedChart farklı chart türlerinden olmalı 3 tane lineChart ı kabul etmiyor son lineChart ı baz alıyor!!
    private void CombinedChartTESTİNG (CombinedChart combinedChart, List<Data> dataList, Period period) {
        ArrayList<Entry> lowerBands = new ArrayList<>();
        ArrayList<Entry> upperBands = new ArrayList<>();
        ArrayList<Entry> middleBands = new ArrayList<>();
        for (int i = 0; i < period.getCode(); i++) {
            lowerBands.add(new Entry(i, (float) dataList.get(i).lowerBand));
            upperBands.add(new Entry(i, (float) dataList.get(i).upperBand));
            middleBands.add(new Entry(i, (float) dataList.get(i).middleBand));
        }

        LineDataSet lowerBandDataSet = new LineDataSet(lowerBands, "lowerBand");
        LineDataSet upperBandDataSet = new LineDataSet(upperBands, "upperBands");
        LineDataSet middleBandDataSet = new LineDataSet(middleBands, "middleBands");

        LineData lowerBandLineData = new LineData(lowerBandDataSet);
        LineData upperBandLineData = new LineData(upperBandDataSet);
        LineData middleBandLineData = new LineData(middleBandDataSet);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(lowerBandLineData);
        combinedData.setData(upperBandLineData);
        combinedData.setData(middleBandLineData);

        combinedChart.setData(combinedData);
        combinedChart.invalidate();

    }

    private List<Data> convertTickerListToDataList (List<TickerDTO> tickerList) {
        List<Data> dataList = new ArrayList<>();
        for (TickerDTO ticker : tickerList) {
            Data data = new Data(ticker);
            dataList.add(data);
            // Log.d(data.calendar + " -->", data.symbol + ":" + data.price);
        }
        return dataList;
    }

    private void BollingerBandsTESTİNG (List<Data> dataList) {
        BollingerBand bollingerBand =
                new BollingerBand(dataList, Period.twentyDay, new SimpleMovingAverage(dataList, Period.twentyDay));
        bollingerBand.analyze();
        bollingerBand.sortDataByCalendar(Sort.ASCENDING);
    }

    private void RelativeStrengthIndexTESTİNG (List<Data> dataList) {
        RelativeStrengthIndex relativeStrengthIndex =
                new RelativeStrengthIndex(dataList, Period.tenDay,
                        new SmoothingMethod(dataList, Period.tenDay));
        relativeStrengthIndex.analyze();
        relativeStrengthIndex.sortDataByCalendar(Sort.ASCENDING);
    }

    private void multiLineChart (LineChart lineChart, List<Data> dataList, Period period) {
        ArrayList<Entry> prices = new ArrayList<>();
        ArrayList<Entry> lowerBands = new ArrayList<>();
        ArrayList<Entry> upperBands = new ArrayList<>();
        ArrayList<Entry> middleBands = new ArrayList<>();
        for (int i = 0; i < dataList.size() - period.getCode(); i++) {
            prices.add(new Entry(i, (float) dataList.get(i + period.getCode()).price));
            lowerBands.add(new Entry(i, (float) dataList.get(i + period.getCode()).lowerBand));
            upperBands.add(new Entry(i, (float) dataList.get(i + period.getCode()).upperBand));
            middleBands.add(new Entry(i, (float) dataList.get(i + period.getCode()).middleBand));
        }
        LineDataSet priceDataSet = new LineDataSet(prices, "price");
        priceDataSet.enableDashedLine(10, 10, 0);
        priceDataSet.setCircleColor(0);
        LineDataSet lowerDataSet = new LineDataSet(lowerBands, "lowerBand");
        lowerDataSet.setCircleColor(3);
        LineDataSet upperDataSet = new LineDataSet(upperBands, "upperBand");
        upperDataSet.setCircleColor(2);
        LineDataSet middleDataSet = new LineDataSet(middleBands, "middleBand");
        middleDataSet.setCircleColor(1);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lowerDataSet);
        dataSets.add(upperDataSet);
        dataSets.add(middleDataSet);
        dataSets.add(priceDataSet);
        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}