import { useContext } from "react";
import { AuthContext } from "../../../context/AuthContext";


function Home(){
    const { user, handleLogout } = useContext(AuthContext);

    return (
        <>
            <p>Nome: {user?.name}</p>
            <p>Nome: {user?.email}</p>
            <p>Nome: {user?.role}</p>
            <button type="button" onClick={handleLogout}>logout</button>
        </>
    )
}

export default Home;