import { useContext, useState } from "react"
import { AuthContext } from "../../../context/AuthContext"
import type { LoginData } from "../../../interfaces/Auth"
import { isAxiosError } from "axios"
import { Link, useNavigate } from "react-router-dom"

function Login() {
    const context = useContext(AuthContext);
    const navigate = useNavigate();
    const [formLogin, setFormLogin] = useState<LoginData>({
        email: "",
        password: ""
    })

    function handleChangeEmail(value: string){
        setFormLogin({...formLogin, email: value})
    }

    function handleChangePassword(value: string){
        setFormLogin({...formLogin, password: value})
    }

    async function btnSaveClick(){
        try{
            await context?.handleLogin(formLogin);
            console.log("logou");
            navigate("/");
        } catch(error){
            if (isAxiosError(error)){
                console.error("Não logou!");
            }
        }
    }

    return (
        <>
            <p>testeeeeeeeeeeeeeee</p>

            <input type="email" id="campoEmail" onChange={(e) => handleChangeEmail(e.target.value)} value={formLogin.email} />
            <input type="password" id="campoPassword" onChange={(e) => handleChangePassword(e.target.value)} value={formLogin.password} />

            <button type="button" onClick={btnSaveClick}>Salvar</button>

            <Link to={"/signup"}>Criar conta</Link>
        </>
    )
}

export default Login;