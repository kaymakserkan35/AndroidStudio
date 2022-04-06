const timeZone = "UTC+3 (TRT)";
const { initializeApp, applicationDefault, cert } = require('firebase-admin/app');
const https = require('https');
const functions = require('firebase-functions');
const info = functions.config().info;
const administor = require('firebase-admin');
const { service } = require('firebase-functions/v1/analytics');
const { log } = require('firebase-functions/logger');
const { Server } = require('http');
const { resolve } = require('path');
const { setPriority } = require('os');
const { error } = require('console');

administor.initializeApp(functions.config());
const currencyapiURL = "https://api.currencyapi.com/v2/latest?apikey=af16ae00-9416-11ec-84ba-c5e8c4460348&base_currency=USD";
const baseCurrency = "USD";
/*---------------------------------------------------------------------------------------------------------------------------------------------------------*/

class ErrorResponse {
  constructor(message = "") {
    this.message = message;
  }
  message = "";
}
class H {
  static errorLog(className = "", methodName = "", errorMessage = "") {
    console.error(className + ":" + methodName + "-->" + errorMessage);
  }
  static infoLog(className = "", methodName = "", errorMessage = "") {
    console.info(className + ":" + methodName + "-->" + errorMessage);
  }
  static warnLog(className = "", methodName = "", errorMessage = "") {
    console.warn(className + ":" + methodName + "-->" + errorMessage);
  }

}
class MyDate {
  dateObj;
  timestamp = 0;  // specific time in milliseconds..
  day = 0;    //  1 of 7
  date = 0;   //  1 of 31
  year = 0;   //  2022
  month = 0;  //  1 of 12 --> 0 of 11
  hour = "";  //  12 : 50
  constructor(timeZone = "Londra/Greenwich") {
    this.dateObj = new Date();
    this.hour = this.dateObj.getUTCHours() + ":" + this.dateObj.getUTCMinutes();
    this.timestamp = this.dateObj.getTime();
    this.day = this.dateObj.getUTCDay();
    this.date = this.dateObj.getUTCDate();
    this.year = this.dateObj.getUTCFullYear();
    this.month = this.dateObj.getUTCMonth() + 1;
    //H.infoLog(MyDate.name, "constructor", this.getCalendar() + " ---  " + this.hour);
  }
  getCalendar() {
    var date = this.date.toString();
    var month = this.month.toString();
    var year = this.year.toString();
    if (parseInt(date) < 10) {
      date = "0" + date;
    }
    if (parseInt(month) < 10) {
      month = "0" + month;
    }
    return (year + "-" + month + "-" + date);
  }

  isTimeBetween(hourSmall = "", hourLarge = "") {
    var hourSmallMinutes = parseInt(hourSmall.split(":")[0]) * 60 + parseInt(hourSmall.split(":")[1]);
    var hourLargeMinutes = parseInt(hourLarge.split(":")[0]) * 60 + parseInt(hourLarge.split(":")[1]);
    var hourNowMinutes = parseInt(this.hour.split(":")[0]) * 60 + parseInt(this.hour.split(":")[1]);
    if (hourSmallMinutes < hourNowMinutes && hourNowMinutes < hourLargeMinutes) { return true; }
    else { return false; }
  }

  isWeekend() {
    var dayOfWeek = this.dateObj.getUTCDay();
    if (dayOfWeek > 5) { return true; }
    else { return false; }
  }


}
class TickerDTO {
  static schema = `${TickerDTO.name}/{tickerId}/{year}/{calendar}`;
  symbol = "";
  baseCurrency = "";
  toCurrency = "";
  open = 0;
  high = 0;
  low = 0;
  price = 0;  // Price/Close, Current price 
  candle = 0;
  timeStamp = 0;  // When last update time
  calendar = "";
  year = ""
  constructor(symbol = "", price = 0) {
    this.symbol = symbol.toUpperCase();;
    this.price = price;
    this.init();
  }


