import { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import WebSocketService from './services/WebSocketService';

// import NodeWalletConnect from "@walletconnect/node";
// import WalletConnectQRCodeModal from "@walletconnect/qrcode-modal";

// Create connector

// module.exports = { 
//   resolve: {
//     fallback: { "crypto": require.resolve("crypto-browserify") }
//   }
// };

const COIN_OBJECT = {
  'matic':'matic-network',
  'btc': 'bitcoin',
  'ada': 'cardano',
  'avax': 'avalanche-2',
  'dot': 'polkadot',
  'eth': 'ethereum',
}

function App() {

  const [ state, setState ] = useState({ value : ''});
  const [ img, setImg ] = useState({value: '/logoBCat.png'});
  const [ coinData, setCoinData ] = useState({ value : null});
  const [ err, setErr ] = useState({ value: null });

  // const walletConnector = new NodeWalletConnect(
  //   {
  //     bridge: "https://bridge.walletconnect.org", // Required
  //   },
  //   {
  //     clientMeta: {
  //       description: "WalletConnect NodeJS Client",
  //       url: "https://nodejs.org/en/",
  //       icons: ["https://nodejs.org/static/images/logo.svg"],
  //       name: "WalletConnect",
  //     },
  //   }
  // );
  
  // // Check if connection is already established
  // if (!walletConnector.connected) {
  //   // create new session
  //   walletConnector.createSession().then(() => {
  //     // get uri for QR Code modal
  //     const uri = walletConnector.uri;
  //     // display QR Code modal
  //     WalletConnectQRCodeModal.open(
  //       uri,
  //       () => {
  //         console.log("QR Code Modal closed");
  //       },
  //       true // isNode = true
  //     );
  //   });
  // }
  
  // // Subscribe to connection events
  // walletConnector.on("connect", (error, payload) => {
  //   if (error) {
  //     throw error;
  //   }
  
  //   // Close QR Code Modal
  //   WalletConnectQRCodeModal.close(
  //     true // isNode = true
  //   );
  
  //   // Get provided accounts and chainId
  //   const { accounts, chainId } = payload.params[0];
  // });
  
  // walletConnector.on("session_update", (error, payload) => {
  //   if (error) {
  //     throw error;
  //   }
  
  //   // Get updated accounts and chainId
  //   const { accounts, chainId } = payload.params[0];
  // });
  
  // walletConnector.on("disconnect", (error, payload) => {
  //   if (error) {
  //     throw error;
  //   }
  
  //   // Delete walletConnector
  //});
  const root = __dirname;

  function handleInput(event) {
    setState({value: event.target.value});
  }
  useEffect(() => {console.log('rendering app component');},[coinData]);
  //TO-DO: fix the use of ErrorMessage as a component.. currently not working at all
  const ErrorMessage = () => {
    if (err){
      return <p> value={err}></p>
    } else {
      return ''
    }
  }

  async function handleSubmit(event){
    event.preventDefault();
    // const request = {
    //   currency: state.value.toUpperCase(),
    // };
    try{
      const names = await axios.get('http://localhost:8081/consultAllPrices'); 
      console.log('Backend results -----> ');
      console.log(names);
      const coin = names.data.filter(coin => coin.symbol === state.value ); 
      
      if(!coin){
        throw Error('No hay monedas con ese nombre');
      }
      
      const maticPrices = axios.get(`https://api.coingecko.com/api/v3/coins/${coin.map(coin => coin.id)}`).then(data => {
        console.log(data);
        setCoinData({ value: data.data });
        setImg({ value: data.data.image.large });
        
        console.log(coinData);
      }).catch(err => console.log(err));

    } catch (error) {
      setErr({ value: error }); 
    }
  }

  return (
    <div className="App">
        <h1>
            HorucitoBot- tradea con el gatito loco.
        </h1>
        <section id="form-section" className="form-section">
          <form onSubmit={handleSubmit}>
            <input className='input-coin' type='text' placeholder='Coin to trade' value={state.value} onChange={handleInput}/>
          </form> 
        </section>
        <section id="coin-section" className="coin-section">
          <div className="card">
            <img className="coin-image" src={img.value} width="200px" height="200px" alt="coin"></img>
            {
              coinData.value !== null ? (
                <div className="container">
                  <h4><b>{coinData.value.symbol.toString().toUpperCase()}-({coinData.value.name})</b></h4>
                  <p>Current Max Price: {Math.max.apply(Math, coinData.value.tickers.map(exc =>{ 
                      return exc.target === 'USDT' ? exc.last : 0; 
                    })
                  )}
                  </p>
                  <p>Current binance price: {coinData.value.tickers.filter(ticker => ticker.market.indentifier === 'binance' && ticker.market.target === 'USDT').last}</p>
                  <p>Current Min Price: {Math.min.apply(Math, coinData.value.tickers.map(exc =>{ 
                      return exc.target === 'USDT' ? exc.last : 100000000000000; 
                    })
                  )}
                  </p>
                </div>
              ) : (<></>)
            }
          </div><i className="fa fa-xing" aria-hidden="true"></i>
        </section>
        <WebSocketService></WebSocketService>
    </div>
  );
}

export default App;
