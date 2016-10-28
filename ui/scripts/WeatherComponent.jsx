import React, {Component} from 'react';
import 'whatwg-fetch';

class SunWeatherComponent extends Component {
  constructor(props){
    super(props);
    this.state = {
      sunrise: undefined,
    }
  }
  
  render(){
    return (
      <div>
        <div>
          <div>Sunrise Time:{this.state.sunrise}</div>
          <div>Sunset Time: {this.state.sunset}</div>
        </div>
        <div>Current tempature: {this.state.tempature}</div>
        <div>Request: {this.state.request}</div>
      </div>
    )
  }
}

export default SunWeatherComponent;