  init() {
    var myDate = new MyDate(timeZone.valueOf());
    this.timeStamp = myDate.timestamp; ///administor.firestore.FieldValue.serverTimestamp();
    this.baseCurrency = this.symbol.split("/")[0];
    this.toCurrency = this.symbol.split("/")[1];
    this.calendar = myDate.getCalendar();
    this.year = myDate.year.toString();
  }
}

class AbstractService {
  service;
  constructor(service) {
    this.service = service;
  }
  isParsable(stringResponse) {
    try {
      var parsedObject = JSON.parse(stringResponse);
      if (parsedObject && typeof parsedObject === "object") {
        return parsedObject;
      }
    } catch (e) {
      H.errorLog(Service.name, "isParsable", e);
      return false;
    }

  }
  setGetRequest(url = "", response) {
    https.get(url, (res) => {
      if (200 > res.statusCode || res.statusCode > 300) {
        response(new ErrorResponse(res.statusMessage));
      }
      var responsedString = "";
      res.on('data', (d) => {
        responsedString += d;
      });

      res.on('end', function () {
        response(responsedString);
      });
    }).on('error', (err) => {
      response(new ErrorResponse(err.message));
      H.errorLog(DatabaseManager.name, "getTickers_currencyapi", err.message);
    });
  }
  getTickers(url = "", callbackTickerList) { }
  ProcessRequest(tickerListListener) { }
}
class Service_currencyapi extends AbstractService {
  updateInterval = "5 Minute";
  url = 'https://api.currencyapi.com/v2/latest?apikey=af16ae00-9416-11ec-84ba-c5e8c4460348&base_currency=USD';
  constructor(service = new AbstractService()) { super(service); }
  convertCurrencyApiResponseToTickerlist(responsedString, callbackTickerList) {
    var tickerList = [];
    var result = this.isParsable(responsedString);

    if (result == false) {
      callbackTickerList(new ErrorResponse(" stringResponse is  not Parsable!!"))
      return;
    }
    for (const property in result.data) {
      var symbol = property;
      var price = result.data[symbol];
      var ticker = new TickerDTO(baseCurrency + "/" + symbol, price);
      tickerList.push(ticker);
      //console.log(currency.symbol + "-->" + currency.price);

    }
    callbackTickerList(tickerList);
  }
  getTickers(url, callbackTickerList) {
    this.setGetRequest(url, (response) => {

      if (response instanceof ErrorResponse) {
        callbackTickerList(response);
        return;
      }
      this.convertCurrencyApiResponseToTickerlist(response, (tickerList) => {
        callbackTickerList(tickerList);
      });
    });
  }
  ProcessRequest(tickerListListener) {
    this.getTickers(this.url, (tickerList) => {
      if (tickerList instanceof ErrorResponse) {
        if (this.service != null) {
          this.service.ProcessRequest(tickerListListener);
        }

      }
      else {
        tickerListListener(tickerList);
      }
    });
  }
}
class Service_currencyapi2 extends AbstractService {
  updateInterval = "1 Hour";
  url = "https://currencyapi.net/api/v1/rates?key=4AcK5c5598q8LR9W6YWsTgRv2Pa9YuhicQKD&output=JSON";
  constructor(service = new AbstractService()) { super(service); }


  convertExchangerateResponseToTickerList(responsedString, callbackTickerList) {
    var tickerList = [new TickerDTO("", 0)];
    var result = this.isParsable(responsedString);
    if (!result) {
      return;
    }
    for (const property in result.rates) {
      var symbol = property;
      if (symbol == "USD") {
        continue;
      }
      var price = result.rates[property];
      var ticker = new TickerDTO(baseCurrency + "/" + symbol, price);
      tickerList.push(ticker);
    }
    callbackTickerList(tickerList);

  }
  getTickers(url, callbackTickerList) {
    this.setGetRequest(url, (responsedString) => {
      if (responsedString instanceof ErrorResponse) {
        return;
      }
      this.convertExchangerateResponseToTickerList(responsedString, (tickerList) => {
        callbackTickerList(tickerList);
      });
    });

  }
  ProcessRequest(tickerListListener) {
    this.getTickers(this.url, (tickerList) => {
      if (tickerList instanceof ErrorResponse) {
        if (this.service != null) {
          this.service.ProcessRequest(tickerListListener);
        }

      }
      else {
        tickerListListener(tickerList);
      }
    });
  }

}

