import React, { PropTypes as T, Component} from 'react'
import AuthService from '../../utils/AuthService'
import { ButtonToolbar, Button} from 'react-bootstrap'
import styles from '../../styles/style.scss'

export default class Login extends Component {
  static propTypes ={
    location: T.object,
    auth: T.instanceOf(AuthService)
  }

  render() {
    const { auth } = this.props
    return (
      <div className={styles.root}> 
        <h2>Login</h2>
            <Button onClick={auth.login.bind(this)}>
              Login
            </Button>
      </div>
    )
  }
}
