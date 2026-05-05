import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import { PrivateRoute } from "./PrivateRoute";
import Login from "../pages/auth/Login/Index";
import Signup from "../pages/auth/Signup/Index";
import Home from "../pages/app/home/Index";


export const routes = createBrowserRouter(
    createRoutesFromElements(
        <>
        <Route path="/" element={<PrivateRoute />}>
            <Route index path="/" element={<Home/>}></Route>
        </Route>

        <Route path="/login" element={<Login/>} />
        {<Route path="/signup" element={<Signup/>} />}
        </>
    )
)