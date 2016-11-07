import React from 'react'
import {Route, Router, IndexRedirect, browserHistory} from 'react-router'
import AuthService from '../utils/AuthService'
import SunWeatherComponent from './WeatherComponent/WeatherComponent.jsx'
import Container from './Container'
import Login from './Login/Login'

const AUTH0_CLIENT_ID="xxx"
const AUTH0_DOMAIN="xxx"
const auth = new AuthService(AUTH0_CLIENT_ID, AUTH0_DOMAIN)

function requireAuth (nextState, replace) {
  if(!auth.loggedIn()){
    replace({ pathname: '/login' })
  }
}

function makeMainRoutes (){
  return (
    <Router history={browserHistory}>
      <Route path='/' component={Container} auth={auth}>
        <IndexRedirect to='/home' />
        <Route path='home' component={SunWeatherComponent} onEnter={requireAuth} />
        <Route path='login' component={Login} />
      </Route>
    </Router>
  )
}

export default makeMainRoutes
