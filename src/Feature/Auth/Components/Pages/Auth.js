import React, { useEffect, useEffect } from "react";
import app from "./base.js";

export const AuthContext = React.createContext ();

export const AuthProvide = ({ children }) => {
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        app.auth().onAuthStateChanged(setCurrentUser)
    }, []);

    return (
        <AuthContext.Provider
        value={{
            currentUser
        }}
        >
            {children}
        </AuthContext.Provider>
        )
}