class Service_exchangerate extends AbstractService {
  updateInterval = "24 hour";
  url = " https://v6.exchangerate-api.com/v6/df61600aad0912e84b3e472c/latest/USD";
  constructor(service = new AbstractService()) { super(service); }
  convertExchangerateResponseToTickerList(responsedString, callbackTickerList) {
    var tickerList = [new TickerDTO("", 0)];
    var result = this.isParsable(responsedString);
    if (!result) {
      return;
    }
    for (const property in result.conversion_rates) {
      var symbol = property;
      if (symbol == "USD") {
        continue;
      }
      var price = result.conversion_rates[property];
      var ticker = new TickerDTO(baseCurrency + "/" + symbol, price);
      tickerList.push(ticker);
    }
    callbackTickerList(tickerList);

  }
  getTickers(url, callbackTickerList) {
    this.setGetRequest(url, (responsedString) => {
      if (responsedString instanceof ErrorResponse) {
        return;
      }
      this.convertExchangerateResponseToTickerList(responsedString, (tickerList) => {
        callbackTickerList(tickerList);
      });
    });

  }
  ProcessRequest(tickerListListener) {
    this.getTickers(this.url, (tickerList) => {
      if (tickerList instanceof ErrorResponse) {
        if (this.service != null) {
          this.service.ProcessRequest(tickerListListener);
        }

      }
      else {
        tickerListListener(tickerList);
      }
    });
  }
}

class Service_blockChain extends AbstractService {
  url = "https://api.blockchain.com/v3/exchange/tickers";
  convertBlockChainResponseToTickerList(responsedString, callbackTickerList) {
    var tickerList = [new TickerDTO("", 0)];
    var result = this.isParsable(responsedString);

    if (result == false) {
      callbackTickerList(new ErrorResponse(" stringResponse is  not Parsable!!"))
      return;
    }
    for (var obj in result) {

      var symbol = (result[obj].symbol).replace("-", "/");
      var price = result[obj].last_trade_price;
      var ticker = new TickerDTO(symbol, price);
      tickerList.push(ticker);
    }
    callbackTickerList(tickerList);
  }
  getTickers(url, callbackTickerList) {
    this.setGetRequest(url, (response) => {

      if (response instanceof ErrorResponse) {
        callbackTickerList(response);
        return;
      }
      this.convertBlockChainResponseToTickerList(response, (tickerList) => {
        callbackTickerList(tickerList);
      });
    });
  }
  ProcessRequest(tickerListListener) {
    this.getTickers(this.url, (tickerList) => {
      if (tickerList instanceof ErrorResponse) {
        if (this.service != null) {
          this.service.ProcessRequest(tickerListListener);
        }

      }
      else {
        tickerListListener(tickerList);
      }
    });
  }
}

