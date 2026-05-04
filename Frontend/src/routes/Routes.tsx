import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import { PrivateRoute } from "./PrivateRoute";


export const routes = createBrowserRouter(
    createRoutesFromElements(
        <>
        <Route path="/" element={<PrivateRoute />}>
            {/*<Route index path="/" element={<Home/>}></Route>*/}
        </Route>

        {/*<Route path="/login" element={<login/>} />*/}
        {/*<Route path="/signup" element={<Signup/>} />*/}
        </>
    )
)