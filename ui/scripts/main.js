import React from 'react'
import ReactDOM from 'react-dom'
import makeMainRoutes from '../views/routes'
import {Route} from 'react-router'
import Bootstrap from 'bootstrap/dist/css/bootstrap.css';


ReactDOM.render(makeMainRoutes(), document.getElementById('reactView'));
