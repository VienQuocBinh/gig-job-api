import React, { useContext } from "react"
import {Route, Redirect} from "react-rounter-dom";
import { AuthContext } from "./Auth";

const PrivateRounte =({component: RounteComponent, ...rest }) =>{
        const {currentUser} = useContext(AuthContext);
        return (
            <Route
            {...rest}
            render ={routeProps =>
             !!currentUser ? (
             <RounteComponent {...routeProps} /> 
        ) : (
            <Redirect to = {"/Login"} />
        )
            }
            />
        );
};
export default PrivateRounte