class SeedDatabase {
  set_Usd_Btc(url = "https:/...", callBackTickerList) {
    var service = new AbstractService(null);
    service.setGetRequest(url, (responseString) => {
      /*
                  "s": "USD/EUR",  // Symbol
                  "o": "3.672241", // Open
                  "h": "3.691708", // High
                  "l": "3.654802", // Low
                  "c": "3.673337", // Price/Close
                  "a": "3.674465", // Ask
                  "b": "3.671711", // Bid
                  "ch": "0.001096", // Change in 1 day candle
                  "cp: "0.03%", // Change in percentage
                  "t": "1583238543", // When update last time Time Unix Format (UTC)
                  "tm": "2020-03-03 12:29:03" // When update last time (UTC)
                 */
      var tickerList = [new TickerDTO("", "")]
      var responsedOBJ = JSON.parse(responseString);
      var symbol = responsedOBJ.info.symbol;
      if (symbol.split("/")[0] == "USD") {
        var dataObj = responsedOBJ.response;
        for (const property in dataObj) {
          //console.log(dataObj[property]);
          var obj = dataObj[property];
          var ticker = new TickerDTO("", 0);
          ticker.symbol = symbol.split("/")[0] + "/" + symbol.split("/")[1];
          ticker.baseCurrency = symbol.split("/")[0];
          ticker.toCurrency = symbol.split("/")[1];
          ticker.price = parseFloat(obj.c);
          ticker.low = parseFloat(obj.l);
          ticker.high = parseFloat(obj.h);
          ticker.open = parseFloat(obj.o);
          ticker.timeStamp = obj.t;

          ticker.candle = ticker.price - ticker.open;
          var calendar = obj.tm.split(" ")[0];
          ticker.calendar = calendar.split("-")[0] + "-" + calendar.split("-")[1] + "-" + calendar.split("-")[2];
          ticker.year = calendar.split("-")[0];
          /-------------------------------------*/
          tickerList.push(ticker);
          //console.log(ticker);
        }
      }
      if (symbol.split("/")[1] == "USD") {
        var dataObj = responsedOBJ.response;
        for (const property in dataObj) {
          //console.log(dataObj[property]);
          var obj = dataObj[property];
          var ticker = new TickerDTO("", 0);
          ticker.symbol = symbol.split("/")[1] + "/" + symbol.split("/")[0];
          ticker.baseCurrency = symbol.split("/")[1];
          ticker.toCurrency = symbol.split("/")[0];
          ticker.price = 1 / parseFloat(obj.c);
          ticker.low = 1 / parseFloat(obj.h);  // high ile low yer degistirir.
          ticker.high = 1 / parseFloat(obj.l); // high ile low yer degistirir.
          ticker.open = 1 / parseFloat(obj.o);
          ticker.candle = (1 / ticker.price) - (1 / ticker.open);
          var calendar = obj.tm.split(" ")[0];
          ticker.calendar = calendar.split("-")[0] + "-" + calendar.split("-")[1] + "-" + calendar.split("-")[2];
          ticker.year = calendar.split("-")[0];
          /-------------------------------------*/
          tickerList.push(ticker);
          //console.log(ticker);
        }
      }
      callBackTickerList(tickerList);
    });
  }
}


class DatabaseManager {

  validateTicker(ticker = new TickerDTO("", 0)) {
    if (ticker.calendar.length != 10) {
      H.errorLog(DatabaseManager.name, "validateTicker", "calendar 's lenght must be 10");
      return false;
    }
    if ((ticker.baseCurrency + "-" + ticker.toCurrency) == undefined) {
      H.errorLog(DatabaseManager.name, "setTickers", "undefinedPath");
      return false;
    }
    /*-----------------------------*/
    return true;
  }
  setTicker(ticker = new TickerDTO("", ""), mergeValue = true) {
    var isValid = this.validateTicker(ticker);
    if (!isValid) {
      return;
    }
    const data = {
      symbol: ticker.symbol,
      price: ticker.price,  // Price/Close, Current price 
      timeStamp: ticker.timeStamp,//administor.firestore.FieldValue.serverTimestamp(),// When last update time
      calendar: ticker.calendar
    }
    if (ticker.open != 0) {
      data.open = ticker.open;
    }
    if (ticker.high != 0) {
      data.high = ticker.high;
    }
    if (ticker.low != 0) {
      data.low = ticker.low;
    }
    if (ticker.candle != 0) {
      data.candle = ticker.candle;
    }
    // merge:true --> eski field lar kalsın. silinmesin demek!
    const document = administor.firestore().collection(TickerDTO.name).doc(ticker.baseCurrency + "-" + ticker.toCurrency).collection(ticker.year).doc(ticker.calendar);
    document.set(data, { merge: mergeValue }).then((result) => {
      //H.infoLog(DatabaseManager.name, "setTicker", ticker.symbol + "successfully set");
    }).catch(err => {
      H.errorLog(DatabaseManager.name, "setTicker", err);
    });

  }

