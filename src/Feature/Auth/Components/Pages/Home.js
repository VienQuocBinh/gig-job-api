import React from "react";
import app from "./base";

const Home = () =>{
    return (
        <>
            <h1>Home</h1>
            <button OnClick={() => app.auth().signOut()}>Sign out</button>
        </>
    )
}
export default Home;