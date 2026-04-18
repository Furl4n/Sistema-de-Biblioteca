import { api, BASE_URL } from "../service/api";
import type { User } from "../interfaces/User";
import { useQuery } from '@tanstack/react-query';

const fetchData = async() => {
    return  await api.get<User>(`${BASE_URL}/user/get`);
}

export function useUserData() {
    return useQuery({
        queryFn: () => fetchData(),
        queryKey: ['user-data']
    })
}