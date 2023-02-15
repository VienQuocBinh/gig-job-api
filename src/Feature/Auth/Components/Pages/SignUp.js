import React,{useCallback} from "react";
import app from "./base";
import {withRoute} from "react-router";

const SignUp =({ history}) => {
    const handleSignUp = useCallback(async event =>{
        event,preventDefault();
        const {email,password } = event.target.elements;
        try{
            await app
                .auth()
                .createUserWithEmailAndPassword(email.value, password.value);
                history.push("/");
        }catch(error){
            alert (error);
        }
        
    }, [history]);
    return (
        <div>
            <h1>Sign up</h1>
            <form onSubmit={handleSignUp}>
                <lable>
                    Email
                    <input name="email" type ="email" placeholder="Email"/>
                    
                </lable>
                <lable>
                    password
                    <input name="password" type ="password" placeholder="Password"/>
                </lable>
                <button type ="Submit">Sign Up</button>
            </form>
        </div>
    );
        
};
export default withRoute(SignUp);