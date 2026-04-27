import { api, BASE_URL } from "../service/api";
import type { User } from "../interfaces/User";
import { useQuery } from '@tanstack/react-query';
import camelcaseKeys from "camelcase-keys";

const fetchData = async() => {
    const response = await api.get<User>(`${BASE_URL}/user/get`);
    return camelcaseKeys(response.data, {deep:true}) as User;
}

export function useUserData() {
    return useQuery({
        queryFn: () => fetchData(),
        queryKey: ['user-data']
    })
}