  setTickersWithPromise(tickerList = [ticker = new TickerDTO("", "")], timeout) {

    //does not work
    tickerList.forEach(ticker => {

      var promise = new Promise(resolve => {
        const document = administor.firestore().collection(TickerDTO.name).doc(ticker.baseCurrency + "-" + ticker.toCurrency).collection(ticker.year).doc(ticker.date);
        document.set({
          symbol: ticker.symbol,
          price: ticker.price,  // Price/Close, Current price 
          timeStamp: ticker.timeStamp,//administor.firestore.FieldValue.serverTimestamp(),// When last update time
          calendar: ticker.calendar,
          year: ticker.year,
        }).then((result) => {
          //H.infoLog(DatabaseManager.name, "setTicker", ticker.symbol + "succescully set");

        }).catch(err => {
          H.errorLog(DatabaseManager.name, "setTicker", ticker.symbol + err);
        });

        setTimeout(resolve, timeout);
      });

    });



  }

  setTickers(tickerList = [new TickerDTO("", "")], writeAtTime = 0, mergeValue = true) {
    let batch = administor.firestore().batch();
    let count = 0;

    tickerList.forEach(ticker => {
      const documentRef = administor.firestore().collection(TickerDTO.name).doc(ticker.baseCurrency + "-" + ticker.toCurrency).collection(ticker.year).doc(ticker.calendar);
      var isValid = this.validateTicker(ticker);
      if (!isValid) {
        return;
      }
      const data = {
        symbol: ticker.symbol,
        price: ticker.price,  // Price/Close, Current price 
        timeStamp: ticker.timeStamp,//administor.firestore.FieldValue.serverTimestamp(),// When last update time
        calendar: ticker.calendar
      }
      if (ticker.open != 0)    { data.open = ticker.open; }
      if (ticker.high != 0)    { data.high = ticker.high; }
      if (ticker.low != 0)     { data.low = ticker.low; }
      if (ticker.candle != 0)  { data.candle = ticker.candle; }
      batch.set(documentRef, data, { merge: mergeValue }
      );
      count = count + 1;
      if (count >= writeAtTime || count >= tickerList.length) {
        H.infoLog("DatabaseManager" + "setTickers" + "batches is  commiting....");
        batch.commit().then(writeResult => {
          H.infoLog("DatabaseManager" + "setTickers" + "batches is successfully committed.");
          batch = administor.firestore().batch();
          count = 0;
        });
      }
    });
  }
  createTicker(ticker = new TickerDTO("", "")) {
    const document = administor.firestore().collection(TickerDTO.name).doc(ticker.baseCurrency + "-" + ticker.toCurrency + "-" + ticker.calendar);
    document.create({
      symbol: ticker.symbol,
      price: ticker.price,  // Price/Close, Current price 
      timeStamp: ticker.timeStamp, // When last update time
      calendar: ticker.calendar,
      year: ticker.year,
    }).then((result) => {
      H.infoLog(DatabaseManager.name, "createTicker", result);
    }).catch(err => {
      H.infoLog(DatabaseManager.name, "createTicker", err);

    });
  }
  readById(documentId = "", collectionName = "", callBack) {
    const document = administor.firestore().doc(`/${collectionName}/${documentId}`).get().then((dataObj) => {
      callBack(dataObj);
    });
  }
  readByQuery(pathName = "", pathValue = "", filterOperation = "==", collectionName = "", callBack) {
    const document = administor.firestore().collection(collectionName).where(pathName, filterOperation, pathValue).get().then((dataList) => {
      callBack(dataList);
    })
  }
}


