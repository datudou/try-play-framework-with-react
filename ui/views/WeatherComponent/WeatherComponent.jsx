import React, {Component} from 'react';
import Rx from 'rxjs/Rx'
import 'whatwg-fetch';

class SunWeatherComponent extends Component {
  constructor(props){
    super(props);
    this.state = {
      sunrise: undefined,
      sunset: undefined,
      temperature: undefined,
      request: 0,
    }
  }

  fetchData(){
    const requestStream = Rx.Observable.of('/data')
    const responseStream = requestStream
      .flatMap( url => Rx.Observable.fromPromise(fetch(url)))
      .flatMap( r => Rx.Observable.fromPromise(r.json()))
    responseStream.subscribe( data => {
      const { sunInfo,temperature } = data
      this.setState(
        {
          sunrise: sunInfo.sunrise,
          sunset: sunInfo.sunset,
          temperature: temperature,
        },
      )
    })
  }

  componentDidMount() {
    this.fetchData()
  }

  render(){
    return (
      <div>
        <table>
          <tbody>
            <tr>
              <td>Sunrise Time</td>
              <td>{this.state.sunrise}</td>
              </tr>
              <tr>
              <td>Sunset Time</td>
              <td>{this.state.sunset}</td>
              </tr>
              <tr>
              <td>Current temperature</td>
              <td>{this.state.temperature}</td>
              </tr>
          </tbody>
        </table>
      </div>
    )
  }
}

export default SunWeatherComponent;
