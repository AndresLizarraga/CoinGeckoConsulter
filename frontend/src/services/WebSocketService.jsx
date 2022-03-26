import React, { Component } from "react/cjs/react.development";
import { W3CWebSocketClient } from "websocket/lib/WebSocketClient";

const client = new W3CWebSocketClient();

export class WebSocketService extends Component {
    constructor(props){
        super(props);
    }

    componentWillMount() {
        client.on('connectFailed', function(error) {
            console.log('Connect Error: ' + error.toString());
        });
        
        client.on('connect', function(connection) {
            console.log('WebSocket Client Connected');
            connection.on('error', function(error) {
                console.log("Connection Error: " + error.toString());
            });
            connection.on('close', function() {
                console.log('echo-protocol Connection Closed');
            });
            connection.on('message', function(message) {
                if (message.type === 'utf8') {
                    console.log("Received: '" + message.utf8Data + "'");
                }
            });
            
            function sendNumber() {
                if (connection.connected) {
                    var number = Math.round(Math.random() * 0xFFFFFF);
                    connection.sendUTF(number.toString());
                    setTimeout(sendNumber, 1000);
                }
            }
            sendNumber();
        });
        
        client.connect('ws://localhost:4200/', 'ws/v1/greetings');
    }

    async send(msg) {
        try{
            socket.send(msg).then();
        } catch(error) {

        }
    }

    render() {
        return(
            <div>
                Insane this as it is
            </div>
        );
    }
     
}
export default WebSocketService;