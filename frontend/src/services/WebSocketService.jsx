import React, { Component } from "react/cjs/react.development";
import { w3cwebsocket as W3CWebSocket } from "websocket";

const socket = new W3CWebSocket('ws://127.0.0.1:8081');

export class WebSocketService extends Component {
    constructor(props){
        super(props);
    }

    componentWillMount() {
        socket.onopen = () => {
            console.log('Client connected to backend');
        };
        socket.onmessage = (message) => {
            console.log(message);
        };
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