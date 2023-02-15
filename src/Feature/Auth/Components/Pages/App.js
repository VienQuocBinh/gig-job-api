import React from 'react';
import "./App.css"
import React from 'react';
import React from 'react';
import React from 'react';
import React from 'react';
import React from 'react';
import React from 'react';
import { AuthProvide } from './Auth';


const App = () =>{
    return (
        <AuthProvide>
        <Rounter>
            <div>
                <Route exact path ="/" component ={Home}/>
                <Route exact path ="/Login" component ={Login}/>
                <Route exact path = "/signup" component={SignUp} />
            </div>
        </Rounter>
        </AuthProvide>
    )
};

export default App;