import { useContext, useState } from "react"
import { AuthContext } from "../../../context/AuthContext"
import type { SignupData } from "../../../interfaces/Auth";
import { isAxiosError } from "axios"
import { Link, useNavigate } from "react-router-dom"

function signup(){
    const context = useContext(AuthContext);
    const navigate = useNavigate();
    const[formSignup, setSignupData] = useState<SignupData>({
        name: "",
        email: "",
        password: "",
        confirmPassword: "",
        role: "ROLE_READER"
    })

    function handleChangeName(value: string){
        setSignupData({...formSignup, name: value})
    }

    function handleChangeEmail(value: string){
        setSignupData({...formSignup, email: value})
    }

    function handleChangePassword(value: string){
        setSignupData({...formSignup, password: value})
    }

    function handleChangeConfirmPassword(value: string){
        setSignupData({...formSignup, confirmPassword: value})
    }

    async function btnSignupClick(){
        try{
            await context?.handleSignup(formSignup);
            console.log("Conta criada!");
            navigate("/");
        } catch(error){
            if (isAxiosError(error)){
                console.log("A conta não foi criada!");
            }
        }
    }

    return(
        <>
        <p>Criar conta</p>

        <form onSubmit={btnSignupClick}>

            <div>
                <label htmlFor="name">name</label>
                <input type="text" id="inputName" onChange={(e) => handleChangeName(e.target.value)} value={formSignup.name}/>
            </div>
            
            <div>
                <label htmlFor="email">email</label>
                <input type="email" id="inputEmail" onChange={(e) => handleChangeEmail(e.target.value)} value={formSignup.email}/>
            </div>
            
            <div>
                <label htmlFor="name">password</label>
                <input type="password" id="inputPassword" onChange={(e) => handleChangePassword(e.target.value)} value={formSignup.password}/>
            </div>
            
            <div>
                <label htmlFor="name">Confirm password</label>
                <input type="password" id="inputConfirmPassword" onChange={(e) => handleChangeConfirmPassword(e.target.value)} value={formSignup.confirmPassword}/>
            </div>
            
            <button type="submit"> Salvar</button>
        
        </form>

        <Link to={"/login"}>Já tenho conta</Link>
        </>
    )
}

export default signup;