exports.onCreateTicker = functions.firestore.document('TickerDTO/{tickerId}/{year}/{calendar}')
  .onCreate((snap, context) => {
    const data = snap.data();
    if (!data.low) { data.low = data.price; }
    if (!data.open) { data.open = data.price; }
    if (!data.high) { data.high = data.price; }
    data.candle = data.price - data.open;
    H.infoLog("DataManager", "onCreate()", "low : " + data.low + "open : " + data.open + "high : " + data.high + "candle : " + data.candle);
    return snap.ref.set(data, { merge: true });
  });

exports.onUpdateTicker = functions
  .runWith({
    timeoutSeconds: 60,
    memory: "256MB",
  })
  .firestore.document('TickerDTO/{tickerId}/{year}/{calendar}')
  .onUpdate((change, context) => {
    const data = change.after.data();
    const previousData = change.before.data();
    if (data.price == previousData.price) {
      H.infoLog("", "onUpdate()", "price is same!! returning...");
      return null;
    }
    // functions.logger.debug("old ->" + previousData.symbol + "new ->" + data.symbol);
    // functions.logger.debug("old price : " + previousData.price + "new  price : " + data.price);
    // functions.logger.debug(!previousData || data.price == previousData.price);
    if (previousData.high < data.price) { data.high = data.price; data.low = previousData.low; }
    //functions.logger.debug("new high->" + data.high);
    if (previousData.low > data.price) { data.low = data.price; data.high = previousData.high; }
    data.open = previousData.open;
    data.candle = data.price - data.open;
    //H.infoLog("DataManager", "onUpdate()", "previousData : " + Object.keys(previousData));
    //H.infoLog("DataManager", "onUpdate()", "newData : " + Object.keys(data));
    // data.open = previousData.open;
    return change.after.ref.set(data, { merge: true });
  });
exports.refreshTickers = functions
  .runWith({
    timeoutSeconds: 60,
    memory: "256MB",
  })
  .pubsub.schedule('every 300 minutes')
  .onRun((context) => {
    /*----------------------------------------------*/
    var myDate = new MyDate("Londra");
    if (myDate.isWeekend()) {
      H.infoLog("myDate.isWeekend-- >" + " hafta sonu!! borsa kapalı!! ");
      //return "hafta sonu!!";
    }
    if (myDate.isTimeBetween("09:00", "17:00")) {
      H.infoLog("myDate.isTimeBetween-- >" + "09:00 - 20:00 disindayiz...borsa kapalı!! ");
      //return " borsa kapalı!! ";
    }
    /*-----------------------------------------------*/
    var databaseManager = new DatabaseManager();

    var exchangerateService = new Service_exchangerate(null);
    var currencApiService = new Service_currencyapi(exchangerateService);
    var currencApiService2 = new Service_currencyapi2(currencApiService);



    currencApiService2.ProcessRequest(tickerList => {
      databaseManager.setTickers(tickerList, 500, true);
    });
    /*-----------------------------------------------------*/
    return "refresh method executed!";
  });


exports.setTickerTesting = functions.https.onRequest((request, response) => {
  var databaseManager = new DatabaseManager();
  var ticker = new TickerDTO("A/B", Math.random() * 10);
  databaseManager.setTicker(ticker, true);
  response.send(ticker);
});

exports.seedTickers_USD_BTC = functions.https.onRequest((request, response) => {
  var url = "https://fcsapi.com/api-v3/forex/history?id=6729&period=1d&access_key=SmsOjuhdCSvAFzL59ELjVmE";
  var databaseManager = new DatabaseManager();
  var seedDatabase = new SeedDatabase();
  seedDatabase.set_Usd_Btc(url, (tickerList) => {
    databaseManager.setTickers(tickerList, 500, true);
  });
  response.send("<h>database seeding...</h>");
});

/*---------------------------------------------------------------------------------------------------*/

/*-----------------------  wait for press key to exit from console  -----------------------------------*/
//console.log("wait..");
//require('readline')
//    .createInterface(process.stdin, process.stdout)
//    .question("Press [Enter] to exit...", function () {
//        process.exit();
//    });