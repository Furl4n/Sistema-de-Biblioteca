import type { User } from "../interfaces/User";
import type { LoginData, SignupData } from "../interfaces/Auth";
import { createContext, useEffect, useState, type ReactNode } from "react";
import { useUserData } from "../hooks/useUser";
import { api, BASE_URL } from "../service/api";
import { setupInterceptors } from "../service/AuthInterceptor";
import { useQueryClient } from "@tanstack/react-query";

interface AuthContextData {
    user: User | undefined
    authenticated: boolean,
    loading: boolean,
    handleLogin: (credentials: LoginData) => Promise<void>,
    handleSignup: (data: SignupData) => Promise<void>,
    handleLogout: () => void
}

export const AuthContext = createContext<AuthContextData>({} as AuthContextData);

interface AuthProviderProps {
    children: ReactNode;
}

export function AuthProvider({ children } : AuthProviderProps) {
    const [user, setUser] = useState<User | undefined>(undefined);
    const[authenticated, setAuthenticated] = useState(false);
    const [loading, setLoading] = useState(true);
    const { data } = useUserData();
    const queryClient = useQueryClient();

    useEffect(()=>{
        if(data){
            setUser(data);
        }
    }, [data]);

    useEffect(() => {
        const token = localStorage.getItem("@Auth:token");

        if(token && token !== "undefined"){
            const parsedToken = JSON.parse(token);

            api.defaults.headers.common["Authorization"] = `Bearer ${parsedToken}`;

            setAuthenticated(true);
        }

        setupInterceptors(handleLogout);

        setLoading(false);
    }, []);

    async function handleLogin({email, password }: LoginData) {
        try{
            const response = await api.post(`${BASE_URL}/auth/login`, {
                email,
                password
            });
            const {token, refreshToken} = response.data;

            localStorage.setItem("@Auth:token", JSON.stringify(token));
            localStorage.setItem("@Auth:refreshToken", JSON.stringify(refreshToken));

            api.defaults.headers.common["Authorization"] = `Bearer ${token}`;

            setAuthenticated(true);
        } catch (error) {
            console.error("Erro no login: ", error);
            throw error;
        }
    }

    async function handleSignup({name, email, role, password}: SignupData) {
        try{
            await api.post(`${BASE_URL}/auth/signup`, {
                name,
                email,
                role,
                password
            });
        } catch (error) {
        console.error("Erro no cadastro: ", error);
        throw error;
        }
    }

    function handleLogout() {
        setAuthenticated(false);
        setUser(undefined);

        localStorage.removeItem("@Auth:token");
        localStorage.removeItem("@Auth:refreshToken");
        queryClient.clear();

        api.defaults.headers.common["Authorization"] = undefined;
    }

    return (
        <AuthContext.Provider
            value={{
                authenticated,
                loading,
                user,
                handleLogin,
                handleSignup,
                handleLogout
            }}
            >
                {children}
            </AuthContext.Provider>
    )
}